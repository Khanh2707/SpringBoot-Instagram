package com.tpkhanh.chatappapi.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchHistoryCreationRequest {
    String id_user_search_history_1;
    String id_user_search_history_2;
}
