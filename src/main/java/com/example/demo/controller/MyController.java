package com.example.demo.controller;

import com.example.demo.service.MyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/string")
public class MyController {
    private final MyService service;

    @PostMapping("/store")
    public Integer storeString(@RequestBody String s) {
        return service.storeString(s);
    }

    @GetMapping("/hello")
    public String getHello() {
        return "Hello";
    }

    @GetMapping("/double")
    public String getDoubledString(@RequestBody String s) {
        return s.repeat(2);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getStoredString(@PathVariable Integer id) {
        Optional<String> optional = service.getStoredString(id);
        return optional.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
