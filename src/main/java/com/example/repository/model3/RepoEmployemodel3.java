package com.example.repository.model3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Employe;

@Repository
public interface RepoEmployemodel3 extends JpaRepository<Employe, Long>{

}
