package cn.sixlab.mine.site.service.config;

import cn.sixlab.mine.site.service.service.JobService;
import cn.sixlab.mine.site.service.service.movie.MovieOkService;
import cn.sixlab.mine.site.service.service.movie.MovieZdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobConfig {

    @Autowired
    private JobService service;

    @Scheduled(cron = "0 3 7,19 * * ? ")
    public void daily() {
        service.run("jdGoldDailyService");
    }

    @Scheduled(cron = "0 0/5 * * * ? ")
    public void jdPrice() {
        service.run("jdGoldIntervalService");
    }

}
