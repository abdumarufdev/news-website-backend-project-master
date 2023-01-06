package com.example.yangiliklarwebsayti.Security;

import com.example.yangiliklarwebsayti.Servise.UserServise;
import com.example.yangiliklarwebsayti.Token.Filtr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Properties;
import java.util.logging.Filter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  // preAuthecyshin Mappingni ishlatish
public class Settings extends WebSecurityConfigurerAdapter {
    @Autowired
    UserServise userServise;
    @Autowired
    Filtr filtr;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(5);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
//                .antMatchers("/user1joyla").permitAll()
//                  .antMatchers("/user1joyla/kiritish").permitAll()
//                  .antMatchers("/joyla/bazagaJoylash").permitAll()
//                .antMatchers("/joyla/uqishid").permitAll()
//                .antMatchers("/joylaLavozim/").permitAll()
//                .antMatchers("/joylaLavozim/kiritish").permitAll()
//                .antMatchers("/joylaLavozim/uchirish/{id}").permitAll()
//                .antMatchers("/joylaLavozim/uqish").permitAll()
//                .antMatchers("/joylaLavozim/uqish").permitAll()
//                .antMatchers("/joylaLavozim/tahrirlash/{id}").permitAll()
//                .antMatchers("/user1joyla/dalete/{id}").permitAll()
//                .antMatchers("/user1joyla/uqish/{id}").permitAll()
                .antMatchers("/user1joyla/registration").permitAll()
                .antMatchers("/user1joyla/login").permitAll()
                .antMatchers("/user1joyla/emailtasdiqlash").permitAll()
//                .antMatchers("/kamentJoylash").permitAll()
//                .antMatchers("/kamentJoylash/kamentJ").permitAll()
              //  .antMatchers("/postt/joylasgPost").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        http
                .addFilterBefore(filtr, UsernamePasswordAuthenticationFilter.class);
    }

   @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("farruxzaitov7176@gmail.com");
        mailSender.setPassword("djoqhitrrqqchfsy");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }
   @Bean
   @Override
   protected AuthenticationManager authenticationManager() throws Exception {
       return super.authenticationManager();
   }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userServise)
                .passwordEncoder(passwordEncoder());
    }
}
