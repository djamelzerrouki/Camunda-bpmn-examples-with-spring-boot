package com.example.repository.mode3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.repository.mode3.Dossier;

@Repository
public interface RepoDossiermode3 extends JpaRepository<Dossier, Long>{

}
