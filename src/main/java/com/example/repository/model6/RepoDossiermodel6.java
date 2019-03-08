package com.example.repository.model6;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.repository.model6.Dossier;

@Repository
public interface RepoDossiermodel6 extends JpaRepository<Dossier, Long>{

}
