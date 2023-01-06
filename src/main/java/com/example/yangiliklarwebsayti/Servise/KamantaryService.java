package com.example.yangiliklarwebsayti.Servise;

import com.example.yangiliklarwebsayti.Dto.AypiRespons;
import com.example.yangiliklarwebsayti.Dto.KamantaryDto;
import com.example.yangiliklarwebsayti.Entity.Kamentary;
import com.example.yangiliklarwebsayti.Repozitary.KamentaryRepozitary;
import com.example.yangiliklarwebsayti.Repozitary.PostRepozitary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KamantaryService {
    @Autowired
    KamentaryRepozitary kamentaryRepozitary;
    @Autowired
    PostRepozitary postRepozitary;

    public AypiRespons kamentJoylashsh(KamantaryDto kamantaryDto) {
        boolean b = postRepozitary.existsById(kamantaryDto.getPostid().getId());
        if (!b)return new AypiRespons("id post mavjud emas ",false);
        Kamentary kamentary=new Kamentary();
        kamentary.setMatn(kamantaryDto.getMatn());
        kamentary.setPost(kamantaryDto.getPostid());
        kamentaryRepozitary.save(kamentary);
        return new AypiRespons("kament saqlandi",true);
    }

    public AypiRespons uqishHAmmasini() {
        List<Kamentary> all = kamentaryRepozitary.findAll();
        return new AypiRespons(all.toString(),true);
    }

    public AypiRespons uqishId(Integer id) {
        Optional<Kamentary> byId = kamentaryRepozitary.findById(id);
        if (byId.isPresent()){
            return new AypiRespons(byId.get().toString(),true);
        }
        return new AypiRespons("Bunday id mavjud emas iltimos boshqa id kiritib ko'ring",false);
    }

    public AypiRespons uchirish(Integer id) {
        try {
            kamentaryRepozitary.deleteById(id);
            return new AypiRespons("Ma'lumot muvaqiyatli o'chirildi",true);
        }
        catch (Exception a){
            return new AypiRespons("Uchirilmadi",false);
        }

    }
}
