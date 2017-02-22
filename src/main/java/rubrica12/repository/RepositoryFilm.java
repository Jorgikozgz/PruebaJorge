package rubrica12.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import rubrica12.model.Film;

@org.springframework.stereotype.Repository
public class RepositoryFilm extends Repository {

	public void insertFilm(Film film) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO AUTHOR (NAMEAUTHOR,DATEOFBIRTH" + "VALUES (?,?)");
			preparedStatement.setString(1, film.getNameFilm());
			preparedStatement.setDate(2, film.getDateOfPremier());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		close(preparedStatement);
		manager.close(conn);
	}

	public ArrayList<Film> findFilms(Film film) {
		ArrayList<Film> list = new ArrayList<Film>();

		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = conn
					.prepareStatement("SELECT * FROM AUTHOR WHERE DATEOFBIRTH = ? OR NAMEAUTHOR like ?");
			preparedStatement.setDate(1, film.getDateOfPremier());
			preparedStatement.setString(2, "%" + film.getNameFilm() + "%");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Film filmTmp = new Film();
				filmTmp.setIdFilm(resultSet.getInt(1));
				filmTmp.setNameFilm(resultSet.getString(2));
				filmTmp.setDateOfPremier(resultSet.getDate(3));
				list.add(filmTmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return list;
	}

	public ArrayList<Film> findFilmByDate(Date date) {
		ArrayList<Film> list = new ArrayList<Film>();

		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = conn.prepareStatement("SELECT * FROM AUTHOR WHERE DATEOFBIRTH = ?");
			preparedStatement.setDate(1, date);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Film film = new Film();
				film.setIdFilm(resultSet.getInt(1));
				film.setNameFilm(resultSet.getString(2));
				film.setDateOfPremier(resultSet.getDate(3));
				list.add(film);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return list;
	}
}
