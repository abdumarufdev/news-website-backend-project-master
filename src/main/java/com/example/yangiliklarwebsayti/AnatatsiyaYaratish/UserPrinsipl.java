package com.example.yangiliklarwebsayti.AnatatsiyaYaratish;

import com.example.yangiliklarwebsayti.Entity.Users;
import jdk.internal.org.xml.sax.ContentHandler;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserPrinsipl {
    @Before(value = "@annotation(xuquqTekshirish)")
    public String xuquqTek(XuquqTekshirish xuquqTekshirish){
        Users users = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for (GrantedAuthority authority : users.getAuthorities()) {
            boolean holat=false;
            if (authority.getAuthority().equals(xuquqTekshirish.xuquq())){
                holat=true;
                break;
            }
            if (!holat){
                return "Bu yo'lga ruxsat yoq";
            }
        }
        return "Yo'lga ruxsat berildi";
    }

}
