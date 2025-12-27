package com.NSABCorp.facenest.controller;


import com.NSABCorp.facenest.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private GreetingService serv;


    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World")String name)
    {
        return serv.greet(name);
    }
}
