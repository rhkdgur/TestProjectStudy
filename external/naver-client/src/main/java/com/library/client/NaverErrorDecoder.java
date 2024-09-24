package com.library.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.ApiException;
import com.library.ErrorStatusType;
import com.library.NaverErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * packageName    : com.library.client
 * fileName       : NaverErrorDecoder
 * author         : rhkdg
 * date           : 2024-09-24
 * description    : parsing 에러에 대한 처리
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-24        rhkdg       최초 생성
 */
@Slf4j
public class NaverErrorDecoder implements ErrorDecoder {

    private  final ObjectMapper objectMapper;

    public NaverErrorDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
           String body = new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
            NaverErrorResponse naverErrorResponse = objectMapper.readValue(body,NaverErrorResponse.class);
            throw new ApiException(naverErrorResponse.errorMessage(), ErrorStatusType.EXTERNEL_API_ERROR, HttpStatus.valueOf(response.status()));
        } catch (IOException e) {
            log.error("[Naver] 에러 메세지 파싱 에러 methodKey={}, code={}, request={}, errorMessage={}",methodKey,response.status(),response.request(),e.getMessage());
            throw new ApiException("네이버 메세지 파싱에러", ErrorStatusType.EXTERNEL_API_ERROR, HttpStatus.valueOf(response.status()));
        }
    }
}
