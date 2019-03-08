package com.example.repository.model4;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.repository.model4.Dossier;

@Repository
public interface RepoDossiermodel4 extends JpaRepository<Dossier, Long>{

}
