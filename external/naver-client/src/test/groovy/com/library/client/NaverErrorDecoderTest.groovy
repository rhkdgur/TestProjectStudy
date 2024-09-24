package com.library.client

import com.fasterxml.jackson.databind.ObjectMapper
import feign.Request
import feign.Response
import spock.lang.Specification

/**
 * packageName    : com.library.client
 * fileName       : NaverErrorDecoderTest
 * author         : rhkdg
 * date           : 2024-09-24
 * description    : 네이버 외부 API 에러 처리에 대한 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-24        rhkdg       최초 생성
 */
class NaverErrorDecoderTest extends Specification {

    ObjectMapper objectMapper = Mock()
    NaverErrorDecoder naverErrorDecoder = new NaverErrorDecoder(objectMapper)

    def "error decoder에서 에러 발생시 RuntimeException 예외가 발생한다."() {
        given : "400 에러를 발생조건으로 선언한다."
        //ErrorDecoder 호출을 위한 responseBody를 세팅해준다.
        def responseBody = Mock(Response.Body)
        def inputStream = new ByteArrayInputStream()
        def response = Response.builder()
        .status(400)
        .body(responseBody)
        .request(Request.create(Request.HttpMethod.GET, "testUrl",[:],null as Request.Body,null))
        .build()

        and:
        //1 회 호출 되는지 검증 그 결과는 inputStream 임의 설정 값으로 지정
        1* responseBody.asInputStream() >> inputStream
        // objectMapper 호출 값은 임의로 선언한 NaverErrorResponse가 된다라는 의미
        1 * objectMapper.readValue(*_) >> new NaverErrorResponse("error!!", "SE03")

        when : "decode가 발생한다."
        //decode 실행시 안에 들어가는 response 값과 objectMapper 값들은 위에 선언된 값들로 이루어진다.
        naverErrorDecoder.decode(_ as String, response)

        then : "RuntimeException이 발생한다."
        RuntimeException e = thrown()
        e.message == "error!!"

    }
}
