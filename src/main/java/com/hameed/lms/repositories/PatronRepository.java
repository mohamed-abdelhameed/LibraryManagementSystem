package com.hameed.lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hameed.lms.entities.Book;
import com.hameed.lms.entities.Patron;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Integer> {
}
