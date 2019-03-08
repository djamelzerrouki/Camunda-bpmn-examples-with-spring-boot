package com.example.repository.model3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.repository.model3.Dossier;

@Repository
public interface RepoDossiermodel3 extends JpaRepository<Dossier, Long>{

}
