package org.bnbc.services;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {
    public String greet(){
        return  "Hello Prema How are you?";
    }
    public String greet(String name){
        return  "Hello How are you? "+ name;
    }
}
