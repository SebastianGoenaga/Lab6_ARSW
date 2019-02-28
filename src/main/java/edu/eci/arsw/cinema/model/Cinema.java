/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.model;

import java.util.List;

/**
 *
 * @author cristian
 */
public class Cinema {
	private String name;
	private List<CinemaFunction> functions;

	public Cinema() {
	}

	public Cinema(String name, List<CinemaFunction> functions) {
		this.name = name;
		this.functions = functions;
	}

	public String getName() {
		return name;
	}
	
	public void addFuction (CinemaFunction function) {
		functions.add(function);
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CinemaFunction> getFunctions() {
		return this.functions;
	}

	public void setSchedule(List<CinemaFunction> functions) {
		this.functions = functions;
	}

	public boolean updateFunction(CinemaFunction function) {
		
		for (CinemaFunction cinemaFunction : functions) {
			System.out.println(cinemaFunction.getMovie().getName());
			System.out.println(function.getMovie().getName());
			if (cinemaFunction.getMovie().getName().equals(function.getMovie().getName())) {
				cinemaFunction.setFunction(function);
				return true;
			}
		}
		return false;
		
	}
}
