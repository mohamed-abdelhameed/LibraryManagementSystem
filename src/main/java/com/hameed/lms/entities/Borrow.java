package com.hameed.lms.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Borrow {
	
	@Id
	@GeneratedValue
	private Integer id;
	private Integer bookId;
	private Integer patronId;
	private Boolean isReturned;
	private Date borrowedDate;
	private Date returnDate;
}
