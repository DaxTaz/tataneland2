package com.daxtaz.tataneland2.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.daxtaz.tataneland2.actor.Actor;
import com.daxtaz.tataneland2.actor.IActorService;

/**
 * Actor Controller
 */
@Controller
public class ActorController {
	
	@Autowired
	private IActorService actorService;
	
	/**
	 * Return all actors
	 * 
	 * @param model The model
	 * @return The actors list page
	 */
	@GetMapping("/actors")
	public String getActors(Model model) {
		List<Actor> actorList = actorService.findAllActors();
		model.addAttribute("actors", actorList);
		return "actors";
	}
	
	/**
	 * Return the new actor page
	 * 
	 * @param model The model (null for new actor)
	 * @return The new actor page
	 */
	@GetMapping("/actor/new")
	public String newActor(Model model) {
		model.addAttribute("actor", new Actor());
		return "newActor";
	}
	
	/**
	 * Save actor
	 * 
	 * @param actorToSave The actor to save
	 * @param bindingResult Result of fields errors
	 * @return Redirect to Actors list if all is good else on the new actor page with errors
	 */
	@PostMapping(value = "/actor/save",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String saveActor(@Valid @ModelAttribute("actor") Actor actor, BindingResult bindingResult, MultipartFile imageData) {
	//public String saveActor(Actor actor, MultipartFile imageData) {
		
		if(bindingResult.hasErrors()) {
			return "newActor";
		}
		
		Actor existActor = actorService.findByName(actor.getName());
		
		if(existActor != null) {
			ObjectError error = new ObjectError("globalError", "Actor already exist");
			bindingResult.addError(error);
			return "newActor";
		}
		
		actorService.saveOrUpdateActor(actor, imageData);
		
		return "redirect:/actors";
	}
	
	/**
	 * Edit the actor
	 * 
	 * @param model the model
	 * @param id Actor identifier
	 * @return Redirect to the actor edit page
	 */
	@GetMapping("actor/edit/{id}")
	public String editActor(Model model, @PathVariable Integer id) {
		Optional<Actor> editActor = actorService.findById(id);
		model.addAttribute("actor", editActor.get());
		return "editActor";
	}
	
	/**
	 * Update the actor
	 * 
	 * @param actorToUpdate The actor to update
	 * @param bindingResult Result of fields errors
	 * @return To the list of actors page
	 */
	@PostMapping(value = "actor/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String  update(@Valid @ModelAttribute("actor") Actor actorToUpdate, BindingResult bindingResult, MultipartFile imageData) {
	//public String  update(Actor actorToUpdate, MultipartFile imageData) {
		
		if(bindingResult.hasErrors()) {
			return "editActor";
		}
		
		actorService.saveOrUpdateActor(actorToUpdate, imageData);
		
		return "redirect:/actors";
	}
	
	/**
	 * Delete actor from database
	 * 
	 * @param id The actor identifier
	 * @return To the list of actors page
	 */
	@GetMapping("actor/delete/{id}")
	public String deleteById(@PathVariable Integer id) {
		actorService.deleteById(id);
		return "redirect:/actors";
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
		throws ServletException {
		
		// Convert multipart object to byte[]
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		
	}
	
}
