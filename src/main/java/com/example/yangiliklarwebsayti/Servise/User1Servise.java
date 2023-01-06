package com.example.yangiliklarwebsayti.Servise;

import com.example.yangiliklarwebsayti.Dto.AypiRespons;
import com.example.yangiliklarwebsayti.Dto.User1Dto;
import com.example.yangiliklarwebsayti.Dto.UserDto;
import com.example.yangiliklarwebsayti.Entity.LavozimEntity;
import com.example.yangiliklarwebsayti.Entity.Users;
import com.example.yangiliklarwebsayti.Repozitary.LavozimRepozitary;
import com.example.yangiliklarwebsayti.Repozitary.UserRepozitary;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class User1Servise {
    @Autowired
    UserRepozitary userRepozitary;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    LavozimRepozitary lavozimRepozitary;
    public AypiRespons Joyla1(User1Dto user1Dto) {
        Optional<Users> byUsername = userRepozitary.findByUsername(user1Dto.getUsername());
        if (byUsername.isPresent()) {
            return new AypiRespons("bunday foydalanovchi allaqachon mavjuddd", false);
        }
        if (user1Dto.getPassword().equals(user1Dto.getRepassword())) {
            Users users = new Users();
            users.setIsmi(user1Dto.getIsmi());
            users.setFamilyasi(user1Dto.getFamilyasi());
            users.setUsername(user1Dto.getUsername());
            users.setPassword(passwordEncoder.encode(user1Dto.getPassword()));
            Optional<LavozimEntity> byNomi = lavozimRepozitary.findByNomi(user1Dto.getLavozim());
            if (!byNomi.isPresent()) {
                return new AypiRespons("Bunday lavozim yoq", false);
            }
            LavozimEntity lavozimEntity = byNomi.get();
            users.setLavozimEntity(lavozimEntity);
            users.setEnabled(true);
            userRepozitary.save(users);
            return new AypiRespons("Ma'lumot saqlandi", true);
        }
        return new AypiRespons("Parolllar mos kelmadi",false);
    }

    public AypiRespons Dalete(Integer id) {
        userRepozitary.deleteById(id);
        return new AypiRespons("o'chirildi",true);
    }

    public AypiRespons Uqish(Integer id) {
        Optional<LavozimEntity> byId = lavozimRepozitary.findById(id);
        if (byId.isPresent()){
            return  new AypiRespons(byId.toString(),true);
        }
        return new AypiRespons("bunday id mavjud emas",false);
    }




}
