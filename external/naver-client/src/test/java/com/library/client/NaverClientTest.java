package com.library.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.library.client
 * fileName       : NaverClientTest
 * author         : rhkdg
 * date           : 2024-09-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-24        rhkdg       최초 생성
 */
// classes 설정은 SpringBootTest에 통합 테스트에 대해 TestConfig를 사용하겠다는 의미입니다.
@SpringBootTest(classes = NaverClientTest.TestConfig.class)
@ActiveProfiles("test")
class NaverClientTest {

    //NaverClient에 대한 의존성을 주입하기 위한 임시 config 입니다.
    //NaverClient는 FeignClients를 사용하기 때문에 그에 대한 EnableFeignClients를 선언하여 실직적인 NaverClient에 대한 bean을 생성
    //EnableAutoConfiguration은 자동 주입을 활성화하는 기능입니다.
    @EnableAutoConfiguration
    @EnableFeignClients(clients = NaverClient.class)
    static class TestConfig{}

    @Autowired
    NaverClient naverClient;

    @Test
    void callNaver() {
        String http = naverClient.search("HTTP", 1, 10);
        System.out.println(http);

        assertFalse(http.isEmpty());
    }
}