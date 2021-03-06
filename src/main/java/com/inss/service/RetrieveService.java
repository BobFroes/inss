package com.inss.service;


import com.inss.domain.Inss;
import com.inss.domain.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RetrieveService {

    @Autowired
    private Repository repository;

    public Optional<Inss> execute(UUID id) {
        return repository.findById(id);
    }
}
