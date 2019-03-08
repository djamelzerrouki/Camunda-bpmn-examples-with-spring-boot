package com.example.repository.model8;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Service;

@Repository
public interface RepoServicemodel8 extends JpaRepository<Service, Long>{

}
