package com.example.repository.model2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Historique;

@Repository
public interface RepoHistoriquemodel2 extends JpaRepository<Historique, Long>{

}
