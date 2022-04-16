package com.example.PAC_07;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository <Author, Integer>{

	Optional<Author> findById(String id);

	void deleteById(String id);

	boolean existsById(String id);

	long countByName(String name);

	void deleteByName(String name);

}