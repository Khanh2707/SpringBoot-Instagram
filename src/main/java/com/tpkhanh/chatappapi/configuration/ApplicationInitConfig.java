package com.tpkhanh.chatappapi.configuration;

import com.tpkhanh.chatappapi.enums.RoleEnum;
import com.tpkhanh.chatappapi.model.Account;
import com.tpkhanh.chatappapi.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(AccountRepository accountRepository) {
        return args -> {
            if (accountRepository.findByAccount("admin").isEmpty()) {
                var roles = new HashSet<String>();
                roles.add(RoleEnum.ADMIN.name());
                Account account = Account.builder()
                        .account("admin")
                        .password(passwordEncoder.encode("admin"))
                        .dateTimeCreate(LocalDateTime.now())
//                        .roles(roles)
                        .build();

                accountRepository.save(account);
                log.warn("admin user has bean created with default password: admin, please change it!");
            }
        };
    }
}
