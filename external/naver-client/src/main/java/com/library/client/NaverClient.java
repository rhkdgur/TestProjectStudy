package com.library.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * packageName    : com.library.client
 * fileName       : NaverClient
 * author         : rhkdg
 * date           : 2024-09-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-24        rhkdg       최초 생성
 */

@FeignClient(name = "naverClient", url="${external.naver.url}",configuration = NaverClientConfiguration.class)
public interface NaverClient {
    @GetMapping("/v1/search/book.json")
    String search(@RequestParam("query") String query,
                  @RequestParam("start") int start,
                  @RequestParam("display") int display);
}
