package com.NSABCorp.facenest.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public String greet(String name)
    {
        return "Hello "+name;
    }
}
