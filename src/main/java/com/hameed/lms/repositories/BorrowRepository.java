package com.hameed.lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hameed.lms.entities.Book;
import com.hameed.lms.entities.Borrow;
import com.hameed.lms.entities.Patron;
import java.util.List;
import java.util.Optional;


@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
	public List<Borrow> findByBookIdAndPatronId(Integer bookId,Integer patronId);
}
