package com.daxtaz.tataneland2.movie;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.daxtaz.tataneland2.actor.Actor;

@Entity
@Table(name = "MOVIE")
public class Movie {

	private Integer id;
	
	private String name;
	
	private String director;
	
	private String writer;
	
	private String storyLine;
	
	private String nationality;
	
	private Integer duration;
	
	private LocalDate creationDate;
	
	/*private Set<Actor> actors;*/
	
	public Movie() {}
	
	public Movie(String name, String director, String writer, String storyLine, String nationality,
			Integer duration, LocalDate creationDate/*, Set<Actor> actors*/) {
		super();
		this.name = name;
		this.director = director;
		this.writer = writer;
		this.storyLine = storyLine;
		this.nationality = nationality;
		this.duration = duration;
		this.creationDate = creationDate;
		//this.actors = actors;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MOVIE_ID")
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

	@Column(name = "DIRECOR", nullable = false)
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
	
	@Column(name = "WRITER", nullable = false)
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Column(name = "STORY_LINE", nullable = false)
	public String getStoryLine() {
		return storyLine;
	}

	public void setStoryLine(String storyLine) {
		this.storyLine = storyLine;
	}
	
	@Column(name = "NATIONALITY", nullable = false)
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	@Column(name = "DURATION", nullable = false)
	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@Column(name = "CREATION_DATE", nullable = false)
	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	/*
	@ManyToMany
	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}
	*/
	
}
