package cn.sixlab.mine.site.core.service.movie;

import cn.sixlab.mine.site.core.api.MovieApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("movie_ku123")
public class MovieKu123Service extends MovieApiService {

    @Override
    public String siteCode() {
        return "ku123";
    }

}
