package cn.sixlab.mine.site.service.service.movie;

import cn.sixlab.mine.site.service.api.MovieApiService;
import org.springframework.stereotype.Service;

@Service("movie_zd")
public class MovieZdService extends MovieApiService {

    @Override
    public String siteCode() {
        return "zd";
    }

}
