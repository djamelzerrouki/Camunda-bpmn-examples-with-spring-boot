package com.example.repository.model2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Service;

@Repository
public interface RepoServicemodel2 extends JpaRepository<Service, Long>{

}
