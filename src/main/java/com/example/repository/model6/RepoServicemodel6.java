package com.example.repository.model6;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Service;

@Repository
public interface RepoServicemodel6 extends JpaRepository<Service, Long>{

}
