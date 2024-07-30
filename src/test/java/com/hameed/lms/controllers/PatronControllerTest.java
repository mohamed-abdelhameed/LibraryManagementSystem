package com.hameed.lms.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hameed.lms.entities.Book;
import com.hameed.lms.entities.Patron;
import com.hameed.lms.services.BookService;
import com.hameed.lms.services.PatronService;

@WebMvcTest(PatronController.class)
@AutoConfigureMockMvc
class PatronControllerTest {

	@Autowired
    private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private PatronService patronService;

	@Test
	void testFetchAllPatrons() throws Exception {
		Patron patron = new Patron();
		patron.setAddress("Address");
		patron.setEmail("Email");
		patron.setName("Name");
		patron.setPhone("Phone");

		List<Patron> allPatrons = List.of(patron);

		given(patronService.fetchAllPatrons()).willReturn(allPatrons);

		mvc.perform(get("/api/patrons").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].email", is(patron.getEmail())));
	}

	@Test
	void testFetchPatron() throws Exception {

		Integer id = 1;
		
		Patron patron = new Patron();
		patron.setId(id);
		patron.setAddress("Address");
		patron.setEmail("Email");
		patron.setName("Name");
		patron.setPhone("Phone");

		given(patronService.fetchPatron(id)).willReturn(Optional.of(patron));

		mvc.perform(get("/api/patrons/"+id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(patron.getId().intValue())))
                .andExpect(jsonPath("$.email", is(patron.getEmail())));
	}

	@Test
	void testAddNewPatron() throws Exception {

		Integer id = 1;
		
		Patron patron = new Patron();
		patron.setId(id);
		patron.setAddress("Address");
		patron.setEmail("Email");
		patron.setName("Name");
		patron.setPhone("Phone");

		given(patronService.addNewPatron(patron)).willReturn(patron);

		mvc.perform(post("/api/patrons")
				.content(objectMapper.writeValueAsString(patron))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(patron.getId().intValue())))
                .andExpect(jsonPath("$.email", is(patron.getEmail())));
	}
	
	@Test
	void testUpdatePatron() throws Exception {

		Integer id = 1;
		
		Patron patron = new Patron();
		patron.setId(id);
		patron.setAddress("Address");
		patron.setEmail("Email");
		patron.setName("Name");
		patron.setPhone("Phone");

		given(patronService.updatePatron(id,patron)).willReturn(Optional.of(patron));

		mvc.perform(put("/api/patrons/"+id)
				.content(objectMapper.writeValueAsString(patron))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(patron.getId().intValue())))
                .andExpect(jsonPath("$.email", is(patron.getEmail())));
	}

	@Test
	void testDeletePatron() throws Exception {

		Integer id = 1;
		
		Patron patron = new Patron();
		patron.setId(id);
		patron.setAddress("Address");
		patron.setEmail("Email");
		patron.setName("Name");
		patron.setPhone("Phone");


		mvc.perform(delete("/api/patrons/"+id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
}
