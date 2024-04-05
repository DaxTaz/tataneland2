package com.daxtaz.tataneland2.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieDao extends JpaRepository<Movie, Integer> {

	public Movie findByName(String movieName);
	
}
