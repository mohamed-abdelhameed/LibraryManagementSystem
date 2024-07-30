package com.hameed.lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hameed.lms.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
