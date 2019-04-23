package com.example.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
 
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Service {
 
     
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;
    private String name;
 	@ManyToOne
 	private SousDirection sousdirection;	
 	@OneToMany(mappedBy="service")
	private Collection<MyTask> myTasks;
 	
}