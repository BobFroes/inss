package com.inss.service;

import com.inss.domain.Inss;
import com.inss.domain.InssRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveService {

    @Autowired
    private InssRepository repository;

    public Inss execute(Inss inss) {
        return repository.save(inss);
    }

}