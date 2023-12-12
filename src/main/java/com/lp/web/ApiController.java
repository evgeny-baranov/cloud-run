package com.lp.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Value("sm://test-secret")
    private String secret;

    @GetMapping("/test")
    public String getTest() {
        return this.secret;
    }
}
