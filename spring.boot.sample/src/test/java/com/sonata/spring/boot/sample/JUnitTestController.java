package com.sonata.spring.boot.sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sonata.spring.boot.sample.controller.ApplicationController;
import com.sonata.spring.boot.sample.model.Book;
import com.sonata.spring.boot.sample.model.Chapter;

public class JUnitTestController extends ApplicationTests {
	
	@Autowired
	private ApplicationController applicationController;
	
	@Test
	public void findBookPos() {
		Object book = applicationController.findBook(1L);
		System.out.println(book);
	}
	
	@Test
	public void findBookNeg() {
		Object book = applicationController.findBook(2L);
		System.out.println(book);
	}
	
	@Test
	public void findAll() throws JsonParseException, JsonMappingException, IOException {
		Object books = applicationController.findAll();
		if (books != null) {
			System.out.println(books);
		}
	}
	
	@Test
	public void addBookToListPos () throws JsonParseException, JsonMappingException, IOException {
		Book book = new Book ();
		List<Chapter> chapters = new ArrayList<Chapter>();
		Chapter chapter = new Chapter();
		chapter.setHeading("Introduction To C-Sharp");
		chapters.add(chapter);
		book.setAuthor("Klaus Kreft and Angelika Langer");
		book.setTitle("C-Sharp");
		book.setChapters(chapters);
		Object bookId = applicationController.addBookToList(book);
		System.out.println("Book Id = " + bookId);
		findAll();
	}
	
	@Test
	public void addBookToListNeg () {
		Object bookId = applicationController.addBookToList(null);
		System.out.println("Book Id = " + bookId);
	}
	
	@Test
	public void removeBookPos () {
		Object message = applicationController.removeBook(1L);
		System.out.println(message);
	}
	
	@Test
	public void removeBookNeg () {
		Object message = applicationController.removeBook(null);
		System.out.println(message);
	}
	
	@Test
	public void microService () {
		Object message = applicationController.microService();
		System.out.println(message);
	}

}
