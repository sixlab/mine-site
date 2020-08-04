package tech.minesoft.demo;

import tech.minesoft.minesite.core.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobConfig {

    @Autowired
    private JobService service;

    @Scheduled(cron = "0 0 7 * * ? ")
    public void daily() {
        service.run("testService");
    }

}
