package com.example.repository.cartenational;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.MyTask;

@Repository
public interface RepoMyTaskcartenational extends JpaRepository<MyTask, Long>{

}
