package com.hameed.lms.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hameed.lms.entities.Book;
import com.hameed.lms.entities.Borrow;
import com.hameed.lms.entities.Patron;
import com.hameed.lms.exceptions.ResourceNotFoundException;
import com.hameed.lms.repositories.BookRepository;
import com.hameed.lms.repositories.BorrowRepository;
import com.hameed.lms.repositories.PatronRepository;

@Service
public class BorrowService {
	
	@Autowired
	private PatronRepository patronRepo;
	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private BorrowRepository borrowRepo;
	
	public boolean returnBook(Integer bookId,Integer patronId) {
		Optional<Book> book = bookRepo.findById(bookId);
		book.orElseThrow(() -> new ResourceNotFoundException("Book not found"));
		Optional<Patron> patron = patronRepo.findById(patronId);
		patron.orElseThrow(() -> new ResourceNotFoundException("Patron not found"));
		if (book.isPresent() && patron.isPresent()) {
			List<Borrow> borrowRef = borrowRepo.findByBookIdAndPatronId(bookId, patronId).stream().filter(b -> !b.getIsReturned()).toList();
			if (borrowRef.isEmpty()) throw new ResourceNotFoundException("Book is not borrowed by this patron");
			if(!borrowRef.isEmpty()) {
				Borrow borrow = borrowRef.get(0);
				if(borrow.getIsReturned()) throw new ResourceNotFoundException("Book is already returned by this patron");
				borrow.setReturnDate(new Date());
				borrow.setIsReturned(true);
				borrowRepo.save(borrow);
				return true;
			}
		}
		return false;
	}
	
	public boolean borrowBook(Integer bookId,Integer patronId) {
		Optional<Book> book = bookRepo.findById(bookId);
		book.orElseThrow(() -> new ResourceNotFoundException("Book not found"));
		Optional<Patron> patron = patronRepo.findById(patronId);
		patron.orElseThrow(() -> new ResourceNotFoundException("Patron not found"));
		
		if (book.isPresent() && patron.isPresent()) {
			List<Borrow> borrowRef = borrowRepo.findByBookIdAndPatronId(bookId, patronId).stream().filter(b -> !b.getIsReturned()).toList();
			if (!borrowRef.isEmpty()) throw new ResourceNotFoundException("Book is already borrowed by this patron");
			if (borrowRef.isEmpty()) {
				Borrow borrow = new Borrow();
				borrow.setBook(book.get());
				borrow.setPatron(patron.get());
				borrow.setIsReturned(false);
				borrow.setBorrowedDate(new Date());
				borrowRepo.save(borrow);
				return true;
			}
		}
		return false;
	}

}
