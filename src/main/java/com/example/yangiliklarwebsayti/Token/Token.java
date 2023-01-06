package com.example.yangiliklarwebsayti.Token;

import com.example.yangiliklarwebsayti.Entity.LavozimEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.DoubleStream;

@Component
public class Token {
    public String key = "qwert";

    public  String tokin(String Username, LavozimEntity lavozimEntity){
        Integer vaqt=60*60*100*24;
       Date date=new Date(System.currentTimeMillis()+vaqt);
        String tokin = Jwts
               .builder()
               .setSubject(Username)
                .setIssuedAt(new Date())
                .setExpiration(date)
                .claim("lavozimEntity",lavozimEntity)
                .signWith(SignatureAlgorithm.HS512,key)
                .compact();
       return tokin;
    }
    public String UsernameOlish(String auth){
        String subject = Jwts
                .parser()
                .setSigningKey(key)
                .parseClaimsJws(auth)
                .getBody()
                .getSubject();
        return subject;
    }



}
