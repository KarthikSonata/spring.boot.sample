package com.sonata.spring.boot.sample;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.sonata.spring.boot.sample.model.Book;
import com.sonata.spring.boot.sample.model.Chapter;
import com.sonata.spring.boot.sample.repository.BasicApplicationRepositoryImpl;

public class JUnitTestRepository extends ApplicationTests {

	@Autowired
	private BasicApplicationRepositoryImpl basicApplicationRepositoryImpl;
	
	@Test
	public void findByIdPos () {
		System.out.println("Control in findByIdPos method.");
		Book book = basicApplicationRepositoryImpl.findById(1L);
		System.out.println(book);
	}
	
	@Test
	public void findByIdNeg () {
		System.out.println("Control in findByIdNeg method.");
		Book book = basicApplicationRepositoryImpl.findById(2L);
		System.out.println(book);
	}
	
	@Test
	public void findAll () {
		System.out.println("Control in findAll method.");
		List<Book> books = basicApplicationRepositoryImpl.findAll();
		if ( ! CollectionUtils.isEmpty(books)) {
			books.forEach(System.out::println);
		}
	}
	
	@Test
	public void addBookToListPos () {
		System.out.println("Control in addBookToListPos method.");
		Book book = new Book ();
		List<Chapter> chapters = new ArrayList<Chapter>();
		Chapter chapter = new Chapter();
		chapter.setHeading("Introduction To C-Sharp");
		chapters.add(chapter);
		book.setAuthor("Klaus Kreft and Angelika Langer");
		book.setTitle("C-Sharp");
		book.setChapters(chapters);
		Long bookId = basicApplicationRepositoryImpl.addBookToList(book);
		System.out.println("Book Id = " + bookId);
	}
	
	@Test
	public void addBookToListNeg () {
		System.out.println("Control in addBookToListNeg method.");
		Long bookId = basicApplicationRepositoryImpl.addBookToList(null);
		System.out.println("Book Id = " + bookId);
	}
	
	@Test
	public void removeBookFromListPos () {
		System.out.println("Control in removeBookFromListPos method.");
		basicApplicationRepositoryImpl.removeBookFromList(1L);
		findAll ();
	}
	
	@Test
	public void removeBookFromListNeg () {
		System.out.println("Control in removeBookFromListNeg method.");
		basicApplicationRepositoryImpl.removeBookFromList(null);
		findAll ();
	}
	
}
