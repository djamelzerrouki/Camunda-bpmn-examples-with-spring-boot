package com.example.repository.model5;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Historique;

@Repository
public interface RepoHistoriquemodel5 extends JpaRepository<Historique, Long>{

}
