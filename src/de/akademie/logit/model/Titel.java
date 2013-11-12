package de.akademie.logit.model;

import java.util.LinkedList;
import java.util.List;

public class Titel
{
	private List<String> titel = new LinkedList<String>();
	private final static int gold = 20;
	private final static int landmin = 5; 
	
	public Titel()
	{
		this.titel.add( "Bauer" );
		
		
		
		
		this.titel.add( "Koenig" );
	}
	
	public int getGold()
	{
		return Titel.gold;
	}
	
	public int getLandmin()
	{
		return Titel.landmin;
	}
}
