package com.daxtaz.tataneland2.movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieDao extends JpaRepository<Movie, Integer> {
	
	public Movie findByName(String movieName);
	
	public void findByWriter(String writer);
	
	@Modifying
	@Query(value = "update movies set name = :name where movie_id = :id", nativeQuery = true)
	public void updateMovieWithoutImage(@Param(value = "name") String name, @Param(value = "id") Integer id);
	
	//@Query(value = "update movies set name = :name where movie_id = :id ", nativeQuery=true)
	//public void test(@Param(value = "name") String name, @Param(value = "id") Integer id);
	
}
