package com.example.repository.cartenational;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.SousDirection;

@Repository
public interface RepoSousDirectioncartenational extends JpaRepository<SousDirection, Long>{

}
