package cn.sixlab.mine.site.service.api;

import cn.sixlab.mine.site.common.utils.JsonUtils;
import cn.sixlab.mine.site.data.mapper.VodInfoMapper;
import cn.sixlab.mine.site.data.mapper.VodInfoUrlsMapper;
import cn.sixlab.mine.site.data.models.VodInfo;
import cn.sixlab.mine.site.data.models.VodSite;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

public abstract class MovieService {

    @Autowired
    private VodInfoMapper infoMapper;

    @Autowired
    private VodInfoUrlsMapper urlsMapper;

    public void save(VodSite site, VodInfo info) {

        VodInfo existInfo = infoMapper.selectExist(info);
        if (null == existInfo) {
            String relate = site.getGroupRelate();

            Map<String, String> relateMap = JsonUtils.toBean(relate, Map.class);
            if (null != relate && relateMap.containsKey(info.getVodGroup())) {
                info.setVodGroup(relateMap.get(info.getVodGroup()));
            }

            info.setCreateTime(new Date());
            infoMapper.insert(info);
        } else {
            // 更新
            VodInfo newInfo = new VodInfo();
            newInfo.setId(existInfo.getId());

            // newInfo.setVodRemarks(info.getVodRemarks());
            // newInfo.setVodUrls(info.getVodUrls());

            newInfo.setUpdateTime(new Date());
            infoMapper.updateByPrimaryKeySelective(newInfo);
        }
    }

    public void parseUrls(VodSite site, VodInfo info) {

    }

}
