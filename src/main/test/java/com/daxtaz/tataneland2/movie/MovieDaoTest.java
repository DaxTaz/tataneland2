package com.daxtaz.tataneland2.movie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;

import java.time.LocalDate;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieDaoTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private IMovieDao movieDao;
	
	@Before
	public void setup() {
		Movie movie = new Movie("movieTest", "director", "writer", "storyline", "nationality", 120, LocalDate.now(), null);
		entityManager.persist(movie);
		entityManager.flush();
	}
	
	@Test
	public void saveMovieTest() {
		Movie saveMovieTest = new Movie("saveMovieTest", "director", "writer", "storyline", "nationality", 120, LocalDate.now(), null);
		Movie saveMovie = movieDao.save(saveMovieTest);
		assertNotNull(saveMovie);
		assertEquals("saveMovieTest", saveMovie.getName());
	}
	
	@Test
	public void updateMovieTest() {
		Movie movieToUpdate = movieDao.findByName("movieTest");
		movieToUpdate.setName("updateMovieTest");
		movieDao.save(movieToUpdate);
		Movie updatedMovieTest = movieDao.findByName("updateMovieTest");
		assertNotNull(updatedMovieTest);
		assertEquals("updateMovieTest", updatedMovieTest.getName());
	}
	
	@Test
	public void deleteMovieTest() {
		Movie movieToDelete = movieDao.findByName("movieTest");
		movieDao.deleteById(movieToDelete.getId());
		Movie deletedMovie = movieDao.findByName("movieTest");
		assertNull(deletedMovie);
	}
	
	@Test
	public void findByNameMovieTest() {
		Movie movie = movieDao.findByName("movieTest");
		assertNotNull(movie);
		assertEquals("movieTest", movie.getName());
	}
	
}
