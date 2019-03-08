package com.example.repository.model7;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Employe;

@Repository
public interface RepoEmployemodel7 extends JpaRepository<Employe, Long>{

}
