package com.mokinder.TrackerProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ThirdpartyConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public WebClient twelveDataApiClient() throws Exception {
//        SslContextBuilder sslContextBuilder = SslContextBuilder.forClient()
//                .trustManager(InsecureTrustManagerFactory.INSTANCE);
//        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContextBuilder));
//        ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
//
//        return WebClient.builder()
//                .baseUrl("https://api.twelvedata.com")
//                .clientConnector(connector)
//                .build();
//    }


}


