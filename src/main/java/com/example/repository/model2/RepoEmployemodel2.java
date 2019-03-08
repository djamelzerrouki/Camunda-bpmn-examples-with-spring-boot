package com.example.repository.model2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Employe;

@Repository
public interface RepoEmployemodel2 extends JpaRepository<Employe, Long>{

}
