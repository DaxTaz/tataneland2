package com.daxtaz.tataneland2.movie;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("MovieService")
@Transactional
public class MovieServiceImpl implements IMovieService {

	@Autowired
	private IMovieDao movieDao;
	
	public MovieServiceImpl(IMovieDao movieDao) {
		super();
		this.movieDao = movieDao;
	}

	public Movie saveMovie(Movie movie, MultipartFile imageData) {
		
		try {
			movie.setImageData(imageData.getBytes());
		} catch (IOException e) {
			System.out.println("#####################Error movie set imagae data");
		}
		
		return movieDao.save(movie);
	}
	
	public Movie updateMovie(Boolean deleteImage, Movie movie, MultipartFile imageData) {
		
		Optional<Movie> movieToUpdate = movieDao.findById(movie.getId());
		
		if(imageData.getSize() != 0) {
			try {
				movie.setImageData(imageData.getBytes());
			} catch (IOException e) {
				System.out.println("#####################Error movie set imagae data");
			}
		} else {
			if(deleteImage)
				movie.setImageData(null);
			else
				movie.setImageData(movieToUpdate.get().getImageData());
		}
		
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
	
	public Optional<Movie> findById(Integer id) {
		return movieDao.findById(id);
	}
	
	public boolean checkIfIdExist(Integer movieId) {
		return movieDao.existsById(movieId);
	}
	
}
