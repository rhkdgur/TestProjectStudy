package com.library.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * packageName    : com.library.client
 * fileName       : NaverClientConfiguration
 * author         : rhkdg
 * date           : 2024-09-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-24        rhkdg       최초 생성
 */
public class NaverClientConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor (@Value("${external.naver.headers.client-id}") String clientId,
                                                  @Value("${external.naver.headers.client-secret}") String clientSecret) {

        return requestTemplate -> requestTemplate.header("X-Naver-Client-Id",clientId)
                .header("X-Naver-Client-Secret",clientSecret);
    }
}
