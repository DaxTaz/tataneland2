package com.daxtaz.tataneland2.actor;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ACTORS")
public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Lob
	private byte[] imageData;
	
	private String name;
	
	private String nationality;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate ageOfBirth;
	
	private String biography;
	
	//private Set<Movie> movies;
	
	public Actor() {}

	public Actor(byte[] imageData, String name, String nationality, LocalDate ageOfBirth, String biography/*, Set<Movie> movies*/) {
		super();
		this.imageData = imageData;
		this.name = name;
		this.nationality = nationality;
		this.ageOfBirth = ageOfBirth;
		this.biography = biography;
		//this.movies = movies;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	@Column(name = "NAME", nullable = false)
	@NotBlank(message  = "Name can not be null")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank(message  = "Nationality can not be null")
	@Column(name = "NATIONALITY", nullable = false)
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	@Column(name = "AGE_OF_BIRTH", nullable = false)
	@NotNull(message  = "Age can not be null")
	public LocalDate getAgeOfBirth() {
		return ageOfBirth;
	}

	public void setAgeOfBirth(LocalDate ageOfBirth) {
		this.ageOfBirth = ageOfBirth;
	}
	
	@NotBlank(message  = "Biography can not be null")
	@Column(name = "BIOGRAPHY")
	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}
	
	/*
	@ManyToMany(mappedBy = "actors")
	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}
	*/
	
	public String generateBase64Image() {
        return Base64.encodeBase64String(this.imageData);
    }
	
}
