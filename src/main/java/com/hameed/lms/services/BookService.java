package com.hameed.lms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hameed.lms.entities.Book;
import com.hameed.lms.exceptions.ResourceNotFoundException;
import com.hameed.lms.repositories.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepo;
	
	public Book addNewBook(Book book) {
		bookRepo.save(book);
		return book;
	}
	
	public List<Book> fetchAllBooks() {
		return bookRepo.findAll();
	}
	
	public Optional<Book> fetchBook(Integer id) {
		Optional<Book> book = bookRepo.findById(id);
		book.orElseThrow(() -> new ResourceNotFoundException("Book not found"));
		return book;
	}
	
	public Optional<Book> updateBook(Integer id,Book book) {
		Optional<Book> refBook = bookRepo.findById(id);
		refBook.orElseThrow(() -> new ResourceNotFoundException("Book not found"));
		if (refBook.isPresent()) {
			Book bookFromDB = refBook.get();
			bookFromDB.setTitle(book.getTitle());
			bookFromDB.setAuthor(book.getAuthor());
			bookFromDB.setIsbn(book.getIsbn());
			bookFromDB.setPubYear(book.getPubYear());
			bookRepo.save(bookFromDB);
			Optional.of(bookFromDB);
			return Optional.of(bookFromDB);
		} else {
			return refBook;
		}
	}
	
	public void removeBook(Integer id) {
		bookRepo.deleteById(id);
	}

}
