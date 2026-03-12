package com.example.bioskopserver.repository;

import com.example.bioskopserver.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
