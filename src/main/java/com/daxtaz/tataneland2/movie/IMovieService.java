package com.daxtaz.tataneland2.movie;

import java.util.List;

public interface IMovieService {
	
	public Movie saveMovie(Movie movie);
	
	public Movie updateMovie(Movie movie);
	
	public void deleteMovie(Integer id);
	
	public List<Movie> findAll();
	
	public Movie findByName(String movieName);
	
	public boolean checkIfIdExist(Integer movieId);

}
