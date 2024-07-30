package com.hameed.lms.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	@OneToMany(mappedBy = "book")
	Set<Borrow> borrows;
}
