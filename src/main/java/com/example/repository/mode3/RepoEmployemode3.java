package com.example.repository.mode3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Employe;

@Repository
public interface RepoEmployemode3 extends JpaRepository<Employe, Long>{

}
