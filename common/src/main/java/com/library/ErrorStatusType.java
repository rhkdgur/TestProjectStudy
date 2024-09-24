package com.library;

import lombok.Getter;

/**
 * packageName    : com.library
 * fileName       : ErrorStatusType
 * author         : rhkdg
 * date           : 2024-09-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-24        rhkdg       최초 생성
 */
@Getter
public enum ErrorStatusType {

    EXTERNEL_API_ERROR("외부 API 호출 에러입니다."),
    UNKNOWN("알수없는 에러입니다."),
    INVALID_PARAMETER("잘못된 요청입니다.");

    private final String description;

    ErrorStatusType(String description) {
        this.description = description;
    }
}
