package com.daxtaz.tataneland2.movie;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

public interface IMovieService {
	
	public Movie saveMovie(Movie movie, MultipartFile imageData);
	
	public Movie updateMovie(Boolean deleteImage, Movie movie, MultipartFile imageData);
	
	public void deleteMovie(Integer id);
	
	public List<Movie> findAll();
	
	public Movie findByName(String movieName);
	
	public Optional<Movie> findById(Integer id);
	
	public boolean checkIfIdExist(Integer movieId);

}
