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
import com.hameed.lms.services.BookService;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
class BookControllerTest {

	@Autowired
    private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private BookService bookService;

	@Test
	void testFetchAllBooks() throws Exception {
		Book book = new Book();
		book.setAuthor("Author");
		book.setIsbn("ISBN");
		book.setPubYear(2024);
		book.setTitle("Book");

		List<Book> allBooks = List.of(book);

		given(bookService.fetchAllBooks()).willReturn(allBooks);

		mvc.perform(get("/api/books").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].title", is(book.getTitle())));
	}

	@Test
	void testFetchBook() throws Exception {

		Integer id = 1;
		
		Book book = new Book();
        book.setId(1);
        book.setAuthor("Author");
        book.setIsbn("ISBN");
        book.setPubYear(2024);
        book.setTitle("Book");

		given(bookService.fetchBook(id)).willReturn(Optional.of(book));

		mvc.perform(get("/api/books/"+id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(book.getId().intValue())))
                .andExpect(jsonPath("$.title", is(book.getTitle())));
	}

	@Test
	void testAddNewBook() throws Exception {

		Integer id = 1;
		
		Book book = new Book();
        book.setId(1);
        book.setAuthor("Author");
        book.setIsbn("ISBN");
        book.setPubYear(2024);
        book.setTitle("Book");

		given(bookService.addNewBook(book)).willReturn(book);

		mvc.perform(post("/api/books")
				.content(objectMapper.writeValueAsString(book))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(book.getId().intValue())))
                .andExpect(jsonPath("$.title", is(book.getTitle())));
	}
	
	@Test
	void testUpdateBook() throws Exception {

		Integer id = 1;
		
		Book book = new Book();
        book.setId(1);
        book.setAuthor("Author");
        book.setIsbn("ISBN");
        book.setPubYear(2024);
        book.setTitle("Book");

		given(bookService.updateBook(id,book)).willReturn(Optional.of(book));

		mvc.perform(put("/api/books/"+id)
				.content(objectMapper.writeValueAsString(book))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(book.getId().intValue())))
                .andExpect(jsonPath("$.title", is(book.getTitle())));
	}

	@Test
	void testDeleteBook() throws Exception {

		Integer id = 1;
		
		Book book = new Book();
        book.setId(1);
        book.setAuthor("Author");
        book.setIsbn("ISBN");
        book.setPubYear(2024);
        book.setTitle("Book");


		mvc.perform(delete("/api/books/"+id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
}
