package com.daxtaz.tataneland2.actor;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.daxtaz.tataneland2.movie.Movie;

@Entity
@Table(name = "ACTOR")
public class Actor {

	private Integer id;
	
	private String name;
	
	private String nationality;
	
	private LocalDate ageOfBirth;
	
	private String biography;
	
	private Set<Movie> movies;

	@Id
	@Column(name = "ACTOR_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "NAME", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "NATIONALITY", nullable = false)
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	@Column(name = "AGE_OF_BIRTH", nullable = false)
	public LocalDate getAgeOfBirth() {
		return ageOfBirth;
	}

	public void setAgeOfBirth(LocalDate ageOfBirth) {
		this.ageOfBirth = ageOfBirth;
	}
	
	@Column(name = "BIOGRAPHY")
	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	@ManyToMany(mappedBy = "actors")
	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}
	
}
