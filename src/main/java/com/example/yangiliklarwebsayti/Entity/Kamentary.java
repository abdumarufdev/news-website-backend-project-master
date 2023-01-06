package com.example.yangiliklarwebsayti.Entity;

import com.example.yangiliklarwebsayti.Entity.Apstrakt.ApstraktEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Kamentary extends ApstraktEntity {
    @Column(nullable = false)
    private String matn;
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;


}
