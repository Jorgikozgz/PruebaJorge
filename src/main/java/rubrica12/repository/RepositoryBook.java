package rubrica12.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rubrica12.model.Author;
import rubrica12.model.Book;

@org.springframework.stereotype.Repository
public class RepositoryBook extends Repository {

	public void insertBook(Book book) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO BOOK (TITLE,ISBN,IDAUTHOR)" + "VALUES (?,?,?)");
			preparedStatement.setString(1, book.getTitle());
			preparedStatement.setInt(2, book.getIsbn());
			preparedStatement.setInt(3, book.getIdAuthor());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		close(preparedStatement);
		manager.close(conn);
	}
	
	public ArrayList<Book> findBooksByidAuthor(int idAuthor) {
		ArrayList<Book> list = new ArrayList<Book>();
		
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = conn.prepareStatement("SELECT * FROM BOOK WHERE IDAUTHOR  = ?");
			preparedStatement.setInt(1, idAuthor);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				book.setIdBook(resultSet.getInt(1));
				book.setTitle(resultSet.getString(2));
				book.setIsbn(resultSet.getInt(3));
				book.setIdAuthor(resultSet.getInt(4));
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return list;
	}

	public List findBooks(Book book) {
		ArrayList<Book> list = new ArrayList<Book>();

		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT * FROM BOOK WHERE TITLE like ?");
			if (book.getIsbn() != 0) {
				sb.append("OR ISBN like '%?%'");
			}
			preparedStatement = conn.prepareStatement(sb.toString());
			preparedStatement.setString(1, "%" + book.getTitle() + "%");
			if (book.getIsbn() != 0) {
				preparedStatement.setInt(2, book.getIsbn());
			}

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Book bookTmp = new Book();
				bookTmp.setIdBook(resultSet.getInt(1));
				bookTmp.setTitle(resultSet.getString(2));
				bookTmp.setIsbn(resultSet.getInt(3));
				bookTmp.setIdAuthor(resultSet.getInt(4));
				list.add(bookTmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return list;
	}
}
