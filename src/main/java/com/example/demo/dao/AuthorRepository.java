package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long>{
	public Author findById(long id);
	public List<Author> findAll();
	public void deleteById(long id);
	
	@Query(value = "select email from authors",nativeQuery = true)
	public List<String> findByEmail();

}
