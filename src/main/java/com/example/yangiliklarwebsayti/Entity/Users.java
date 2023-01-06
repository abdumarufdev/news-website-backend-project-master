package com.example.yangiliklarwebsayti.Entity;

import com.example.yangiliklarwebsayti.Entity.Apstrakt.ApstraktEntity;
import com.example.yangiliklarwebsayti.Entity.Enum.Xuquqlar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Users extends ApstraktEntity implements UserDetails {

    private String ismi;
    private String familyasi;
    private String username;
    private String password;

    private String emailkod;
    @ManyToOne
    private LavozimEntity lavozimEntity;


    private boolean accountNonExpired=true;
    private boolean accountNonLocked=true;
    private boolean credentialsNonExpired=true;
    private boolean enabled=false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {    // foydalanuvchini lovozimga xuquq;arni birlashtiradi
        List<Xuquqlar> xuquqlarList = this.lavozimEntity.getXuquqlarList();
        ArrayList<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        for (Xuquqlar xuquqlar : xuquqlarList) {
            grantedAuthorities.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {return xuquqlar.name();}
            });
        }
        return grantedAuthorities;
    }

    public Users(String ismi, String familyasi, String username, String password, LavozimEntity lavozimEntity, boolean enabled) {
        this.ismi = ismi;
        this.familyasi = familyasi;
        this.username = username;
        this.password = password;
        this.lavozimEntity = lavozimEntity;
        this.enabled = enabled;
    }

}
