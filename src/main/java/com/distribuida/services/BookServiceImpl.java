package com.distribuida.services;

import com.distribuida.author.AuthorsCliente;
import com.distribuida.db.Book;
import com.distribuida.dto.BookDto;
import com.distribuida.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class BookServiceImpl implements BookService {
String urlAuthor = "http://localhost:3002/authors";

 public BookServiceImpl() {
		Map<String, String> env = System.getenv();
		for (String envName : env.keySet()) {
			if (envName.equals("URL_AUTHOR")) {
				urlAuthor = env.get(envName);
			}
		}
		System.out.println("URL_AUTHOR**********: " + urlAuthor);
	}
CloseableHttpClient httpclient = HttpClients.createDefault();
	@Autowired
	private BookRepository bookRepository;
	
	public AuthorsCliente getAuthor(Integer id) {
		try {
			HttpGet httpGet = new HttpGet(urlAuthor + "/" + id);
			var response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			System.out.println(entity);
			ObjectMapper obtectMapper = new ObjectMapper();
			AuthorsCliente auu = obtectMapper.readValue(EntityUtils.toString(entity), AuthorsCliente.class);
			return auu;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<BookDto> findAll() {
		List<Book> books = bookRepository.findAll();
		List<BookDto> ret = books.stream().map(book -> {
			AuthorsCliente author = this.getAuthor(book.getAuthor());
			if (author == null) {
				author = new AuthorsCliente(
						0,
						"Sin nombre",
						"Sin apellido"
				);
			}
			return new BookDto(
					book.getId(),
					book.getIsbn(),
							book.getTitle(),
							book.getAuthor(),
							book.getPrice(),
							String.format("%s, %s", author.getLastName(), author.getFirstName())
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
			AuthorsCliente author = this.getAuthor(book.get().getAuthor());
			if (author == null) {
				author = new AuthorsCliente(
						0,
						"Sin nombre",
						"Sin apellido"
				);
			}
			BookDto ret = new BookDto(
					book.get().getId(),
					book.get().getIsbn(),
					book.get().getTitle(),
					book.get().getAuthor(),
					book.get().getPrice(),
					String.format("%s, %s", author.getLastName(), author.getFirstName())
			);
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
