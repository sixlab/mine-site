package tech.minesoft.demo.movie;

import tech.minesoft.demo.api.MovieApiService;
import org.springframework.stereotype.Service;

@Service("movie_zd")
public class MovieZdService extends MovieApiService {

    @Override
    public String siteCode() {
        return "zd";
    }

}
