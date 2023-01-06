package com.example.yangiliklarwebsayti.Repozitary;

import com.example.yangiliklarwebsayti.Entity.Kamentary;
import com.example.yangiliklarwebsayti.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KamentaryRepozitary extends JpaRepository<Kamentary,Integer> {

    //boolean existsByPostId(Integer post_id);
    boolean existsByPostId(Integer post_id);
}