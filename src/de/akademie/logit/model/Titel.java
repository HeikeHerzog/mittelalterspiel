package de.akademie.logit.model;

import java.util.LinkedList;
import java.util.List;

public class Titel
{
	private List<String> titel = new LinkedList<String>();
	private final static int GOLD = 20;
	private final static int LANDMIN = 5;

	public Titel()
	{
		this.titel.add( "Bauer" );
		this.titel.add( "Graf" );
		this.titel.add( "Baron" );
		this.titel.add( "Lord" );
		this.titel.add( "Fuerst" );
		this.titel.add( "Prinz" );
		this.titel.add( "Koenig" );
	}

	public int getGold()
	{
		return Titel.GOLD;
	}

	public int getLandmin()
	{
		return Titel.LANDMIN;
	}

	public String getTitel( int index )
	{
		return this.titel.get( index );
	}

}
