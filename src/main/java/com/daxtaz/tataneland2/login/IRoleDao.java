package com.daxtaz.tataneland2.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleDao extends JpaRepository<Role, Integer> {

	Role findByName(String name);
	
}
