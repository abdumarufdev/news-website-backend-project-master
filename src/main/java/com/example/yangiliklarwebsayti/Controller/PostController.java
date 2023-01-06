package com.example.yangiliklarwebsayti.Controller;

import com.example.yangiliklarwebsayti.AnatatsiyaYaratish.XuquqTekshirish;
import com.example.yangiliklarwebsayti.Dto.AypiRespons;
import com.example.yangiliklarwebsayti.Dto.LavozimDto;
import com.example.yangiliklarwebsayti.Dto.PostDto;
import com.example.yangiliklarwebsayti.Servise.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/postt")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/joylasgPost")
    @XuquqTekshirish(xuquq = "ADDPOST")
    public HttpEntity<?> Kiritamiz( @RequestBody PostDto postDto){
        AypiRespons aypiRespons=postService.joyla(postDto);
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
    @PreAuthorize(value = "hasAnyAuthority('DALETEPOST')")
    @DeleteMapping("/DeletePost/{id}")
    public HttpEntity<?> DeletePst(@PathVariable Integer id){
        AypiRespons aypiRespons=postService.deletepost(id);
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
    @GetMapping("/uqish/{id}")
    public HttpEntity<?> UqishPost(@PathVariable Integer id){
        AypiRespons aypiRespons=postService.uqishpost(id);
        return  ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
    @PreAuthorize(value = "hasAnyAuthority('REDPOST')")
    @GetMapping("/uqishHammasini")
    public HttpEntity<?> uqishHammasini(){
         AypiRespons aypiRespons=postService.uqishPostHam();
        return  ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());

    }



}
