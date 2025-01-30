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
	@Column(name = "ACTOR_ID")
	private Integer id;
	
	@Lob
	@Column(name = "IMAGE_DATA")
	private byte[] imageData;
	
	@Column(name = "NAME", nullable = false)
	@NotBlank(message  = "Name can not be null")
	private String name;
	
	@NotBlank(message  = "Nationality can not be null")
	@Column(name = "NATIONALITY", nullable = false)
	private String nationality;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "AGE_OF_BIRTH", nullable = false)
	private LocalDate ageOfBirth;
	
	@NotBlank(message  = "Biography can not be null")
	@Column(name = "BIOGRAPHY", length = 1000)
	private String biography;
	
	public Actor() {}

	public Actor(byte[] imageData, String name, String nationality, LocalDate ageOfBirth, String biography) {
		super();
		this.imageData = imageData;
		this.name = name;
		this.nationality = nationality;
		this.ageOfBirth = ageOfBirth;
		this.biography = biography;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public LocalDate getAgeOfBirth() {
		return ageOfBirth;
	}

	public void setAgeOfBirth(LocalDate ageOfBirth) {
		this.ageOfBirth = ageOfBirth;
	}
	
	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}
	
	public String generateBase64Image() {
        return Base64.encodeBase64String(this.imageData);
    }
	
}
