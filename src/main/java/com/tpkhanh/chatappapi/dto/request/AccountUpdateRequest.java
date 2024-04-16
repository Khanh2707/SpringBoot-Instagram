package com.tpkhanh.chatappapi.dto.request;

import java.time.LocalDateTime;

public class AccountUpdateRequest {
    private String password;
    private LocalDateTime date_time_create;
    private Boolean state_active;
    private LocalDateTime last_time_active;

    public String getPassword() {
        return password;
    }

    public LocalDateTime getDate_time_create() {
        return date_time_create;
    }

    public Boolean getState_active() {
        return state_active;
    }

    public LocalDateTime getLast_time_active() {
        return last_time_active;
    }
}
