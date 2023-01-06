package com.example.yangiliklarwebsayti.Controller;

import com.example.yangiliklarwebsayti.Dto.AypiRespons;
import com.example.yangiliklarwebsayti.Dto.LavozimDto;
import com.example.yangiliklarwebsayti.Dto.User1Dto;
import com.example.yangiliklarwebsayti.Dto.UserDto;
import com.example.yangiliklarwebsayti.Servise.User1Servise;
import com.example.yangiliklarwebsayti.Servise.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user1joyla")
public class UserController {
    @Autowired
    User1Servise user1Servise;
    @Autowired
    UserServise userServise;
    @PreAuthorize(value = "hasAnyAuthority('ADDUSER')")
    @PostMapping("/kiritish")
    public HttpEntity<?> Joyla(@RequestBody User1Dto user1Dto){
        AypiRespons aypiRespons=user1Servise.Joyla1(user1Dto);
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
    @PreAuthorize(value = "hasAnyAuthority('DALETEUSER')")
    @DeleteMapping("/dalete/{id}")
    public HttpEntity<?> Delete(@PathVariable Integer id){
        AypiRespons aypiRespons=user1Servise.Dalete(id);
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }

    @GetMapping("/uqish/{id}")
    public HttpEntity<?> Uqish(@PathVariable Integer id){
        AypiRespons aypiRespons=user1Servise.Uqish(id);
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
    @PreAuthorize(value = "hasAnyAuthority('REDUSER')")
    @GetMapping("/korish")
    public HttpEntity<?> Korish(){
        AypiRespons apiResponse=userServise.korish();
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getXabar());
    }

    @PostMapping("/registration")
    public HttpEntity<?> UserReg(@RequestBody UserDto userDto){
        AypiRespons apiResponse = userServise.Registration(userDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getXabar());
    }
    @PostMapping("/login")
    public HttpEntity<?> Login(@RequestBody UserDto userDto){
        AypiRespons apiResponse = userServise.login(userDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getXabar());
    }
    @GetMapping("/emailtasdiqlash")
    public HttpEntity<?> Tasdiqlash(@RequestParam String useremail, @RequestParam String emailkod){
        AypiRespons apiResponse = userServise.faollashtirish(useremail,emailkod);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getXabar());
    }


}
