package rubrica12.service;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rubrica12.model.Author;
import rubrica12.repository.RepositoryAuthor;

@Service
public class AuthorService {

	@Autowired
	RepositoryAuthor repositoryAuthor;
	
	public void insertAuthor(Author author){
		repositoryAuthor.insertAuthor(author);
	}
	
	public ArrayList<Author> findAuthors(Author author) {
		return repositoryAuthor.findAuthors(author);
	}
	
	public ArrayList<Author> findAuthorsByDate(Date date) {
		return repositoryAuthor.findAuthorsByDate(date);
	}
}
