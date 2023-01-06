package com.example.yangiliklarwebsayti.Controller;

import com.example.yangiliklarwebsayti.Dto.AypiRespons;
import com.example.yangiliklarwebsayti.Dto.KamantaryDto;
import com.example.yangiliklarwebsayti.Servise.KamantaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kamentJoylash")
public class KamantaryControllar {
    @Autowired
    KamantaryService kamantaryService;
    @PreAuthorize(value = "hasAnyAuthority('ADDKOMENT')")
    @PostMapping("/kamentJ")
    public HttpEntity<?> KamentJoylash(@RequestBody KamantaryDto kamantaryDto){
        AypiRespons aypiRespons=kamantaryService.kamentJoylashsh(kamantaryDto);
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
    @PreAuthorize(value = "hasAnyAuthority('REDKOMENT')")
    @GetMapping("/uqishham")
    public HttpEntity<?> Uqishhammasi(){
        AypiRespons aypiRespons=kamantaryService.uqishHAmmasini();
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
    @GetMapping("/uqish/{id}")
    public HttpEntity<?> uqishId(@PathVariable Integer id){
        AypiRespons aypiRespons=kamantaryService.uqishId(id);
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
    @PreAuthorize(value = "hasAnyAuthority('DALETEKOMENT')")
    @DeleteMapping("/delete")
    public HttpEntity<?> delete(@PathVariable Integer id){
        AypiRespons aypiRespons=kamantaryService.uchirish(id);
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
}
