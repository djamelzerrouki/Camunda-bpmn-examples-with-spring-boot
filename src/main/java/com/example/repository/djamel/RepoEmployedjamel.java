package com.example.repository.djamel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Employe;

@Repository
public interface RepoEmployedjamel extends JpaRepository<Employe, Long>{

}
