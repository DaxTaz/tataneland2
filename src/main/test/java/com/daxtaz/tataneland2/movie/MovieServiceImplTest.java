package com.daxtaz.tataneland2.movie;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.daxtaz.tataneland2.actor.Actor;

public class MovieServiceImplTest {

	private IMovieDao movieDao;
	
	private MovieServiceImpl movieService;
	
	@Before
	public void setup() {
		movieDao = Mockito.mock(IMovieDao.class);
		movieService = new MovieServiceImpl(movieDao);
	}
	
	@Test
	public void saveMovieTest() {
		
		Set<Actor> actors = new HashSet<Actor>();
		Actor actor = new Actor("actor", "nationality", LocalDate.now(), "biography", null);
		actors.add(actor);
		
		Movie movie = new Movie("movieTest", "director", "writer", "storyline", "nationality", 120, LocalDate.now(), actors);
		Mockito.when(movieDao.save(any(Movie.class))).thenReturn(movie);
		Movie savedMovie = movieService.saveMovie(movie);
		
		assertNotNull(savedMovie);
		assertEquals(movie.getName(), savedMovie.getName());
		assertEquals(movie.getDirector(), savedMovie.getDirector());
		assertEquals(movie.getWriter(), savedMovie.getWriter());
		assertEquals(movie.getStoryLine(), savedMovie.getStoryLine());
		assertEquals(movie.getNationality(), savedMovie.getNationality());
		assertEquals(movie.getDuration(), savedMovie.getDuration());
		assertEquals(movie.getCreationDate(), savedMovie.getCreationDate());
		
		//TODO a verifier ce que ca compare
		assertEquals(movie.getActors(), savedMovie.getActors());
		
		verify(movieDao).save(any(Movie.class));
	}
	
	@Test
	public void updateMovieTest() {
		
		Set<Actor> actors = new HashSet<Actor>();
		Actor actor = new Actor("actor", "nationality", LocalDate.now(), "biography", null);
		actors.add(actor);
		
		Movie movie = new Movie("movieTest", "director", "writer", "storyline", "nationality", 120, LocalDate.now(), actors);
		Mockito.when(movieDao.save(any(Movie.class))).thenReturn(movie);
		Movie savedMovie = movieService.saveMovie(movie);
		
		Movie movieToUpdate = new Movie("updatedMovieTest", "updatedDirector", "updatedWriter", "updatedStoryline", "updatedNationality", 240, LocalDate.now(), actors);
		Mockito.when(movieDao.save(any(Movie.class))).thenReturn(movieToUpdate);
		Movie updatedMovie = movieService.updateMovie(movieToUpdate);
		
	}
	
	@Test
	public void findByNameTest() {
		Movie movie = new Movie("movieTest", "director", "writer", "storyline", "nationality", 120, LocalDate.now(), null);
		Mockito.when(movieDao.findByName(anyString())).thenReturn(movie);
		Movie findMovie = movieService.findByName("movieTest");
		assertNotNull(findMovie);
		assertEquals("movieTest", findMovie.getName());
		verify(movieDao).findByName(anyString());
	}
}
