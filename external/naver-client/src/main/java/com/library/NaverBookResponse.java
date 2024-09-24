package com.library;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.library
 * fileName       : NaverBookResponse
 * author         : rhkdg
 * date           : 2024-09-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-24        rhkdg       최초 생성
 */
@Getter
@ToString
public class NaverBookResponse {

    private String lastBuildDate;

    private int total;

    private int start;

    private int display;

    private List<Item> items;

}
