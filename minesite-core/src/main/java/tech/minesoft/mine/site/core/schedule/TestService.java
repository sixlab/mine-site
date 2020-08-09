package tech.minesoft.mine.site.core.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.minesoft.mine.site.core.api.Job;

@Slf4j
@Service
public class TestService implements Job {
    private static String CODE = "TEST";

    @Override
    public void run() {
        log.info("job run:"+CODE);
    }

}
