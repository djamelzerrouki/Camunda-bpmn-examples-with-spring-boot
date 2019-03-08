package com.example.repository.mode3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Historique;

@Repository
public interface RepoHistoriquemode3 extends JpaRepository<Historique, Long>{

}
