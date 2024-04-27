package com.tpkhanh.chatappapi.repository;

import com.tpkhanh.chatappapi.model.SearchHistory;
import com.tpkhanh.chatappapi.model.SearchHistoryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, SearchHistoryKey> {
    Collection<SearchHistory> findAllByOrderByDateTimeSearchDesc();
    List<SearchHistory> findById_IdUser1OrderByDateTimeSearchDesc(String idUser1);
}
