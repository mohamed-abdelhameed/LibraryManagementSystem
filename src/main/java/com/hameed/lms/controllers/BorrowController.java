package com.hameed.lms.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hameed.lms.entities.Patron;
import com.hameed.lms.services.BorrowService;
import com.hameed.lms.services.PatronService;

@RestController
public class BorrowController {
	
	@Autowired
	private BorrowService borrowService;
	
	@PostMapping("/api/borrow/{bookId}/patron/{patronId}")
	public ResponseEntity<?> borrowBook(@PathVariable Integer bookId,@PathVariable Integer patronId){
		if(borrowService.borrowBook(bookId,patronId)) {
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/api/return/{bookId}/patron/{patronId}")
	public ResponseEntity<?> returnBook(@PathVariable Integer bookId,@PathVariable Integer patronId){
		if(borrowService.returnBook(bookId,patronId)) {
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
