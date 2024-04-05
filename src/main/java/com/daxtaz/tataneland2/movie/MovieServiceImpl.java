package com.daxtaz.tataneland2.movie;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MovieService")
@Transactional
public class MovieServiceImpl implements IMovieService {

	@Autowired
	private IMovieDao movieDao;
	
	public Movie saveMovie(Movie movie) {
		return movieDao.save(movie);
	}
	
	public Movie updateMovie(Movie movie) {
		return movieDao.save(movie);
	}
	
	public void deleteMovie(Integer id) {
		movieDao.deleteById(id);
	}
	
	public List<Movie> findAll() {
		return movieDao.findAll();
	}
	
	public Movie findByName(String movieName) {
		return movieDao.findByName(movieName);
	}
	
	public boolean checkIfIdExist(Integer movieId) {
		return movieDao.existsById(movieId);
	}
	
}
