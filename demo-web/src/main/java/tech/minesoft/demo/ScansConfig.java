package tech.minesoft.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("tech.minesoft.minesite.core.mapper")
@MapperScan("tech.minesoft.demo.mapper")
@ComponentScan("tech.minesoft.minesite")
@Configuration
public class ScansConfig {
}
