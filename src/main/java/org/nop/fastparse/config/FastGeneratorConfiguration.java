package org.nop.fastparse.config;

import org.nop.fastparse.entity.Generator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnClass(FastGeneratorService.class)
@EnableConfigurationProperties(Generator.class)

public class FastGeneratorConfiguration {
    private Generator generator;

    // 自动注入配置类
    public FastGeneratorConfiguration(Generator generator) {
        this.generator = generator;
    }

    @Bean
    @ConditionalOnMissingBean(FastGeneratorService.class) // 当容器没有此bean时，才注册
    public FastGeneratorService setService(){
        FastGeneratorService fastGeneratorService = new FastGeneratorService();
        fastGeneratorService.setBasePackage(fastGeneratorService.getBasePackage());
        fastGeneratorService.setName(generator.getName());
        return fastGeneratorService;
    }
}
