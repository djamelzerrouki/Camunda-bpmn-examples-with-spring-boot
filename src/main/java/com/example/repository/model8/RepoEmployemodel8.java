package com.example.repository.model8;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Employe;

@Repository
public interface RepoEmployemodel8 extends JpaRepository<Employe, Long>{

}
