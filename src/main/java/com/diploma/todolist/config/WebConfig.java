package com.diploma.todolist.config;

import com.diploma.todolist.config.constant.UUIDCorrelationId;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zalando.logbook.CorrelationId;
import org.zalando.logbook.HeaderFilter;
import org.zalando.logbook.HeaderFilters;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.httpclient.LogbookHttpRequestInterceptor;
import org.zalando.logbook.httpclient.LogbookHttpResponseInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public HeaderFilter headerFilter(HttpLogProperties httpLogProperties) {
        return HeaderFilters.removeHeaders(header -> !httpLogProperties.getHeadersInclude().contains(header));
    }

    @Bean
    public CorrelationId correlationId() {
        return new UUIDCorrelationId();
    }

    @Bean
    public HttpClient httpClient(Logbook logbook) {
        return HttpClientBuilder.create()
                .addInterceptorFirst(new LogbookHttpRequestInterceptor(logbook))
                .addInterceptorLast(new LogbookHttpResponseInterceptor())
                .build();
    }

}

