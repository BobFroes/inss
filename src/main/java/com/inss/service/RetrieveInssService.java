package com.inss.service;


import com.inss.domain.Inss;
import com.inss.repository.InssRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RetrieveInssService {

    private final InssRepository repository;

    @Autowired
    public RetrieveInssService(InssRepository repository) {
        this.repository = repository;
    }

    public Optional<Inss> execute(UUID id) {
        return repository.findById(id);
    }
}
