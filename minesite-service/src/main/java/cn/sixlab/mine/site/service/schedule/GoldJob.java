package cn.sixlab.mine.site.service.schedule;

import cn.sixlab.datastrend.service.JdGoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GoldJob {

    @Autowired
    private JdGoldService jdGoldService;

    @Scheduled(cron = "0 3 7,19 * * ? ")
    public void daily(){
        jdGoldService.daily();
    }

    @Scheduled(cron = "0 0/5 * * * ? ")
    public void jdPrice() {
        jdGoldService.intrival();
    }

}
