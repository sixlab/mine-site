package cn.sixlab.mine.site.service.api;

import cn.sixlab.mine.site.common.utils.JsonUtils;
import cn.sixlab.mine.site.data.mapper.VodInfoMapper;
import cn.sixlab.mine.site.data.mapper.VodInfoUrlsMapper;
import cn.sixlab.mine.site.data.mapper.VodPlayerMapper;
import cn.sixlab.mine.site.data.models.VodInfo;
import cn.sixlab.mine.site.data.models.VodInfoUrls;
import cn.sixlab.mine.site.data.models.VodPlayer;
import cn.sixlab.mine.site.data.models.VodSite;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class MovieService {

    @Autowired
    private VodInfoMapper infoMapper;

    @Autowired
    private VodPlayerMapper playerMapper;

    @Autowired
    private VodInfoUrlsMapper urlsMapper;

    private ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    protected abstract void init();

    public void save(VodSite site, VodInfo info, List<VodInfoUrls> urlsList) {

        VodInfo existInfo = infoMapper.selectExist(info);
        if (null == existInfo) { // 新增
            // 处理分类
            String relate = site.getGroupRelate();
            Map<String, String> relateMap = JsonUtils.toBean(relate, Map.class);
            if (null != relate && relateMap.containsKey(info.getVodGroup())) {
                info.setVodGroup(relateMap.get(info.getVodGroup()));
            }

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

            if(CollectionUtils.isNotEmpty(toInsert)){
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
        if(StringUtils.isEmpty(playerName)){
            VodPlayer vodPlayer = playerMapper.selectByCode(playerCode);

            if(null==vodPlayer){
                playerName = playerCode;
            }else{
                playerName = vodPlayer.getPlayerName();
                map.put(playerCode, playerName);
            }
        }

        return playerName;
    }

}
