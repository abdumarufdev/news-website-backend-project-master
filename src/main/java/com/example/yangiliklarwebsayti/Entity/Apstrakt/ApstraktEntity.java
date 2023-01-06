package com.example.yangiliklarwebsayti.Entity.Apstrakt;

import com.example.yangiliklarwebsayti.Entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@MappedSuperclass   // bir necha class larga birdaniga bog'lash
public abstract class ApstraktEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreatedBy
    private Integer  kimTomonidanYaratilgan;
    @LastModifiedBy
    private Integer kimTomonidanTahrirlangan;
    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp qachonYaratilganVaqt;
    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp qachonTahrirlanganVaqt;

}
