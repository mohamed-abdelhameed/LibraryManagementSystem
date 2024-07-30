package com.hameed.lms.controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.hameed.lms.services.BorrowService;

@WebMvcTest(BorrowController.class)
@AutoConfigureMockMvc
public class BorrowControllerTest {
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private BorrowService borrowService;
	
	@Test
	void testBorrowBook() throws Exception {
		Integer bookId=1;
		Integer patronId=1;

		given(borrowService.borrowBook(bookId,patronId)).willReturn(true);

		mvc.perform(post("/api/borrow/"+bookId+"/patron/"+patronId)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	void testReturnBook() throws Exception {
		Integer bookId=1;
		Integer patronId=1;

		given(borrowService.returnBook(bookId,patronId)).willReturn(true);

		mvc.perform(put("/api/return/"+bookId+"/patron/"+patronId)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
}
