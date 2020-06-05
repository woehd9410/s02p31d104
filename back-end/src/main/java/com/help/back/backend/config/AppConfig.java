package com.help.back.backend.config;

import javax.sql.DataSource;

import com.help.back.backend.config.security.JwtInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@MapperScan(basePackages = "com.help.back.backend.dao")
@EnableTransactionManagement
public class AppConfig implements WebMvcConfigurer {

    private static final String[] EXCLUDE_PATHS = {"/swagger-resources/**", "/swagger-ui.html", "/swagger/**",
            "/error", "/webjars/**", "/swagger-ui.html#/**/**", "/message", "/history", "/file", "/api/v1/user/login",
            "/v2/api-docs", "/api/v1/user/kakao-login", "/api/v1/user/email/**", "/api/v1/user/password", "/api/v1/group-user/id/**"
            };

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("addInterceptors 실행");
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**", "/**/**", "/**/**/**", "/**/**/**/**").excludePathPatterns(EXCLUDE_PATHS);
    }
}