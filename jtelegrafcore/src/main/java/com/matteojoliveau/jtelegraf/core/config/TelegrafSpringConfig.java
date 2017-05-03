package com.matteojoliveau.jtelegraf.core.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelegrafSpringConfig {

    @Bean
    public OkHttpClient httpClient() {
        return new OkHttpClient.Builder().build();
    }


}
