package com.example.PAC_07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class LibraryRestController {
	
	@Autowired
	AuthorService authorservice;
	
	@GetMapping("authors")
	public ResponseEntity<Iterable<Author>> getAllAuthors() {
		
		var headers = new HttpHeaders();
		headers.add("ResponseGet", "getAllAuthors executed");
		headers.add("version", "1.0 Api Rest Author Object");
		
		return ResponseEntity.accepted().headers(headers).body(authorservice.queryAuthorsFromArray());
	}
	
	@PostMapping(path = "/addAuthor", consumes = "application/json" )
	public ResponseEntity<Author> createBook(@Validated @RequestBody Author author) {
		
		Author authorSaved = authorservice.addAuthorToArray(author);
		
		var headers = new HttpHeaders();
		headers.add("ResponseCreate", "createAuthor executed");
		headers.add("version", "1.0 Api Rest Author Object");
		headers.add("Executed Output", "author created");
		
		return ResponseEntity.accepted().headers(headers).body(authorSaved);
	}
	
	@DeleteMapping ("/deleteAuthor/{name}")
	public ResponseEntity<Author> deleteAuthor  (@PathVariable String name ) {
		
		String responsedelete = ""; 
		int idAuthor = authorservice.findAuthorByName (name);
		Author authortodelete = null;
		
		if ( idAuthor != -1 ) 
			{
			authortodelete = authorservice.getAuthorById(idAuthor);
			authorservice.deleteAuthorFromArray(idAuthor);
			responsedelete = responsedelete + "author: " + name  + " - deleted #succes";
		}else {
			System.out.println("Author not found, not deleted");
			responsedelete = responsedelete + "book: " + name  + " - not deleted book not found #fail";
		}
		
		var headers = new HttpHeaders();
		headers.add("ResponseDeleted", "deleteAuthor executed");
		headers.add("version", "1.0 Api Rest Author Object");
		headers.add("Executed Output", responsedelete);
		
		return ResponseEntity.accepted().headers(headers).body(authortodelete);
	}
	
	@PostMapping("/replaceAuthor/{name}")
	public ResponseEntity<Author> updateAuthor (@PathVariable String name, @RequestBody Author authorFromRest ) {
		
		String responseUpdate = "";
		Author authorToUpdate = null;

		int idAuthor = authorservice.findAuthorByName (name);
		if ( idAuthor == -1 ) {
			responseUpdate = responseUpdate + "author not found";
		} else {
			authorToUpdate = authorservice.getAuthorById(idAuthor);
			
			//we are going to compare both books: 
			//bookFromRest vs bookToUpdate
			//we need to compare each field of our object
			responseUpdate += "book found";
			boolean updated = false;
			
			if  (authorFromRest.getName() != null) {
				responseUpdate += " - author name value updated: " + authorFromRest.getName() +  "( old value: " + authorToUpdate.getName() + ")" ;
				authorToUpdate.setName(authorFromRest.getName());
				updated = true;
			}
			if  (authorFromRest.getCountry() != null) {
				responseUpdate += " - Country value updated: " + authorFromRest.getCountry() +  "( old value: " + authorToUpdate.getCountry() + ")" ;
				authorToUpdate.setCountry(authorFromRest.getCountry());
				updated = true;
			}
			if  (authorFromRest.getDob() != 0) {
				responseUpdate += " - Date of Birth int value updated: " + authorFromRest.getDob() +  "( old value: " + authorToUpdate.getDob() + ")" ;
				authorToUpdate.setDob(authorFromRest.getDob());
				updated = true;
			}
			if  (authorFromRest.getQtyBooks() != 0) {
				responseUpdate += " - QtyBooks int value updated: " + authorFromRest.getQtyBooks() +  "( old value: " + authorToUpdate.getQtyBooks() + ")" ;
				authorToUpdate.setQtyBooks(authorFromRest.getQtyBooks());
				updated = true;
			}
			
			if  (authorFromRest.getAlive() != true) {
				responseUpdate += " - Alive Boolean value updated: " + authorFromRest.getAlive() +  "( old value: " + authorToUpdate.getAlive() + ")" ;
				authorToUpdate.setAlive(authorFromRest.getAlive());
				updated = true;
			}
		
			if (!updated) responseUpdate += " - try to update but any field updated - something wrong happened";
			else authorservice.replaceAuthor (idAuthor, authorToUpdate);
		}
		
		var headers = new HttpHeaders();
		headers.add("ResponseUpdate", "updateAuthor executed");
		headers.add("version", "1.0 Api Rest Author Object");
		headers.add("Executed Output", responseUpdate);
		
		return ResponseEntity.accepted().headers(headers).body(authorToUpdate);	
	}
}