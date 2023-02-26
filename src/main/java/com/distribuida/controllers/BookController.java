package com.distribuida.controllers;

import com.distribuida.db.Book;
import com.distribuida.dto.BookDto;
import com.distribuida.services.BookService;
import com.distribuida.util.ObjectMapperUtils;
import jakarta.ws.rs.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bookService;
	@GetMapping("")
  public List<BookDto> findAll() throws IOException {
		System.out.println(ObjectMapperUtils.mapAll(bookService.findAll(), Book.class));
		return bookService.findAll();
	}
	@GetMapping("/{id}")
	public BookDto findById(@PathVariable("id") String id) {
		System.out.println(id);
		return bookService.findById(id).isPresent() ? bookService.findById(id).get() : null;
	}
	@PostMapping("")
	public  ResponseEntity<?> insert(@RequestBody Book book) {
		
		System.out.println(ObjectMapperUtils.map(book, Book.class));
		bookService.insert(book);
		return new ResponseEntity("Señadio correctamente", HttpStatus.OK);
	}
	@PutMapping("")
	public void update(Book book) {
		bookService.update(book);
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		bookService.delete(id);
	}

}
