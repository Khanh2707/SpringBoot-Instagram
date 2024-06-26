package com.tpkhanh.chatappapi.repository.httpclient;

import com.tpkhanh.chatappapi.dto.request.ExchangeTokenRequest;
import com.tpkhanh.chatappapi.dto.response.ExchangeTokenResponse;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "outbound-chatapp", url = "https://oauth2.googleapis.com")
public interface OutboundChatappClient {
    @PostMapping(value = "/token", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ExchangeTokenResponse exchangeToken(@QueryMap ExchangeTokenRequest request);
}
