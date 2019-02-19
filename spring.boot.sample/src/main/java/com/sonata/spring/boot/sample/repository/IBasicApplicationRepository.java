package com.sonata.spring.boot.sample.repository;

import java.util.List;

import com.sonata.spring.boot.sample.model.Book;

public interface IBasicApplicationRepository {

	Book findById (Long id);

	List<Book> findAll ();

	Long addBookToList (Book book);

	void removeBookFromList (Long bookId);

}
