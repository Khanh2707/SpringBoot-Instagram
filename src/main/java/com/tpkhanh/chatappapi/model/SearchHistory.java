package com.tpkhanh.chatappapi.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class SearchHistory {
    @EmbeddedId
    SearchHistoryKey id;

    @ManyToOne
    @MapsId("idUser1")
    @JoinColumn(name = "id_user_search_history_1")
    User user1;

    @ManyToOne
    @MapsId("idUser2")
    @JoinColumn(name = "id_user_search_history_2")
    User user2;

    LocalDateTime dateTimeSearch;
}
