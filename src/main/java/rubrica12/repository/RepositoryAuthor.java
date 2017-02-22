package rubrica12.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import rubrica12.model.Author;

@org.springframework.stereotype.Repository
public class RepositoryAuthor extends Repository {

	public void insertAuthor(Author author) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO AUTHOR (NAMEAUTHOR,DATEOFBIRTH)" + "VALUES (?,?)");
			preparedStatement.setString(1, author.getNameAuthor());
			preparedStatement.setDate(2, author.getDateOfBirth());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		close(preparedStatement);
		manager.close(conn);
	}

	public ArrayList<Author> findAuthors(Author author) {
		ArrayList<Author> list = new ArrayList<Author>();

		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = conn
					.prepareStatement("SELECT * FROM AUTHOR WHERE DATEOFBIRTH = ? OR NAMEAUTHOR like ?");
			preparedStatement.setDate(1, author.getDateOfBirth());
			preparedStatement.setString(2, "%" + author.getNameAuthor() + "%");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Author authorTmp = new Author();
				authorTmp.setIdAuthor(resultSet.getInt(1));
				authorTmp.setNameAuthor(resultSet.getString(2));
				authorTmp.setDateOfBirth(resultSet.getDate(3));
				list.add(authorTmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return list;
	}

	public ArrayList<Author> findAuthorsByDate(Date date) {
		ArrayList<Author> list = new ArrayList<Author>();

		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = conn.prepareStatement("SELECT * FROM AUTHOR WHERE DATEOFBIRTH = ?");
			preparedStatement.setDate(1, date);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Author author = new Author();
				author.setIdAuthor(resultSet.getInt(1));
				author.setNameAuthor(resultSet.getString(2));
				author.setDateOfBirth(resultSet.getDate(3));
				list.add(author);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return list;
	}
}
