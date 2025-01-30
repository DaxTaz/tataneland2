package com.daxtaz.tataneland2.movie;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;

import com.daxtaz.tataneland2.actor.Actor;

@Entity
@Table(name = "MOVIES")
public class Movie {

	@Id
	@Column(name = "MOVIE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Lob
	@Column(name = "IMAGE_DATA")
	private byte[] imageData;
	
	@Column(name = "NAME", nullable = false)
	@NotBlank(message = "Name can not be null")
	private String name;
	
	@Column(name = "DIRECTOR", nullable = false)
	@NotBlank(message = "Director can not be null")
	private String director;
	
	@Column(name = "WRITER", nullable = false)
	@NotBlank(message = "Writer can not be null")
	private String writer;
	
	@Column(name = "STORY_LINE", nullable = false, length = 1000)
	@NotBlank(message = "Strory line can not be null")
	private String storyLine;
	
	@Column(name = "NATIONALITY", nullable = false)
	@NotBlank(message = "Nationality can not be null")
	private String nationality;
	
	@Column(name = "DURATION", nullable = false)
	//@NotBlank(message = "Duration can not be null")
	private Integer duration;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "CREATION_DATE", nullable = false)
	//@NotBlank(message = "Creation date can not be null")
	private LocalDate creationDate;
	
	public Movie() {}
	
	public Movie(byte[] imageData, String name, String director, String writer, String storyLine, String nationality,
			Integer duration, LocalDate creationDate) {
		super();
		this.imageData = imageData;
		this.name = name;
		this.director = director;
		this.writer = writer;
		this.storyLine = storyLine;
		this.nationality = nationality;
		this.duration = duration;
		this.creationDate = creationDate;
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

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
	
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getStoryLine() {
		return storyLine;
	}

	public void setStoryLine(String storyLine) {
		this.storyLine = storyLine;
	}
	
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	
	public String generateBase64Image() {
        return Base64.encodeBase64String(this.imageData);
    }
	
}
