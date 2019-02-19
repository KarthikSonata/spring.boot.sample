package com.sonata.spring.boot.sample.model;

import java.util.List;

import lombok.Data;

@Data
public class Book {

	private Long bookId;
	
	private String title;
	
	private String author;
	
	private List<Chapter> chapters;
	
}
