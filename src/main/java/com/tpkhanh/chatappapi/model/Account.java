package com.tpkhanh.chatappapi.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_account;
    private String account;
    private String password;
    private LocalDateTime date_time_create;
    private Boolean state_active;
    private LocalDateTime last_time_active;

    public Integer getId_account() {
        return id_account;
    }

    public void setId_account(Integer id_account) {
        this.id_account = id_account;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDate_time_create() {
        return date_time_create;
    }

    public void setDate_time_create(LocalDateTime date_time_create) {
        this.date_time_create = date_time_create;
    }

    public Boolean getState_active() {
        return state_active;
    }

    public void setState_active(Boolean state_active) {
        this.state_active = state_active;
    }

    public LocalDateTime getLast_time_active() {
        return last_time_active;
    }

    public void setLast_time_active(LocalDateTime last_time_active) {
        this.last_time_active = last_time_active;
    }
}
