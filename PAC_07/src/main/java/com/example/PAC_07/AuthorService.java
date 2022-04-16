package com.example.PAC_07;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
	
	@Autowired
	AuthorRepository authorRepository;
	
	public Iterable<Author> findAll() {
		
		//System.out.println ("Books" + books);
		
		return authorRepository.findAll();
	}
	
	public void save(Author author) {
		
		authorRepository.save(author);
		
		
	}
	
	public String findAndDeleteById(String id) {

		String response = "";
		Optional<Author> authorFound = authorRepository.findById(id);

		if (authorFound.isPresent()) {

			authorRepository.delete(authorFound.get());
			response += "author deleted";
		} else {

			response += "author not found";
		}

		return response;
	}

	public void deleteById(String id) {

		// String response = "";

		authorRepository.deleteById(id);

	}

	public String update(String id, Author author) {

		String response = "";
		Optional<Author> authorFound = authorRepository.findById(id);

		if (authorFound.isPresent()) {

			authorFound.get().setName(author.getName());
			authorRepository.save(authorFound.get());
			response += "author updated";

		} else {
			response += "author not found";
		}

		return response;

	}

	// other options
	public long count() {

		long quantity = authorRepository.count();

		return quantity;
	}

	public boolean existsById(String id) {

		boolean isAuthor = authorRepository.existsById(id);

		return isAuthor;
	}

	public Long countByLastname(String author) {

		long quantity = authorRepository.countByAuthor(author);

		return quantity;
	}

	public void deleteByLastname(String author) {

		authorRepository.deleteByAuthor(author);

	}
}