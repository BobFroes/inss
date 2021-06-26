package com.inss.service;

import com.inss.domain.Inss;
import com.inss.repository.InssRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveInssService {

    private final InssRepository repository;

    @Autowired
    public SaveInssService(InssRepository repository) {
        this.repository = repository;
    }

    public Inss execute(Inss inss) {
        return repository.save(inss);
    }

}