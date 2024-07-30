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

import com.hameed.lms.entities.Book;
import com.hameed.lms.services.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/api/books")
	public ResponseEntity<?> fetchAllBooks(){
		List<Book> books = bookService.fetchAllBooks();		
		return ResponseEntity.ok(books);
		
	}
	
	@GetMapping("/api/books/{id}")
	public ResponseEntity<?> fetchBook(@PathVariable Integer id){
		Optional<Book> book = bookService.fetchBook(id);
		return ResponseEntity.of(book);
	}
	
	@PostMapping("/api/books")
	public ResponseEntity<?> addNewBook(@RequestBody Book book){
		bookService.addNewBook(book);
		return ResponseEntity.ok(book);
	}
	
	@PutMapping("/api/books/{id}")
	public ResponseEntity<?> updateBook(@PathVariable Integer id,@RequestBody Book book){
		Optional<Book> updatedBook = bookService.updateBook(id,book);
		return ResponseEntity.of(updatedBook);
	}
	
	@DeleteMapping("/api/books/{id}")
	public ResponseEntity<?> removeBook(@PathVariable Integer id){
		bookService.removeBook(id);
		return ResponseEntity.ok().build();
	}
	
}
