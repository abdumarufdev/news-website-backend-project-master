package com.example.yangiliklarwebsayti.AnatatsiyaYaratish;

import java.lang.annotation.*;

@Documented
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface XuquqTekshirish {
    String xuquq();
}
