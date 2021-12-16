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

import com.example.demo.dto.AuthorDto;
import com.example.demo.service.AuthorService;


@RestController
@RequestMapping("/author")
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	Logger log = LoggerFactory.getLogger(AuthorController.class);

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AuthorDto>> findAllAuthor() {
		List<AuthorDto> list = authorService.getAllAuthor();
		return new ResponseEntity<List<AuthorDto>>(list, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> setAuthor(@Valid @RequestBody AuthorDto authorDto) {
		Map<String, Long> map = authorService.setAuthor(authorDto);
		return new ResponseEntity<Map<String, Long>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAuthorById(@PathVariable("id") long id) {
		AuthorDto authorDto = authorService.getAuthorById(id);
		return new ResponseEntity<AuthorDto>(authorDto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAuthor(@PathVariable("id") long id) {
		long deleted_id = authorService.deleteAuthorById(id);
		return new ResponseEntity<String>("record with id: " + deleted_id + " is Deleted", HttpStatus.OK);
	}

	@RequestMapping(value = "/all", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAllAuthors() {
		List<Long> list = authorService.deleteAllAuthors();
		return new ResponseEntity<String>("all records with id: " + list + " are deleted", HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAuthor(@Valid @RequestBody AuthorDto authorDto, @PathVariable("id") long id) {
		AuthorDto authDto = authorService.updateAuthorById(authorDto, id);
		return new ResponseEntity<String>("Record with id: " + authDto.getId() + " is updated", HttpStatus.OK);
	}

}
