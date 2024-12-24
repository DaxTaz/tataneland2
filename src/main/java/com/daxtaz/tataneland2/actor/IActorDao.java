package com.daxtaz.tataneland2.actor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActorDao extends JpaRepository<Actor, Integer> {

	Actor findByName(String name);
	
}
