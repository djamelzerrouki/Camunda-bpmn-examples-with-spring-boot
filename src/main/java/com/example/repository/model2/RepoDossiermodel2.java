package com.example.repository.model2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.repository.model2.Dossier;

@Repository
public interface RepoDossiermodel2 extends JpaRepository<Dossier, Long>{

}
