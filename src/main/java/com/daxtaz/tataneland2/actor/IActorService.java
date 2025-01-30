package com.daxtaz.tataneland2.actor;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface IActorService {
	
	List<Actor> findAllActors();
	
	Actor findByName(String name);

	Optional<Actor> findById(Integer id);
	
	Actor saveActor(Actor actor, MultipartFile imageData);
	
	Actor updateActor(Boolean deleteImage, Actor actor, MultipartFile imageData);
	
	void deleteById(Integer id);
	
}
