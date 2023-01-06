package com.example.yangiliklarwebsayti.Servise;

import com.example.yangiliklarwebsayti.Dto.AypiRespons;
import com.example.yangiliklarwebsayti.Dto.UserDto;
import com.example.yangiliklarwebsayti.Entity.Apstrakt.LavozimConsdata;
import com.example.yangiliklarwebsayti.Entity.Users;
import com.example.yangiliklarwebsayti.Repozitary.*;
import com.example.yangiliklarwebsayti.Token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServise implements UserDetailsService {
    @Autowired
    UserRepozitary userRepozitary;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    LavozimEntityRepozitary lavozimEntityRepozitary;
    @Autowired
    Token token;
    @Autowired
    JavaMailSender getJavaMailSender;

    @Autowired
    AuthenticationManager authenticationManager;


    public AypiRespons Joyla(UserDto userDto) {
       boolean b=userRepozitary.existsByUsername(userDto.getUsername());
       if (b){
           return new AypiRespons("bunday foydalanovchi allaqachon mavjuddd",false);
       }
     if (userDto.getPassword().equals(userDto.getRepassword())){
         Users users=new Users();
         users.setIsmi(userDto.getIsmi());
         users.setFamilyasi(userDto.getFamilyasi());
         users.setUsername(userDto.getUsername());
         users.setPassword(userDto.getPassword());
         users.setLavozimEntity(lavozimEntityRepozitary.findByNomi(LavozimConsdata.USER).get());

         users.setEnabled(true);
         userRepozitary.save(users);
         return new AypiRespons("Ma'lumot saqlandi",true);
     }
        return new AypiRespons("Parolllar mos kelmadi",false);
    }

    public AypiRespons uqish(Integer id) {
      return null;
    }


    public AypiRespons Registration(UserDto userDto) {
        boolean b = userRepozitary.existsByUsername(userDto.getUsername());
        if (b) {
            return new AypiRespons("bunday foydalanovchi allaqachon mavjud", false);
        }
        if (userDto.getPassword().equals(userDto.getRepassword())) {

            Users users = new Users();
            users.setIsmi(userDto.getIsmi());
            users.setFamilyasi(userDto.getFamilyasi());
            users.setUsername(userDto.getUsername());
            users.setPassword(passwordEncoder.encode(userDto.getPassword()));
            users.setEmailkod(UUID.randomUUID().toString().substring(0, 6));
            users.setLavozimEntity(lavozimEntityRepozitary.findByNomi(LavozimConsdata.USER).get());
            boolean xabaryuborish = Xabaryuborish(users.getUsername(), users.getEmailkod());
            if (xabaryuborish) {
                users.setEnabled(true);
                userRepozitary.save(users);
                return new AypiRespons("hisobni faollashtirish kodi email hisobingizga yuborildi", true);
            }
        }
        return new AypiRespons("Ro'yxatdan o'tmadiz, malumotlaringizni tekshirib qayta kiritinng!",false);
    }

    public boolean Xabaryuborish(String email,String emailkod){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("farruxzaitov7176@gmail.com");
            mailMessage.setTo(email);
            mailMessage.setSubject("Confirmation email");
            mailMessage.setText("<a href='http://localhost:8080/user1joyla/emailtasdiqlash?useremail="+email+"&emailkod="+emailkod+"'>hisobni tasdiqlash</a>");
            getJavaMailSender.send(mailMessage);
            return true;
        }
        catch (Exception e){
            e.getStackTrace();
        }
        return false;
    }
    public AypiRespons faollashtirish(String useremail, String emailkod) {
        Optional<Users> byUsernameAndEmailKod = userRepozitary.findByUsernameAndEmailkod(useremail, emailkod);
        if(byUsernameAndEmailKod.isPresent()){
            Users users = byUsernameAndEmailKod.get();
            users.setEnabled(true);
            users.setEmailkod(null);
            userRepozitary.save(users);
            return new AypiRespons("Hisobingiz faollashtirildi!", true);
        }
        return new AypiRespons("Akkauntingiz allaqachon faolashtirilmadi!",false);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> byUsername = userRepozitary.findByUsername(username);
        if (byUsername.isPresent()){
            return byUsername.get();
        }
        return (UserDetails) new UsernameNotFoundException("username topilmadi");
    }

    public AypiRespons login(UserDto loginDto) {
        Optional<Users> byUsername = userRepozitary.findByUsername(loginDto.getUsername());
        try {
           if (byUsername.isPresent()){
               Users users=new Users();
               if(users.getEmailkod()==null){
                   Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
                   Users principal = (Users) authenticate.getPrincipal();
                   String s = token.tokin(principal.getUsername(), principal.getLavozimEntity());
                   return new AypiRespons("profilga xush kelibsiz!" + s,true);
               }
               return new AypiRespons("Siz akavutinggizni faollashtiring",true);
           }
           return new AypiRespons("User name yoq",false);
        }
        catch (Exception e) {
            return new AypiRespons("login yoki parol xato", false);
        }
    }

    public AypiRespons korish() {
        List<Users> all = userRepozitary.findAll();
        return new AypiRespons(all.toString(),true);
    }

    public AypiRespons uqishHammasini() {
        List<Users> all = userRepozitary.findAll();
        return new AypiRespons(all.toString(),true);
    }
}
