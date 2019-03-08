package com.example.repository.model8;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.repository.model8.Dossier;

@Repository
public interface RepoDossiermodel8 extends JpaRepository<Dossier, Long>{

}
