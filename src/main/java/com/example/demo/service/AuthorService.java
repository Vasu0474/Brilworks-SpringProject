package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.dto.AuthorDto;

public interface AuthorService {

	List<AuthorDto> getAllAuthor();
	Map<String,Long> setAuthor(AuthorDto authorDto);
	AuthorDto getAuthorById(long id);
	AuthorDto updateAuthorById(AuthorDto authorDto,long id);
	long deleteAuthorById(long id);
	List<Long> deleteAllAuthors();
}
