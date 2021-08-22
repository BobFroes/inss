package com.inss.service;

import com.inss.domain.Inss;
import com.inss.domain.Repository;
import com.inss.exception.AlreadyExistingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveService {

    @Autowired
    private Repository repository;

    public Inss execute(Inss inss) {
        if (repository.findByYear(inss.getYear()).isPresent()) throw new AlreadyExistingException();

        return repository.save(inss);
    }

}