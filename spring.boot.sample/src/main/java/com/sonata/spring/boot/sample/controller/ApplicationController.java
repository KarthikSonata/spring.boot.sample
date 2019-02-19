package com.sonata.spring.boot.sample.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.sonata.spring.boot.sample.model.Book;
import com.sonata.spring.boot.sample.service.IBasicApplicationService;

@Controller
public class ApplicationController {
	
   private static final String SUCCESS = "Success";
	
   private static final String ERROR = "Unexpected error occured please contact Sonata Admin.";

   private static final String MESSAGE = "The requested book('s) is not available.";
   
   private static final String ADD_BOOK_MESSAGE = "Please provide proper details of the book.";
   
   private static final String WELCOME_NOTE = "Welcome to Sonata.";
   
   @Value("${Company.Name}")
   private String companyName;
   
   @Value("${RedirectURI}")
   private String redirectURI;
   
   @Autowired
   private RestTemplate restTemplate;
   
   @Autowired
   private IBasicApplicationService basicApplicationService;
   
   @RequestMapping("/")
   @ResponseBody
    public String home() {
        try {
			return WELCOME_NOTE;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
    }
   
   @RequestMapping(value = "/microService")
   @ResponseBody
   public String microService() {
      try {
    	  String company = "Welcome to " + companyName ;
		  HttpHeaders headers = new HttpHeaders();
		  headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		  HttpEntity <String> entity = new HttpEntity<String>(headers);
		  
		  String department = restTemplate.exchange( redirectURI + "department", HttpMethod.GET, entity, String.class).getBody();
		  return company + " " + department;
	  } catch (RestClientException e) {
		  e.printStackTrace();
		  return ERROR;
	  }
   }
   
   @RequestMapping(value = "/findAll", method = RequestMethod.GET)
   @ResponseBody
   public Object findAll() {
	   try {
		   List<Book> books = basicApplicationService.findAll();
		   return CollectionUtils.isEmpty(books) ? MESSAGE : books;
	   } catch (Exception e) {
		   e.printStackTrace();
		   return ERROR;
	   }
   }
   
   @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
   @ResponseBody
   public Object findBook(@PathVariable Long id) {
	   try {
		   Book book = basicApplicationService.findById(id);
		   return book == null ? MESSAGE : book;
	   } catch (Exception e) {
		   e.printStackTrace();
		   return ERROR;
	   }
   }
   
   @RequestMapping(value = "/add", method = RequestMethod.POST)
   @ResponseBody
   public Object addBookToList (@RequestBody Book book) {
	   try {
		   Long bookId = basicApplicationService.addBookToList(book);
		   return book == null ? ADD_BOOK_MESSAGE : bookId;
	   } catch (Exception e) {
		   e.printStackTrace();
		   return ERROR;
	   }
   }
   
   @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
   @ResponseBody
   public Object removeBook(@PathVariable Long id) {
	   try {
		   basicApplicationService.removeBookFromList(id);
		   return SUCCESS;
	   } catch (Exception e) {
		   e.printStackTrace();
		   return ERROR;
	   }
   }
   
}
