package com.example.repository.model7;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Service;

@Repository
public interface RepoServicemodel7 extends JpaRepository<Service, Long>{

}
