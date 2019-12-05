package com.server.blog.common.config;

import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan("com.server.blog.myblog.*.dao")
public class MybatisPlusConfig {
    @Bean
    public ISqlInjector sqlInjector() {
        return new DefaultSqlInjector();
    }
}