package com.example.repository.model4;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Historique;

@Repository
public interface RepoHistoriquemodel4 extends JpaRepository<Historique, Long>{

}
