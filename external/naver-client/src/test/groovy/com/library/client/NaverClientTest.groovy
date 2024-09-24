package com.library.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.test.context.ActiveProfiles
import spock.lang.Ignore
import spock.lang.Specification

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

@Ignore
@SpringBootTest(classes = TestConfig.class)
@ActiveProfiles("test")
class NaverClientItTest extends Specification {

    @EnableAutoConfiguration
    @EnableFeignClients(clients = NaverClient.class)
    static class TestConfig{}

    @Autowired
    NaverClient naverClient

    def "Naver 호출" () {
        given:

        when:
        def response = naverClient.search("HTTP", 1, 10)

        then:
        response.size() == 10
    }
}
