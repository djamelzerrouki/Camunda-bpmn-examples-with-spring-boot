package com.example.repository.model7;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.repository.model7.Dossier;

@Repository
public interface RepoDossiermodel7 extends JpaRepository<Dossier, Long>{

}
