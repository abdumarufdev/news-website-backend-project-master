package com.example.yangiliklarwebsayti.Entity;

import com.example.yangiliklarwebsayti.Entity.Apstrakt.ApstraktEntity;
import com.example.yangiliklarwebsayti.Entity.Enum.Xuquqlar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class LavozimEntity extends ApstraktEntity {
  //  @NotBlank(message = "Problini olmaydi")
    private String nomi;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
 //   @NotEmpty(message = "Huquqlar bo'sh bo'lmasloigi kk")
    private List<Xuquqlar> xuquqlarList;
  //  @NotNull(message = "Izoh bo'sh bo'lmasin")
  @Column(nullable = false)
    private String izoh;
}
