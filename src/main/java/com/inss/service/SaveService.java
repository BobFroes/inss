package com.inss.service;

import com.inss.domain.Inss;
import com.inss.domain.InssRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveService {

    private final InssRepository repository;

    @Autowired
    public SaveService(InssRepository repository) {
        this.repository = repository;
    }

    public Inss execute(Inss inss) {
        return repository.save(inss);
    }

}