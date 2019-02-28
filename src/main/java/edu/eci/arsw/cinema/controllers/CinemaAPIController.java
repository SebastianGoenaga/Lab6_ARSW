/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
/**
 *
 * @author cristian
 */
import edu.eci.arsw.cinema.services.CinemaServices;

@RestController
@RequestMapping(value = "/cinemas")

public class CinemaAPIController {
	
	@Autowired
	private CinemaServices cinemaServices;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(){
	    try {
	        //obtener datos que se enviarán a través del API
	    	
	    	Set<Cinema> data = cinemaServices.getAllCinemas();
	        return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
	    } catch (CinemaPersistenceException ex) {
	        Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
	    }        
	}
	@GetMapping("{name}")
	public ResponseEntity<?> getByName(@PathVariable String name){
	    try {
	        //obtener datos que se enviarán a través del API
	    	
	    	ArrayList<CinemaFunction> data = (ArrayList<CinemaFunction>) cinemaServices.getCinemaByName(name).getFunctions();
	        return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
	    } catch (CinemaPersistenceException ex) {
	        Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
	    }        
	}
	@GetMapping("{name}/{date}")
	public ResponseEntity<?> getByDate(@PathVariable String name, @PathVariable String date){
	    try {
	        //obtener datos que se enviarán a través del API
	    	
	    	ArrayList<CinemaFunction> data = (ArrayList<CinemaFunction>) cinemaServices.getFunctionsbyCinemaAndDate(name, date.replace("%20", " "));
	        return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
	    } catch (CinemaException ex) {
	        Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
	    }        
	}
	@GetMapping("{name}/{date}/{moviename}")
	public ResponseEntity<?> getByDate(@PathVariable String name, @PathVariable String date, @PathVariable String moviename){
	    try {
	        //obtener datos que se enviarán a través del API
	    	
	    	CinemaFunction data = cinemaServices.getFunctionsbyCinemaAndHourAndMovie(name, date, moviename);
	        return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
	    } catch (CinemaException ex) {
	        Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
	    }        
	}
	@PostMapping("{name}")
	public ResponseEntity<?> putFunction(@PathVariable String name, @RequestBody CinemaFunction function){
	    try {
	    	
	        //registrar dato
	    	
	    	
	    	cinemaServices.addCinemaFuction(name, function);
	    	
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (CinemaException ex) {
	        Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Error bla bla bla",HttpStatus.FORBIDDEN);            
	    }        

	}
	@PutMapping("{name}")
	public ResponseEntity<?> updateFunction(@PathVariable String name, @RequestBody CinemaFunction function){
	    try {	    	
	    	
	    	cinemaServices.updateCinemaFuction(name, function);
	    	
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (CinemaException ex) {
	        Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Error bla bla bla",HttpStatus.FORBIDDEN);            
	    }        

	}
}
