package com.example.repository.mode3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Service;

@Repository
public interface RepoServicemode3 extends JpaRepository<Service, Long>{

}
