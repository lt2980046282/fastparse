package org.nop.fastparse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "org.nop.fastparse.mapper")
@SpringBootApplication
public class FastParseApplication {
    public static void main(String[] args) {
        SpringApplication.run(FastParseApplication.class, args);
    }
}
