package com.example.repository.model5;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Employe;

@Repository
public interface RepoEmployemodel5 extends JpaRepository<Employe, Long>{

}
