package cn.sixlab.mine.site;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan(basePackages = {
        "cn.sixlab.mine.site.data.models",
})
@EnableJpaRepositories(basePackages = {
        "cn.sixlab.mine.site.data.dao",
})
@EnableCaching
@EnableScheduling
public class MineSiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MineSiteApplication.class, args);
    }

    @Bean
    protected Module module() {
        return new Hibernate5Module();
    }

}
