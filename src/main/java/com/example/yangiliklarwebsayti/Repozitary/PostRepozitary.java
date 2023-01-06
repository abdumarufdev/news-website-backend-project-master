package com.example.yangiliklarwebsayti.Repozitary;

import com.example.yangiliklarwebsayti.Entity.Post;
//import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepozitary extends JpaRepository<Post,Integer> {
    boolean existsByTitile(String titile);
    boolean existsById(Integer id);
}
