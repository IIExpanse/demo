package com.example.demo.service;

import com.example.demo.repository.MyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyService {
    private final MyRepository repository;

    public int storeString(String s) {
        return repository.storeString(s);
    }

    public Optional<String> getStoredString(int id) {
        return repository.getStoredString(id);
    }
}
