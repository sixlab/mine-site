package cn.sixlab.mine.site.core.schedule;

import cn.sixlab.mine.site.core.api.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService implements Job {
    private static String CODE = "TEST";

    @Override
    public void run() {
        log.info("job run:"+CODE);
    }

}
