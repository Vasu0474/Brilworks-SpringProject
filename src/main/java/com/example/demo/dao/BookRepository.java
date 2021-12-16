package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
	public Book findById(long id);

	public Book deleteById(long id);

	public List<Book> findAll();

	@Query(value = "select b.* from books b inner join authors a on b.author_fk=a.id where a.first_name=?1", nativeQuery = true)
	public List<Book> findBooksByAuthorName(String name);
}
