package com.example.yangiliklarwebsayti.Controller;

import com.example.yangiliklarwebsayti.Dto.AypiRespons;
import com.example.yangiliklarwebsayti.Dto.UserDto;
import com.example.yangiliklarwebsayti.Entity.Users;
import com.example.yangiliklarwebsayti.Servise.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

//import javax.xml.ws.Service;
import java.security.Security;
import java.util.List;

@RestController
@RequestMapping("/joyla")
public class Controlller {
    @Autowired
    UserServise userServise;
    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping("/bazagaJoylash")
    public HttpEntity<?> Joylash(@RequestBody UserDto userDto){
    AypiRespons aypiRespons=userServise.Joyla(userDto);
    return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }

    @GetMapping("/uqish/{id}")
    public HttpEntity<?> uqiId(@PathVariable Integer id){
        AypiRespons aypiRespons=userServise.uqish(id);
        return  ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
    @GetMapping("/uqishHammasini")
    public HttpEntity<?> uqishhammasini(){
        AypiRespons aypiRespons=userServise.uqishHammasini();
        return  ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }



}
