package com.library.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.NaverErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;

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
            throw new RuntimeException(naverErrorResponse.errorMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
