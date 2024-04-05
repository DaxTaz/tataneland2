package com.daxtaz.tataneland2.movie;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/movie")
public class MovieRestController {
	
	@Autowired
	private MovieServiceImpl movieService;
	
	@PostMapping("/saveMovie")
	public ResponseEntity<MovieDTO> saveMovie(@RequestBody MovieDTO movieDtoRequest) {
		
		Movie existMovie = movieService.findByName(movieDtoRequest.getName());
		
		if(existMovie != null)
			return new ResponseEntity<MovieDTO>(HttpStatus.CONFLICT);
		
		Movie movieRequest = mapMovieDTOToMovie(movieDtoRequest);
		Movie movieResponse = movieService.saveMovie(movieRequest);
		
		if(movieResponse != null) {
			MovieDTO movieDtoResponse = mapMovieToMovieDTO(movieResponse);
			return new ResponseEntity<MovieDTO>(movieDtoResponse, HttpStatus.CREATED);
		}
		
		return new ResponseEntity<MovieDTO>(HttpStatus.NOT_MODIFIED);
		
	}
	
	@PutMapping("/updateMovie")
	public ResponseEntity<MovieDTO> updateMovie(@RequestBody MovieDTO movieDtoRequest) {
		
		boolean exist = movieService.checkIfIdExist(movieDtoRequest.getId());
		
		if(!exist)
			return new ResponseEntity<MovieDTO>(HttpStatus.NOT_FOUND);
		
		Movie movieRequest = mapMovieDTOToMovie(movieDtoRequest);
		Movie movieResponse = movieService.updateMovie(movieRequest);
		
		if(movieResponse != null) {
			MovieDTO movieDtoResponse = mapMovieToMovieDTO(movieResponse);
			return new ResponseEntity<MovieDTO>(movieDtoResponse, HttpStatus.OK);
		}
		
		return new ResponseEntity<MovieDTO>(HttpStatus.NOT_MODIFIED);
		
	}
	
	@DeleteMapping("/deleteMovie/{movieId}")
	public ResponseEntity<String> deleteMovie(@PathVariable Integer movieId) {
		movieService.deleteMovie(movieId);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/findAllMovie")
	public ResponseEntity<List<MovieDTO>> findAllMovie() {
		
		List<MovieDTO> movieDtoList = new ArrayList<MovieDTO>();
		
		List<Movie> movieList = movieService.findAll();
		for(Movie movie : movieList) {
			MovieDTO movieDto = mapMovieToMovieDTO(movie);
			movieDtoList.add(movieDto);
		}
		
		return new ResponseEntity<List<MovieDTO>>(movieDtoList, HttpStatus.OK);
		
	}
	
	private MovieDTO mapMovieToMovieDTO(Movie movie) {
		ModelMapper modelMapper = new ModelMapper();
		MovieDTO movieDto = modelMapper.map(movie, MovieDTO.class);
		return movieDto;
	}
	
	private Movie mapMovieDTOToMovie(MovieDTO movieDto) {
		ModelMapper modelMapper = new ModelMapper();
		Movie movie = modelMapper.map(movieDto, Movie.class);
		return movie;
	}
	
}
