package com.distribuida.repository;

import com.distribuida.db.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface BookRepository
				extends MongoRepository<Book, String> {
}
