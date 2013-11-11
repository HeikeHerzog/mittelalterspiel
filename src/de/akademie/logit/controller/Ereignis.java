package de.akademie.logit.controller;

import de.akademie.logit.model.Spieler;

public class Ereignis
{

	private Spieler spieler;

	public Ereignis(Spieler _spieler) {
		this.spieler = _spieler;
	}
	
	public void ereignisTrigger() {
		
	}
	
	
	public boolean maeuseplage() {
		return false;
	}
	
	
	public boolean pest() {
		return false;
	}
	
	public boolean guteErnte() {
		return false;
	}
	
	
}
