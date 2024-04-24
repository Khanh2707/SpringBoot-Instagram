package com.tpkhanh.chatappapi.mapper;

import com.tpkhanh.chatappapi.dto.request.SearchHistoryCreationRequest;
import com.tpkhanh.chatappapi.dto.response.SearchHistoryResponse;
import com.tpkhanh.chatappapi.model.SearchHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SearchHistoryMapper {

    SearchHistory toSearchHistory(SearchHistoryCreationRequest request);

    SearchHistoryResponse toSearchHistoryResponse(SearchHistory searchHistory);
}
