package com.example.PAC_07;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AuthorService {
	
	static List<Author> authors = new ArrayList<Author>();
	static {
		//int id, String name, String country; int dob; int qtyBooks;Boolean alive
		Author author1 = new Author (0001, "Carlos Ruiz Zafon", "Spain", 25-9-1964, 9, false);
		Author author2 = new Author (0002, "Arturo Perez-Reverte", "Spain", 25-11-1951, 33, true);
		
		authors.add(author1);
		authors.add(author2);
	}
	
	public List<Author> queryAuthorsFromArray(){
		System.out.println("Authors" + authors);
		return authors;
	}
	
	public Author addAuthorToArray(Author author) {
		authors.add(author);
		return author;
	}
	
	public String deleteAuthorFromArray(String name) {
		int index = findAuthorByName(name);
		authors.remove(index);
		return "Book deleted by title";
	}
	
	public String deleteAuthorFromArray(int id) {
		authors.remove(id);
		return "Author deleted by id";
	}
	
	int findAuthorByName(String name) {
		int index = -1;
		for ( Author authorTemporal  : authors) {
			if ( authorTemporal.getName().equals(name) ) {	
				index = authors.indexOf(authorTemporal);
			}
		}
		return index;
	}
	
	public Author replaceAuthor(int idAuthor, Author author) {
		authors.set(idAuthor, author);
		return author;
	}
	
	public Author getAuthorById (int id) {
		Author author = authors.get(id);
		return author;
	}
}