package de.akademie.logit.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.akademie.logit.model.Marktplatz;
import de.akademie.logit.view.Anzeige;

public class Eingabe
{
	
	private Anzeige anzeige;
	private Marktplatz marktplatz;
	private Sabotageakt sabotageakt;
	
	
	public Eingabe() {

	}
	
	
	public void initialisieren() {
		
	}
	
	
	public void loop() {
		
	}
	
	public String getEingabe() {
		InputStreamReader isr = new InputStreamReader(System.in);
	    BufferedReader br = new BufferedReader(isr);	    
	    try {
			String eingabe = br.readLine();
			return eingabe;
		} catch (IOException e) {			
			e.printStackTrace();
			return null;
		}	    
	}
	
	
	public void setMarktplatz(Marktplatz marktplatz) {
		
	}
	
	
	public Marktplatz getMarktplatz() {
		return null;
	}

}
