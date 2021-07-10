package com.inss.service;

import com.inss.domain.InssRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DeleteService {

    @Autowired
    private InssRepository repository;

    public void execute(UUID id) {
        repository.findById(id).ifPresent(inss -> {
            inss.setDeletedAt(LocalDateTime.now());
            repository.save(inss);
        });
    }
}
