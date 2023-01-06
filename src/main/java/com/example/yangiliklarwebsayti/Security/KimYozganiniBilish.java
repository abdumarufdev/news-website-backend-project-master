package com.example.yangiliklarwebsayti.Security;

import com.example.yangiliklarwebsayti.Entity.Users;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class KimYozganiniBilish implements AuditorAware<Integer> {

    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()  && authentication!=null){
            Users users = (Users) authentication.getPrincipal();
            return Optional.of(users.getId());
        }
        return Optional.empty();
    }
}
