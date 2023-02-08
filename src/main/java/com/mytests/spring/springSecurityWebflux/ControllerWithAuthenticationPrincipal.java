package com.mytests.spring.springSecurityWebflux;

import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerWithAuthenticationPrincipal {

    @GetMapping("/principal1")
    public String username(@AuthenticationPrincipal(expression = "@myPrincipalBean.username(#this)") String username) {
        System.out.println("**********************************");
        System.out.println(username);
        System.out.println("**********************************");
        return username;
    }
    @GetMapping("/principal2")
    public String getPrincipal(@CurrentSecurityContext(expression = "authentication.principal")
                               HttpExchange.Principal principal) {
        return principal.getName();
    }
}