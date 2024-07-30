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
import com.hameed.lms.services.PatronService;

@RestController
public class PatronController {
	
	@Autowired
	private PatronService patronService;
	
	@GetMapping("/api/patrons")
	public ResponseEntity<?> fetchAllPatrons(){
		List<Patron> patrons = patronService.fetchAllPatrons();		
		return ResponseEntity.ok(patrons);
		
	}
	
	@GetMapping("/api/patrons/{id}")
	public ResponseEntity<?> fetchPatron(@PathVariable Integer id){
		Optional<Patron> patron = patronService.fetchPatron(id);
		return ResponseEntity.of(patron);
	}
	
	@PostMapping("/api/patrons")
	public ResponseEntity<?> addNewPatron(@RequestBody Patron patron){
		patronService.addNewPatron(patron);
		return ResponseEntity.ok(patron);
	}
	
	@PutMapping("/api/patrons/{id}")
	public ResponseEntity<?> updatePatron(@PathVariable Integer id,@RequestBody Patron patron){
		Optional<Patron> updatedPatron = patronService.updatePatron(id,patron);
		return ResponseEntity.of(updatedPatron);
	}
	
	@DeleteMapping("/api/patrons/{id}")
	public ResponseEntity<?> removePatron(@PathVariable Integer id){
		patronService.removePatron(id);
		return ResponseEntity.ok().build();
	}
	
}
