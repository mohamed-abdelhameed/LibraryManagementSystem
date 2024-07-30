package com.hameed.lms.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Book {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String title;
	private String author;
	private Integer pubYear;
	private String isbn;
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
		name="Borrow",
		joinColumns = {@JoinColumn(name="bookId")},
		inverseJoinColumns = {@JoinColumn(name="patronId")}
	)
	Set<Patron> patrons=new HashSet<>();
}
