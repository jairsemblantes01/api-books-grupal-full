package com.distribuida.services;

import com.distribuida.db.Book;
import com.distribuida.dto.BookDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BookService {
	List<BookDto> findAll( ) throws IOException;
	
	Optional<BookDto> findById(String id);
	
	void insert(Book book);
	
	void update( Book book);

void update(Book book, String id);

void delete(String id );
	
}
