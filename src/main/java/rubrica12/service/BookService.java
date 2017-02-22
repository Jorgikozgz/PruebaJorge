package rubrica12.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rubrica12.model.Book;
import rubrica12.repository.RepositoryBook;

@Service
public class BookService {

	@Autowired
	RepositoryBook repositoryBook;
	
	public void insertBook(Book book){
		repositoryBook.insertBook(book);
	}
	
	public List findBooks(Book book) {
		return repositoryBook.findBooks(book);
	}
	
	public ArrayList<Book> findBooksByidAuthor(int idAuthor) {
		return repositoryBook.findBooksByidAuthor(idAuthor);
	}
}
