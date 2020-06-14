package cn.sixlab.mine.site.service.schedule;

import cn.sixlab.mine.site.service.service.movie.MovieOkService;
import cn.sixlab.mine.site.service.service.movie.MovieZdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MovieJob {

    @Autowired
    private MovieOkService okService;

    @Autowired
    private MovieZdService zdService;

    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void daily(){
        okService.hour("2");
    }

    @Scheduled(cron = "0 30 0/1 * * ? ")
    public void jdPrice() {
        zdService.hour("2");
    }

}
