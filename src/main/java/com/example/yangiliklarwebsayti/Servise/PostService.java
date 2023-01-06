package com.example.yangiliklarwebsayti.Servise;

import com.example.yangiliklarwebsayti.Dto.AypiRespons;
import com.example.yangiliklarwebsayti.Dto.LavozimDto;
import com.example.yangiliklarwebsayti.Dto.PostDto;
import com.example.yangiliklarwebsayti.Entity.Post;
import com.example.yangiliklarwebsayti.Repozitary.PostRepozitary;
import com.example.yangiliklarwebsayti.Repozitary.UserRepozitary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepozitary postRepozitary;
    @Autowired
    PasswordEncoder passwordEncoder;

    public AypiRespons joyla(PostDto postDto) {
        boolean b = postRepozitary.existsByTitile(postDto.getTitile());
        if (b){
            return  new AypiRespons("mavjud emas",false);
        }
        Post post=new Post();
        post.setTitile(postDto.getTitile());
        post.setText(postDto.getText());
        postRepozitary.save(post);
        return new AypiRespons("joylandi",true);
    }

    public AypiRespons deletepost(Integer id) {
        postRepozitary.findById(id);
        return new AypiRespons("O'chirildi",true);
    }

    public AypiRespons uqishpost(Integer id) {
        Optional<Post> byId = postRepozitary.findById(id);
        if (byId.isPresent()){
            return new AypiRespons(byId.toString(),true);
        }
        return new AypiRespons("Bunday id mavjud emas",false);
    }

    public AypiRespons uqishPostHam() {
        List<Post> all = postRepozitary.findAll();
        return new AypiRespons(all.toString(),true);
    }
}
