package rubrica12.service;

import java.sql.Date;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import rubrica12.model.Film;
import rubrica12.repository.RepositoryFilm;

@Service
public class FilmService {
	@Autowired
	RepositoryFilm repositoryFilm;
	
	public void insertFilm(Film film){
		repositoryFilm.insertFilm(film);
	}
	
	public ArrayList<Film> findFilms(Film film) {
		return repositoryFilm.findFilms(film);
	}
	
	public ArrayList<Film> findFilmByDate(Date date) {
		return repositoryFilm.findFilmByDate(date);
	}

	

}
