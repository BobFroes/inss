package com.inss.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InssRepository extends JpaRepository<Inss, UUID> {
    Optional<Inss> findByIsCurrentTrue();
}
