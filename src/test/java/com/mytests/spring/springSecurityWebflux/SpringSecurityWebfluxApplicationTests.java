package com.mytests.spring.springSecurityWebflux;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.server.SecurityWebFilterChain;

@SpringBootTest
class SpringSecurityWebfluxApplicationTests {

    @Qualifier("adminHttpSecurity")
    @Autowired
    SecurityWebFilterChain securityWebFilterChain;
    @Test
    void contextLoads() {
    }

}
