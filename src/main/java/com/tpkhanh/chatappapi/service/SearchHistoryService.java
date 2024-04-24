package com.tpkhanh.chatappapi.service;

import com.tpkhanh.chatappapi.dto.request.SearchHistoryCreationRequest;
import com.tpkhanh.chatappapi.dto.response.SearchHistoryResponse;
import com.tpkhanh.chatappapi.exception.AppException;
import com.tpkhanh.chatappapi.exception.ErrorCode;
import com.tpkhanh.chatappapi.mapper.SearchHistoryMapper;
import com.tpkhanh.chatappapi.model.SearchHistory;
import com.tpkhanh.chatappapi.model.SearchHistoryKey;
import com.tpkhanh.chatappapi.model.User;
import com.tpkhanh.chatappapi.repository.SearchHistoryRepository;
import com.tpkhanh.chatappapi.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SearchHistoryService {

    UserRepository userRepository;
    SearchHistoryMapper searchHistoryMapper;
    SearchHistoryRepository searchHistoryRepository;

    public List<SearchHistoryResponse> getAllSearchHistory() {
        return searchHistoryRepository.findAllByOrderByDateTimeSearchDesc().stream().map(searchHistoryMapper::toSearchHistoryResponse).toList();
    }

    public SearchHistoryResponse createSearchHistory(SearchHistoryCreationRequest request) {

        SearchHistory searchHistory = searchHistoryMapper.toSearchHistory(request);

        User user1 = userRepository.findById(request.getId_user_search_history_1()).orElseThrow(() -> new AppException(ErrorCode.ID_USER_NOT_EXISTED));
        searchHistory.setUser1(user1);

        User user2 = userRepository.findById(request.getId_user_search_history_2()).orElseThrow(() -> new AppException(ErrorCode.ID_USER_NOT_EXISTED));
        searchHistory.setUser2(user2);

        SearchHistoryKey searchHistoryKey = new SearchHistoryKey();
        searchHistoryKey.setIdUser1(user1.getIdUser());
        searchHistoryKey.setIdUser2(user2.getIdUser());
        searchHistory.setId(searchHistoryKey);

        searchHistory.setDateTimeSearch(LocalDateTime.now());

        return searchHistoryMapper.toSearchHistoryResponse(searchHistoryRepository.save(searchHistory));
    }

    public void deleteSearchHistory(String id_user_search_history_1, String id_user_search_history_2) {
        SearchHistoryKey searchHistoryKey = new SearchHistoryKey(id_user_search_history_1, id_user_search_history_2);
        searchHistoryRepository.deleteById(searchHistoryKey);
    }

    public void deleteAllSearchHistory() {
        searchHistoryRepository.deleteAll();
    }
}
