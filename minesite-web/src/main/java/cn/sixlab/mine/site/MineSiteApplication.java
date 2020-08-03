package cn.sixlab.mine.site;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

// @EntityScan(basePackages = {
//         "cn.sixlab.mine.site.core.models",
// })
@EnableCaching
@EnableScheduling
@SpringBootApplication
@MapperScan("cn.sixlab.mine.site.core.mapper")
public class MineSiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MineSiteApplication.class, args);
    }

}
