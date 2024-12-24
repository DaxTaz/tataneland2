package com.daxtaz.tataneland2.actor;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("ActorService")
@Transactional
public class ActorServiceImpl implements IActorService {
	
	@Autowired
	private IActorDao actorDao;
	
	public List<Actor> findAllActors() {
		List<Actor> actorList = actorDao.findAll();
		return actorList;
	}
	
	public Actor findByName(String name) {
		return actorDao.findByName(name);
	}
	
	public Optional<Actor> findById(Integer id) {
		return actorDao.findById(id);
	}
	
	public Actor saveOrUpdateActor(Actor actor, MultipartFile imageData) {
		try {
			actor.setImageData(imageData.getBytes());
		} catch (IOException e) {
			System.out.println("#####################GET_BYTES");
		}
		
		return actorDao.save(actor);
	}

	public void deleteById(Integer id) {
		actorDao.deleteById(id);
	}
	
}
