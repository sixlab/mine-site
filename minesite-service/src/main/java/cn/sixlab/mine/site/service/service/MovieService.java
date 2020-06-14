package cn.sixlab.mine.site.service.service;

import cn.sixlab.mine.site.common.utils.Ctx;
import cn.sixlab.mine.site.data.mapper.VodSiteMapper;
import cn.sixlab.mine.site.data.models.VodSite;
import cn.sixlab.mine.site.service.api.MovieApiService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private VodSiteMapper siteMapper;

    public void init() {
        List<VodSite> siteList = siteMapper.selectInit();

        if(CollectionUtils.isNotEmpty(siteList)){
            for (VodSite site : siteList) {
                MovieApiService service = Ctx.getBean(MovieApiService.class, "movie_" + site.getSiteCode());
                service.init();
            }
        }
    }
}
