package com.daxtaz.tataneland2.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.daxtaz.tataneland2.movie.IMovieService;
import com.daxtaz.tataneland2.movie.Movie;

@Controller
public class MovieController {

	@Autowired
	private IMovieService movieService;
	
	@GetMapping("/movies")
	public String findAllMovies(Model model) {
		List<Movie> movieList = movieService.findAll();
		model.addAttribute("movies", movieList);
		return "movies";
	}
	
	@GetMapping("/movie/view/{id}")
	public String viewMovie(Model model, @PathVariable Integer id) {
		Optional<Movie> movie = movieService.findById(id);
		model.addAttribute("movie", movie.get());
		return "viewMovie";
	}
	
	@GetMapping("/movie/new")
	public String newMovie(Model model) {
		model.addAttribute("movie", new Movie());
		return "newMovie";
	}
	
	@PostMapping(value = "/movie/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String saveMovie(@Valid @ModelAttribute("movie") Movie movie, MultipartFile imageData, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "newMovie";
		}
		
		Movie existMovie = movieService.findByName(movie.getName());
		
		if(existMovie != null) {
			ObjectError error = new ObjectError("movie", "Movie already exist");
			bindingResult.addError(error);
			return "newMovie";
		}
		
		movieService.saveMovie(movie, imageData);
		
		return "redirect:/movies";
	}
	
	@GetMapping("/movie/edit/{id}")
	public String movieEdit(Model model, @PathVariable Integer id) {
		Optional<Movie> movie = movieService.findById(id);
		model.addAttribute("movie", movie.get());
		return "editMovie";
	}
	
	@PostMapping(value = "movie/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String updateMovie(@RequestParam(value = "deleteImage", required = false) String deleteImage, @Valid @ModelAttribute("movie") Movie movie, MultipartFile imageData, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "editMovie";
		}
		
		if(deleteImage != null)
			movieService.updateMovie(true, movie, imageData);
		else
			movieService.updateMovie(false, movie, imageData);
		
		return "redirect:/movies";
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
		throws ServletException {
		
		// Convert multipart object to byte[]
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		
	}
	
}
