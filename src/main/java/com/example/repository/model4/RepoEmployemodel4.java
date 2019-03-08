package com.example.repository.model4;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Employe;

@Repository
public interface RepoEmployemodel4 extends JpaRepository<Employe, Long>{

}
