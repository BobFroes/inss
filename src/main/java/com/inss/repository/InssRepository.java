package com.inss.repository;

import com.inss.domain.Inss;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InssRepository extends JpaRepository<Inss, UUID> {

}
