package com.tpkhanh.chatappapi.repository;

import com.tpkhanh.chatappapi.model.InvalidatedToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedTokenRepository extends MongoRepository<InvalidatedToken, String> {
}
