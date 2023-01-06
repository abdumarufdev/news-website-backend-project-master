package com.example.yangiliklarwebsayti.Repozitary;

import com.example.yangiliklarwebsayti.Entity.LavozimEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LavozimEntityRepozitary extends JpaRepository<LavozimEntity,Integer> {
    Optional<LavozimEntity> findByNomi(String nomi);
}
