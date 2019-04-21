package com.example.repository.cartenational;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.NextTask;

@Repository
public interface RepoNextTaskcartenational extends JpaRepository<NextTask, Long>{

}
