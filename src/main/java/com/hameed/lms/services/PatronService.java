package com.hameed.lms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hameed.lms.entities.Patron;
import com.hameed.lms.exceptions.ResourceNotFoundException;
import com.hameed.lms.repositories.PatronRepository;

@Service
public class PatronService {
	
	@Autowired
	private PatronRepository patronRepo;
	
	public Patron addNewPatron(Patron patron) {
		patronRepo.save(patron);
		return patron;
	}
	
	public List<Patron> fetchAllPatrons() {
		return patronRepo.findAll();
	}
	
	public Optional<Patron> fetchPatron(Integer id) {
		Optional<Patron> patron = patronRepo.findById(id);
		patron.orElseThrow(() -> new ResourceNotFoundException("Patron not found"));
		return patron;
	}
	
	public Optional<Patron> updatePatron(Integer id,Patron patron) {
		Optional<Patron> refPatron = patronRepo.findById(id);
		refPatron.orElseThrow(() -> new ResourceNotFoundException("Patron not found"));
		if (refPatron.isPresent()) {
			Patron patronFromDB = refPatron.get();
			patronFromDB.setName(patron.getName());
			patronFromDB.setAddress(patron.getAddress());
			patronFromDB.setPhone(patron.getPhone());
			patronFromDB.setEmail(patron.getEmail());
			patronRepo.save(patronFromDB);
			Optional.of(patronFromDB);
			return Optional.of(patronFromDB);
		} else {
			return refPatron;
		}
	}
	
	public void removePatron(Integer id) {
		patronRepo.deleteById(id);
	}

}
