package com.example.repository.ahmedhamid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Service;

@Repository
public interface RepoServiceahmedhamid extends JpaRepository<Service, Long>{

}
