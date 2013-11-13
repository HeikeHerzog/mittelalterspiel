package de.akademie.logit.controller;

import de.akademie.logit.model.Marktplatz;
import de.akademie.logit.model.Spieler;
import de.akademie.logit.view.Anzeige;

/**
 * 
 * @author paul
 *
 */
public class SabotageaktController
{

	private Spieler opfer;
	private Spieler aktiverSpieler;
	private Anzeige anzeige;
	private Marktplatz marktplatz;
	private int soldateneinsatz;
	private int sabotageKosten = 10;

	public SabotageaktController( Spieler opfer, Spieler aktiverSpieler,
	      int soldateneinsatz )
	{
		this.opfer = opfer;
		this.aktiverSpieler = aktiverSpieler;
		this.soldateneinsatz = soldateneinsatz;
	}

	private boolean goldStehlen()
	{
		return false;
	}

	private boolean gebaeudeZerstoeren()
	{
		return false;
	}

	private boolean kornVergiften()
	{
		return false;
	}

	private boolean zufriedenheitVerringern()
	{
		return false;
	}

	public boolean sabotiere( int auswahl )
	{
		return false;
	}

	public int ermittleAngreiferErfolg()
	{
		return -1;
	}
	
	public int getSoldateneinsatz()
	{
		return this.soldateneinsatz;
	}
	
	public int ermittleSabotageAnteil()
	{
		return -1;
	}

}
