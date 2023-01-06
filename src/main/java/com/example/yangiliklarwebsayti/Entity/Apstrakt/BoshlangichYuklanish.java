package com.example.yangiliklarwebsayti.Entity.Apstrakt;

import com.example.yangiliklarwebsayti.Entity.Enum.Xuquqlar;
import com.example.yangiliklarwebsayti.Entity.LavozimEntity;
import com.example.yangiliklarwebsayti.Entity.Users;
import com.example.yangiliklarwebsayti.Repozitary.LavozimEntityRepozitary;
import com.example.yangiliklarwebsayti.Repozitary.UserRepozitary;
import lombok.Data;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.yangiliklarwebsayti.Entity.Enum.Xuquqlar.*;

@Data
@Component
public class BoshlangichYuklanish implements CommandLineRunner {    // dastur ishga tushgandan birinchi shu ishlaydi
 @Value(value = "${spring.sql.init.mode}")
   String holatt;

   @Autowired
    LavozimEntityRepozitary lavozimEntityRepozitary;
   @Autowired
    UserRepozitary userRepozitary;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
       if (holatt.equals("always")){
           Xuquqlar[] xuquqlars = Xuquqlar.values();
           LavozimEntity admin = lavozimEntityRepozitary.save(new LavozimEntity(
                   LavozimConsdata.ADMIN, Arrays.asList(xuquqlars),
                   "Admin"
           ));
           lavozimEntityRepozitary.save(new LavozimEntity(
                   //  LavozimConsdata.USER,Arrays.asList(Xuquqlar.ADDKOMENT,Xuquqlar.EDITMAYCOMENT,Xuquqlar.REDKOMENT,Xuquqlar.DELETEMYKOMENT,REDPOST)
                   LavozimConsdata.USER,Arrays.asList(ADDKOMENT,EDITMAYCOMENT,REDPOST,DELETEMYKOMENT,REDKOMENT),
                   "User"
           ));
           userRepozitary.save(new Users(
                   "Admin","asdfg","fa6@gmail.com",passwordEncoder.encode("farrux2003"),admin,true
           ));
           userRepozitary.save(new Users(
                   "farrux","zaitov","uuuu@gnail.com",passwordEncoder.encode("farrux2003"),admin,true
           ));
       }
    }
}
