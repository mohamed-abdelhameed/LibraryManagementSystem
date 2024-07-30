package com.hameed.lms.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Borrow {
	
	@Id
	@GeneratedValue
	private Integer id;
	private Boolean isReturned;
	private Date borrowedDate;
	private Date returnDate;
	@ManyToOne
	@JoinColumn(name="book_id",nullable=false)
	private Book book;
	@ManyToOne
	@JoinColumn(name="patron_id",nullable=false)
	private Patron patron;
}
