package com.tpkhanh.chatappapi.controller;

import com.tpkhanh.chatappapi.dto.request.ApiResponse;
import com.tpkhanh.chatappapi.dto.request.SearchHistoryCreationRequest;
import com.tpkhanh.chatappapi.dto.response.SearchHistoryResponse;
import com.tpkhanh.chatappapi.service.SearchHistoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search_history")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SearchHistoryController {

    SearchHistoryService searchHistoryService;

    @GetMapping("")
    ApiResponse<List<SearchHistoryResponse>> getAllSearchHistory() {
        return ApiResponse.<List<SearchHistoryResponse>>builder()
                .result(searchHistoryService.getAllSearchHistory())
                .build();
    }

    @GetMapping("/{idUser1}")
    ApiResponse<List<SearchHistoryResponse>> getAllByUser1SearchHistory(@PathVariable String idUser1) {
        return ApiResponse.<List<SearchHistoryResponse>>builder()
                .result(searchHistoryService.getAllByUser1SearchHistory(idUser1))
                .build();
    }

    @PostMapping("")
    ApiResponse<SearchHistoryResponse> createSearchHistory(@RequestBody SearchHistoryCreationRequest request) {
        return ApiResponse.<SearchHistoryResponse>builder()
                .result(searchHistoryService.createSearchHistory(request))
                .build();
    }

    @DeleteMapping("/{id_user_search_history_1}/{id_user_search_history_2}")
    ApiResponse<String> deleteSearchHistory(@PathVariable String id_user_search_history_1, @PathVariable String id_user_search_history_2) {
        searchHistoryService.deleteSearchHistory(id_user_search_history_1, id_user_search_history_2);
        return ApiResponse.<String>builder()
                .result("Search history has been deleted")
                .build();
    }

    @DeleteMapping("")
    ApiResponse<String> deleteAllSearchHistory() {
        searchHistoryService.deleteAllSearchHistory();
        return ApiResponse.<String>builder()
                .result("All search history has been deleted")
                .build();
    }
}
