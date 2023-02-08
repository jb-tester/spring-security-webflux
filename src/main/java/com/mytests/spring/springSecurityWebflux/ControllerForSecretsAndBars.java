package com.mytests.spring.springSecurityWebflux;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ControllerForSecretsAndBars {

    @GetMapping("/secret")
    public String secret() {
        return "secret";
    }

    @GetMapping("/bar")
    public String bar() {
        return "bar";
    }
}
