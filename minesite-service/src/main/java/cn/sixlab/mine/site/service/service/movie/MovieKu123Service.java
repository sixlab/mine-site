package cn.sixlab.mine.site.service.service.movie;

import cn.sixlab.mine.site.service.api.MovieApiService;
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
