package com.example.demo.service;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AuthorRepository;
import com.example.demo.dto.AuthorDto;
import com.example.demo.entity.Author;
import com.example.demo.exception.CustomException;

@Service
public class AuthorServiceImp implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;
	
	ModelMapper model=new ModelMapper();
	
	
	private static final Logger log = LoggerFactory.getLogger(AuthorServiceImp.class);

	
	public List<AuthorDto> getAllAuthor()
	{
		log.info("inside getAllAuthor method");
		List<AuthorDto> list=new ArrayList<>();
		List<Author> list1=authorRepository.findAll();
		if(list1.isEmpty())
		{
			throw new CustomException("no recors found in database");
		}
		else
		{
			for(Author author:list1)
			{
				list.add(model.map(author, AuthorDto.class));
			}
		}
		return list;
		
	}
	
	public Map<String,Long> setAuthor(AuthorDto authorDto)
	{
		log.info("inside setAuthor method");
		Map<String,Long> map=new HashMap<>();
		/*
		 * if(author.getFname()==null || author.getFname().length()==0) { throw new
		 * CustomException("fields are empty"); }
		 */
//			List<String> email=authorRepository.findByEmail();
//			for(String s:email)
//			{
//				if(authorDto.getEmail().equals(s))
//				{
//					throw new CustomException("email already exist");
//				}
//			}
			Author author=new ModelMapper().map(authorDto, Author.class);
			authorRepository.save(author);
			map.put("id", author.getId());
			return map;
	}
	
	

	@Cacheable(cacheNames = "author",key = "#id")
	public AuthorDto getAuthorById(long id)
	{
		log.info("inside getAuthorById method");
		Author author=authorRepository.findById(id);
		if(author==null)
		{
			throw new CustomException("no such author present");
		}
		return model.map(author, AuthorDto.class);
	}
	
	@CachePut(cacheNames = "author",key = "#id")
	public AuthorDto updateAuthorById(AuthorDto authorDto,long id)
	{
		log.info("inside updateAuthorById method");
		Author author=authorRepository.findById(id);
		if(author==null)
		{
			throw new CustomException("there is no such id to update");
		}
		author.setFname(authorDto.getFname());
		author.setLname(authorDto.getLname());
		author.setAddress(authorDto.getAddress());
		author.setEmail(authorDto.getEmail());
		authorRepository.save(author);
		return new ModelMapper().map(author, AuthorDto.class);
	}
	
	@CacheEvict(cacheNames = "author",key = "#id")
	public long deleteAuthorById(long id)
	{
		log.info("inside deleteAuthorById method");
		Author author=authorRepository.findById(id);
		if(author==null)
		{
			throw new CustomException("no such record is present");
		}
		authorRepository.deleteById(id);
		return author.getId();
	}
	public List<Long> deleteAllAuthors()
	{
		log.info("inside deleteAllAuthors method");
		List<Long> idList=new ArrayList<>();
		List<Author> list=authorRepository.findAll();
		if(list.isEmpty())
		{
			throw new CustomException("no records present in author");
		}
		for(Author author:list)
		{
			long id=author.getId();
			deleteAuthorById(id);
			idList.add(id);
		}
		return idList;
	}

	
}
