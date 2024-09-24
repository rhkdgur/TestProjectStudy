package com.library;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * packageName    : com.library
 * fileName       : ApiException
 * author         : rhkdg
 * date           : 2024-09-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-24        rhkdg       최초 생성
 */
@Getter
public class ApiException extends  RuntimeException {

    private final String errorMessage;

    private final ErrorStatusType errorStatusType;

    private final HttpStatus httpStatus;

    public ApiException(String errorMessage, ErrorStatusType errorStatusType, HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.errorStatusType = errorStatusType;
        this.httpStatus = httpStatus;
    }

}
