package com.sonata.spring.boot.sample.service;

import java.util.List;

import com.sonata.spring.boot.sample.model.Book;

public interface IBasicApplicationService {

	Book findById(Long id);

	List<Book> findAll();

	Long addBookToList (Book book);

	void removeBookFromList (Long bookId);

}
