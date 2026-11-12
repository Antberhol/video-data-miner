package com.aisstwitchminer.twitchminer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TwitchRestClientConfig {

    @Bean
    public RestTemplate restTemplate(
            @Value("${TWITCH_CLIENT_ID:}") String clientId,
            @Value("${TWITCH_OAUTH_TOKEN:}") String oauthToken) {

        RestTemplate restTemplate = new RestTemplate();
        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
            if (clientId != null && !clientId.isBlank()) {
                request.getHeaders().set("Client-ID", clientId);
            }
            if (oauthToken != null && !oauthToken.isBlank()) {
                request.getHeaders().set(HttpHeaders.AUTHORIZATION, "Bearer " + oauthToken);
            }
            return execution.execute(request, body);
        };
        restTemplate.getInterceptors().add(interceptor);
        return restTemplate;
    }
}
