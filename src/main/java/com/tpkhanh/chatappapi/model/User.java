package com.tpkhanh.chatappapi.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    String id_user;
    String avatar;
    String name;
    String description;
    Boolean gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_account_user", referencedColumnName = "id_account")
    Account account;
}
