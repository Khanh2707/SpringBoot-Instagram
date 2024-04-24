package com.tpkhanh.chatappapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Embeddable
public class SearchHistoryKey implements Serializable {
    @Column(name = "id_user_search_history_1")
    String idUser1;

    @Column(name = "id_user_search_history_2")
    String idUser2;
}
