package com.example.repository.model6;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Historique;

@Repository
public interface RepoHistoriquemodel6 extends JpaRepository<Historique, Long>{

}
