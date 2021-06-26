package com.inss.service;

import com.inss.repository.InssRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DeleteInssService {

    private final InssRepository repository;

    @Autowired
    public DeleteInssService(InssRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id) {
        repository.findById(id).ifPresent(inss -> {
            inss.setDeletedAt(LocalDateTime.now());
            repository.save(inss);
        });
    }
}
