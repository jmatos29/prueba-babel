package com.billet.gestiontareas;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {

    @org.junit.jupiter.api.Test
    public void test() {
        System.out.println(new BCryptPasswordEncoder().encode("password"));
    }
}
