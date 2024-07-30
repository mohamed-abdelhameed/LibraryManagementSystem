package com.hameed.lms.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Patron {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String address;
	private String phone;
	private String email;
	@OneToMany(mappedBy = "patron")
	Set<Borrow> borrows;
	
}
