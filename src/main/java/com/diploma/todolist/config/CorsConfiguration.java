package com.diploma.todolist.config;

import com.diploma.todolist.config.constant.OpenApiCorsFilter;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@Slf4j
public class CorsConfiguration {

    @Bean
    public FilterRegistrationBean<OpenApiCorsFilter> openApiCorsFilter(Environment env) {
        FilterRegistrationBean<OpenApiCorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new OpenApiCorsFilter());
        registrationBean.addUrlPatterns(env.getProperty("springdoc.api-docs.path"));
        return registrationBean;
    }

    @Bean
    public OpenApiCustomiser serverOpenApiCustomiser() {
        return openAPI -> {
            if (openAPI == null) {
                log.warn("openAPI is null");
                return;
            }
            if (openAPI.getServers() == null) {
                log.warn("openAPI.getServers() is null");
                return;
            }
            openAPI.getServers().forEach(server -> {
                if (server.getUrl() == null) {
                    log.warn("server.getUrl() is null");
                } else {
                    server.setUrl(server.getUrl().replaceFirst("http", "https"));
                }
            });
        };
    }
}

