package com.faizan.smart.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Index {

    @GetMapping("")
    public ResponseEntity<String> index() {
       return ResponseEntity.ok("Welcome to my School");
    }
}
