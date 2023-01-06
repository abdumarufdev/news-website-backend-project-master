package com.example.yangiliklarwebsayti.Servise;

import com.example.yangiliklarwebsayti.Dto.AypiRespons;
import com.example.yangiliklarwebsayti.Dto.LavozimDto;
import com.example.yangiliklarwebsayti.Entity.Apstrakt.BoshlangichYuklanish;
import com.example.yangiliklarwebsayti.Entity.LavozimEntity;
import com.example.yangiliklarwebsayti.Repozitary.LavozimRepozitary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class LavozimSevise {
    @Autowired
    LavozimRepozitary lavozimRepozitary;
    @Autowired
    LavozimSevise lavozimSevise;
    public AypiRespons joyla2(LavozimDto lavozimDto) {
        Optional<LavozimEntity> byNomi = lavozimRepozitary.findByNomi(lavozimDto.getNomi());
        if (byNomi.isPresent()){
            return new AypiRespons("Bunday Lavozim mavjud",false);
        }
        LavozimEntity lavozimEntity =new LavozimEntity();
        lavozimEntity.setNomi(lavozimDto.getNomi());
        lavozimEntity.setIzoh(lavozimDto.getIzoh());
        lavozimEntity.setXuquqlarList(lavozimDto.getXuquqlarList());
        lavozimRepozitary.save(lavozimEntity);
        return new AypiRespons("Lavozim qo'shildi",true);
    }
    public AypiRespons Uqish(Integer id) {
        Optional<LavozimEntity> byId = lavozimRepozitary.findById(id);
        if (byId.isPresent()){
            return new AypiRespons(byId.toString(),true);
        }
        return new AypiRespons("Bunday id mavjud emas",false);
    }
    public AypiRespons Tahrirlash(Integer id, LavozimDto lavozimDto) {
        Optional<LavozimEntity> byId = lavozimRepozitary.findById(id);
        LavozimEntity lavozimEntity=new LavozimEntity();
        if (byId.isPresent()){
           lavozimEntity.setNomi(lavozimEntity.getNomi());
        }
        else {
            lavozimEntity.setIzoh

                    (lavozimDto.getIzoh());
        }
        if (byId.isPresent()){
            lavozimEntity.setXuquqlarList(lavozimEntity.getXuquqlarList());
        }else {
            lavozimEntity.setXuquqlarList(lavozimDto.getXuquqlarList());
        }
        lavozimRepozitary.save(lavozimEntity);
        return new AypiRespons("taxrirlandi",true);
    }

    public AypiRespons uqishHamma() {
        List<LavozimEntity> all = lavozimRepozitary.findAll();
        return new AypiRespons(all.toString(),true);
    }
    public AypiRespons uchirish1(Integer id) {
        lavozimRepozitary.deleteById(id);
        return new AypiRespons("o'chirildi",true);
    }
}
