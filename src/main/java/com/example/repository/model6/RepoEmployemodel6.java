package com.example.repository.model6;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Employe;

@Repository
public interface RepoEmployemodel6 extends JpaRepository<Employe, Long>{

}
