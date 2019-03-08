package com.example.repository.model5;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Service;

@Repository
public interface RepoServicemodel5 extends JpaRepository<Service, Long>{

}
