package com.example.yangiliklarwebsayti.Repozitary;

import com.example.yangiliklarwebsayti.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepozitary extends JpaRepository<Users,Integer> {
    boolean existsByUsername(String username);
    Optional<Users> findByUsername(String username);
    Optional<Users> findByUsernameAndEmailkod(String username, String emailkod);


}
