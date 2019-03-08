package com.example.repository.model5;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.repository.model5.Dossier;

@Repository
public interface RepoDossiermodel5 extends JpaRepository<Dossier, Long>{

}
