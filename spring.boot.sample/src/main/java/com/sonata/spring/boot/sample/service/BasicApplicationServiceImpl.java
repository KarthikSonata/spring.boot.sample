package com.sonata.spring.boot.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonata.spring.boot.sample.model.Book;
import com.sonata.spring.boot.sample.repository.IBasicApplicationRepository;

@Service
public class BasicApplicationServiceImpl implements IBasicApplicationService {
	
	@Autowired
	private IBasicApplicationRepository basicApplicationRepository;

	@Override
	public Book findById (Long id) {
		try {
			return id == null ? null : basicApplicationRepository.findById(id);
		} catch (RuntimeException ex) {
			throw ex;
		}
	}
	
	@Override
	public List<Book> findAll () {
		try {
			return basicApplicationRepository.findAll();
		} catch (RuntimeException ex) {
			throw ex;
		}
	}
	
	@Override
	public Long addBookToList (Book book) {
		try {
			return basicApplicationRepository.addBookToList(book);
		} catch (RuntimeException ex) {
			throw ex;
		}
	}
	
	@Override
	public void removeBookFromList (Long bookId) {
		try {
			basicApplicationRepository.removeBookFromList(bookId);
		} catch (RuntimeException ex) {
			throw ex;
		}
	}
}
