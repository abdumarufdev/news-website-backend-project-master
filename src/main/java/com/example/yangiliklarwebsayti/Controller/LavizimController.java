package com.example.yangiliklarwebsayti.Controller;

import com.example.yangiliklarwebsayti.AnatatsiyaYaratish.XuquqTekshirish;
import com.example.yangiliklarwebsayti.Dto.AypiRespons;
import com.example.yangiliklarwebsayti.Dto.LavozimDto;
import com.example.yangiliklarwebsayti.Entity.Apstrakt.BoshlangichYuklanish;
import com.example.yangiliklarwebsayti.Repozitary.LavozimRepozitary;
import com.example.yangiliklarwebsayti.Servise.LavozimSevise;
import com.example.yangiliklarwebsayti.Servise.UserServise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("joylaLavozim")
public class LavizimController {
    @Autowired
    LavozimSevise lavozimSevise;
    @PreAuthorize(value = "hasAnyAuthority('ADDLOVOZIM')")
    @PostMapping("kiritish")
    public HttpEntity<?> kiritamiz(@Valid @RequestBody LavozimDto lavozimDto){
        AypiRespons aypiRespons=lavozimSevise.joyla2(lavozimDto);
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
    @PreAuthorize(value = "hasAnyAuthority('DELETELAVOZIM')")
    @DeleteMapping("/uchirish/{id}")
    private HttpEntity<?> Uchir(@PathVariable Integer id){
        AypiRespons aypiRespons=lavozimSevise.uchirish1(id);
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
    @GetMapping("/uqish/{id}")
    private HttpEntity<?> uqidi(@PathVariable Integer id){
        AypiRespons aypiRespons=lavozimSevise.Uqish(id);
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
    @PreAuthorize(value = "hasAnyAuthority('RIDLOVOZIM')")
    @GetMapping("/uqishham")
    private HttpEntity<?> uqishhamma(){
        AypiRespons aypiRespons=lavozimSevise.uqishHamma();
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
    @PreAuthorize(value = "hasAnyAuthority('EDITLOVOZIM')")
    @PutMapping("/tahrirlash/{id}")
    public HttpEntity<?> tahrirlash(@PathVariable Integer id, @RequestBody LavozimDto lavozimDto){
        AypiRespons aypiRespons=lavozimSevise.Tahrirlash(id,lavozimDto);
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }

}




