package com.example.yangiliklarwebsayti.Entity;

import com.example.yangiliklarwebsayti.Entity.Apstrakt.ApstraktEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Post extends ApstraktEntity {
    @Column(nullable = false,columnDefinition = "text")
    private String titile;
    @Column(nullable = false,columnDefinition = "text")
    private String text;

   /* @OneToMany
     private List<Kamentary> kamentaries;*/

}
