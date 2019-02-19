package com.sonata.spring.boot.sample.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.sonata.spring.boot.sample.model.Book;
import com.sonata.spring.boot.sample.model.Chapter;

@Repository
public class BasicApplicationRepositoryImpl implements IBasicApplicationRepository {

	private static List<Book> books = new ArrayList<Book>();
	
	private static final Long BOOK_ID = 1L;
	
	private static final String TITLE = "JAVA";
	
	private static final String AUTHOR = "James Gosling";
	
	static {
		List<Chapter> chapterList = new ArrayList();
		Chapter chapter = new Chapter();
		chapter.setHeading("Java Introduction");
		chapterList.add(chapter);
		Book book = new Book();
		book.setBookId(BOOK_ID);
		book.setTitle(TITLE);
		book.setAuthor(AUTHOR);
		book.setChapters(chapterList);
		books.add(book);
	}
	
	@Override
	public Book findById (Long id) {
		return CollectionUtils.isEmpty(books) ? null : books.stream().filter(book -> book.getBookId().equals(id)).findFirst().orElse(null);
	}
	
	@Override
	public List<Book> findAll () {
		return books;
	}
	
	@Override
	public Long addBookToList (Book book) {
		Long bookId = null;
		if (book != null) {
			bookId = 1L;
			if (! CollectionUtils.isEmpty(books)) {
				Comparator <Book> comparator = (b1, b2) -> Long.compare(b1.getBookId(), b2.getBookId());
				bookId = books.stream().max(comparator).get().getBookId() + 1;
			}
			book.setBookId(bookId);
			books.add(book);
		}
		return bookId;
	}
	
	@Override
	public void removeBookFromList (Long bookId) {
		if (! CollectionUtils.isEmpty(books) && bookId != null) {
			books.removeIf(book -> book.getBookId().equals(bookId));
		}
	}
	
	
			
}
