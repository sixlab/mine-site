package cn.sixlab.mine.site.service.api;

import cn.sixlab.mine.site.core.utils.HttpUtils;
import cn.sixlab.mine.site.core.utils.JsonUtils;
import cn.sixlab.mine.site.core.vo.ResultJson;
import cn.sixlab.mine.site.core.mapper.VodInfoMapper;
import cn.sixlab.mine.site.core.mapper.VodInfoUrlsMapper;
import cn.sixlab.mine.site.core.mapper.VodPlayerMapper;
import cn.sixlab.mine.site.core.mapper.VodSiteMapper;
import cn.sixlab.mine.site.core.models.VodInfo;
import cn.sixlab.mine.site.core.models.VodInfoUrls;
import cn.sixlab.mine.site.core.models.VodPlayer;
import cn.sixlab.mine.site.core.models.VodSite;
import cn.sixlab.mine.site.service.vo.Vod;
import cn.sixlab.mine.site.service.vo.VodList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public abstract class MovieApiService {

    @Autowired
    private VodSiteMapper siteMapper;

    @Autowired
    private VodInfoMapper infoMapper;

    @Autowired
    private VodPlayerMapper playerMapper;

    @Autowired
    private VodInfoUrlsMapper urlsMapper;

    private ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    private Map<String, String> relateMap;

    public void init() {
        hour("");
    }

    public void hour(String hour) {
        VodSite vodSite = siteMapper.selectByCode(siteCode());

        if(null==vodSite){
            return;
        }

        if (vodSite.getPage() > 0) {
            log.error(vodSite.getPage() + "页正在处理，开始时间:" + vodSite.getBeginTime());
            // TODO 警报
            return;
        }

        relateMap = JsonUtils.toBean(vodSite.getGroupRelate(), Map.class);
        if (null == relateMap) {
            relateMap = new HashMap<>();
        }

        int page = 1;

        while (page > 0) {
            VodSite site = new VodSite();
            site.setId(vodSite.getId());
            site.setPage(page);
            if (page == 1) {
                site.setBeginTime(new Date());
            }
            siteMapper.updateByPrimaryKeySelective(site);

            page = page(vodSite, page, hour);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        VodSite site = new VodSite();
        site.setId(vodSite.getId());
        site.setPage(0);
        site.setEndTime(new Date());
        siteMapper.updateByPrimaryKeySelective(site);
    }

    public abstract String siteCode();

    public int page(VodSite vodSite, int page, String hour) {
        String url = vodSite.getSiteUrl();

        // page
        if (url.contains("?")) {
            // XXX?a=b&c=d
            url = url + "&";
        } else {
            // XXX
            url = url + "?";
        }
        url = url + "pg=" + page;

        // hour
        if (StringUtils.isNotEmpty(hour)) {
            url = url + "&pg=" + hour;
        }

        ResultJson resultJson = HttpUtils.sendGet(url, null);
        if (resultJson.isSuccess()) {
            VodList vodList = parseXml(resultJson.getMessage());
            if (null != vodList && CollectionUtils.isNotEmpty(vodList.getVodList())) {
                vodList.getVodList().forEach(vod -> save(vodSite, vod));

                if (page < vodList.getTotalPage()) {
                    return page + 1;
                }
            }
        }

        return 0;
    }

    public VodList parseXml(String xml) {
        try {
            VodList vodList = new VodList();

            Document document = DocumentHelper.parseText(xml);
            Element root = document.getRootElement();
            Element list = root.element("list");

            vodList.setTotalPage(Integer.valueOf(list.attributeValue("pagecount")));

            List<Vod> vods = new ArrayList<>();
            list.elements().forEach(video -> {
                VodInfo info = new VodInfo();
                info.setVodName(video.element("name").getTextTrim());
                info.setVodGroup(video.element("type").getTextTrim());
                info.setVodPic(video.element("pic").getTextTrim());
                info.setVodLang(video.element("lang").getTextTrim());
                info.setVodArea(video.element("area").getTextTrim());
                info.setVodYear(video.element("year").getTextTrim());
                info.setVodActor(video.element("actor").getTextTrim());
                info.setVodDirector(video.element("director").getTextTrim());
                info.setVodIntro(video.element("des").getTextTrim());

                Map<String, String> urlTexts = new LinkedHashMap<>();
                Element dl = video.element("dl");
                dl.elements("dd").forEach(dd -> {
                    String key = dd.attributeValue("flag");
                    String val = dd.getTextTrim();
                    urlTexts.put(key, val);
                });

                Vod vod = new Vod();
                vod.setRemark(video.element("note").getTextTrim());
                vod.setVodInfo(info);
                vod.setUrlTexts(urlTexts);
                vods.add(vod);
            });
            vodList.setVodList(vods);

            return vodList;
        } catch (DocumentException e) {
            e.printStackTrace();
            log.error("DOM解析异常", e);
            // TODO 警报
        }
        return null;
    }

    public void save(VodSite site, Vod vod) {
        VodInfo info = vod.getVodInfo();
        List<VodInfoUrls> urlsList = parseUrls(vod.getRemark(), vod.getUrlTexts());

        VodInfo existInfo = infoMapper.selectExist(info);
        if (null == existInfo) { // 新增
            // 处理分类

            String vodGroup = info.getVodGroup();
            vodGroup = MapUtils.getString(relateMap, vodGroup, vodGroup);
            info.setVodGroup(vodGroup);

            info.setCreateTime(new Date());
            infoMapper.insert(info);

            urlsMapper.insertList(info.getId(), urlsList);
        } else {
            // 更新
            VodInfo newInfo = new VodInfo();
            newInfo.setId(existInfo.getId());
            newInfo.setUpdateTime(new Date());
            infoMapper.updateByPrimaryKeySelective(newInfo);

            List<VodInfoUrls> toInsert = new ArrayList<>();
            for (VodInfoUrls url : urlsList) {
                VodInfoUrls existUrl = urlsMapper.selectPlayerUrl(existInfo.getId(), url.getPlayerCode());

                if (null != existUrl) {
                    VodInfoUrls newUrl = new VodInfoUrls();
                    newUrl.setId(existUrl.getId());
                    newUrl.setVodRemarks(url.getVodRemarks());
                    newUrl.setVodUrls(url.getVodUrls());
                    urlsMapper.updateByPrimaryKeySelective(newUrl);
                } else {
                    toInsert.add(url);
                }
            }

            if (CollectionUtils.isNotEmpty(toInsert)) {
                urlsMapper.insertList(existInfo.getId(), toInsert);
            }
        }
    }

    public List<VodInfoUrls> parseUrls(String remark, Map<String, String> urlTexts) {
        List<VodInfoUrls> urlsList = new ArrayList<>();

        for (String key : urlTexts.keySet()) {
            String val = urlTexts.get(key);

            VodInfoUrls item = new VodInfoUrls();
            item.setStatus(1);
            item.setVodRemarks(remark);
            item.setPlayerCode(key);
            item.setPlayerName(playerName(key));

            // 第01集$http://yuledy.helanzuida.com/20200621/6247_2744a8d2/index.m3u8#
            // 第02集$http://yuledy.helanzuida.com/20200622/6300_736c05f2/index.m3u8
            item.setVodUrls(val);

            urlsList.add(item);
        }

        return urlsList;
    }

    public String playerName(String playerCode) {
        String playerName = map.get(playerCode);
        if (StringUtils.isEmpty(playerName)) {
            VodPlayer vodPlayer = playerMapper.selectByCode(playerCode);

            if (null == vodPlayer) {
                playerName = playerCode;
            } else {
                playerName = vodPlayer.getPlayerName();
                map.put(playerCode, playerName);
            }
        }

        return playerName;
    }

}
