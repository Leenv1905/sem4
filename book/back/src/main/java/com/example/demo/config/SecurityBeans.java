package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
// có thể khai báo @Bean cho PasswordEncoder ở SecurityConfig
// Tuy nhiên để mở rộng thì nên tách riêng
// Miễn là nă trong 1 @Configuration là được
@Configuration
public class SecurityBeans {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
