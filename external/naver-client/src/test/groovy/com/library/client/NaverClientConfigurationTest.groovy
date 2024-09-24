package com.library.client

import feign.RequestTemplate
import spock.lang.Specification

/**
 * packageName    : com.library.client
 * fileName       : NaverClientConfigurationTest
 * author         : rhkdg
 * date           : 2024-09-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-24        rhkdg       최초 생성
 */
class NaverClientConfigurationTest extends Specification {
    NaverClientConfiguration configuration

    void setup() {
        configuration = new NaverClientConfiguration()
    }

    def "requestIntercepter에 header에 key값들이 들어간다" () {
        given: "clientId와 clientSecret을 주입해준다."
        def clientId = "id"
        def clientSecret = "secret"
        def template = new RequestTemplate()

        and: "intercepter 전에는 header값이 존재하지 않는다."
        template.headers()["x-Naver-Client-Id"] == null
        template.headers()["X-Naver-Client-Secret"] == null

        when: "requestInterceptor를 실행한다."
        def result = configuration.requestInterceptor(clientId,clientSecret)
        result.apply(template)

        then: "requestIntercepter에 header에 key값들이 추가된다"
        template.headers()["x-Naver-Client-Id"].contains(clientId)
        template.headers()["X-Naver-Client-Secret"].contains(clientSecret)
    }
}
