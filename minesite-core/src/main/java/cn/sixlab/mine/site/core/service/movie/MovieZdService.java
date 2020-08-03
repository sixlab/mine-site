package cn.sixlab.mine.site.core.service.movie;

import cn.sixlab.mine.site.core.api.MovieApiService;
import org.springframework.stereotype.Service;

@Service("movie_zd")
public class MovieZdService extends MovieApiService {

    @Override
    public String siteCode() {
        return "zd";
    }

}
