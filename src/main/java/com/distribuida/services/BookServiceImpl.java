package com.distribuida.services;

import com.distribuida.author.AuthorConection;
import com.distribuida.author.AuthorsCliente;
import com.distribuida.db.Book;
import com.distribuida.dto.BookDto;
import com.distribuida.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookServiceImpl implements BookService {


	@Autowired
	private BookRepository bookRepository;
	@Autowired
	AuthorConection authorConection;
	
	@Override
	public List<BookDto> findAll() {
		List<Book> books = bookRepository.findAll();
		List<BookDto> ret = books.stream().map(book -> {
			String nameAuthor = authorConection.getAuthor(book.getAuthor());
			return new BookDto(
					book.getId(),
					book.getIsbn(),
							book.getTitle(),
							book.getAuthor(),
							book.getPrice(),
							nameAuthor
			);
		}).collect(Collectors.toList());
		
		return ret;
	}
	
	@Override
	public Optional<BookDto> findById(String id) {
		Optional<Book> book = bookRepository.findById(id);
		if (book.isEmpty()) {
			return Optional.empty();
		} else {
			String nameAuthor = authorConection.getAuthor(book.get().getAuthor());
			BookDto ret = new BookDto(
					book.get().getId(),
					book.get().getIsbn(),
					book.get().getTitle(),
					book.get().getAuthor(),
					book.get().getPrice(),
							nameAuthor);
			return Optional.of(ret);
		}
	}
	
	@Override
	public void insert(@RequestBody Book book) {
		bookRepository.save(book);
	}

@Override
public void update(Book book) {
	bookRepository.save(book);

}


@Override
public void update(Book book, String id) {
	bookRepository.save(book);

}

@Override
	public void delete(String id) {
		bookRepository.deleteById(id);
	}
}
