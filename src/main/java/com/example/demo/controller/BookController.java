package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BookDto;
import com.example.demo.service.BookService;
import com.example.demo.service.BookServiceImp;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	Logger log = LoggerFactory.getLogger(BookController.class);

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<BookDto>> getAllBooks() {
		log.info("all books");
		List<BookDto> list = bookService.getAllBooks();
		return new ResponseEntity<List<BookDto>>(list, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> setBook(@Valid @RequestBody BookDto bookDto) {
		Map<String, Long> map = bookService.setBook(bookDto);
		return new ResponseEntity<Map<String, Long>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<BookDto> getBookById(@PathVariable("id") long id) {
		BookDto bookDto = bookService.getBookById(id);
		return new ResponseEntity<BookDto>(bookDto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBook(@PathVariable("id") long id) {
		long deleted_id = bookService.deleteBook(id);
		return new ResponseEntity<String>("book with id: " + deleted_id + " is deleted", HttpStatus.OK);
	}

	@RequestMapping(value = "/all", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAllBooks() {
		List<Long> list = bookService.deleteAllBooks();
		return new ResponseEntity<String>("all records with id: " + list + " are deleted", HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateBook(@Valid @RequestBody BookDto bookDto, @PathVariable("id") long id) {
		BookDto bDto = bookService.updateBook(bookDto, id);
		long updated_id = bDto.getId();
		return new ResponseEntity<String>("book with id:" + updated_id + " is updated", HttpStatus.OK);

	}
	
	@RequestMapping(value = "name/{name}", method = RequestMethod.GET)
	public void getBookByAuthorName(@PathVariable("name") String name) {
		bookService.findBookByAuthorName(name);
		//return new ResponseEntity<BookDto>(bookDto, HttpStatus.OK);
	}
	

}
