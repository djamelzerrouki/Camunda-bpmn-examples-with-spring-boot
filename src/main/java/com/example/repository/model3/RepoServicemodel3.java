package com.example.repository.model3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Service;

@Repository
public interface RepoServicemodel3 extends JpaRepository<Service, Long>{

}
