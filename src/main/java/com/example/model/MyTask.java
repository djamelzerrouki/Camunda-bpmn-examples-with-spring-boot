package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MyTask {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	private String idtask;
	private String name;
	private String type;
	@ManyToOne
	private Service service;
	@OneToMany(mappedBy="mytask")
	private Collection<NextTask> nexttasks;
	@OneToMany(mappedBy="mytask")
	private Collection<Employe> employes;

}
