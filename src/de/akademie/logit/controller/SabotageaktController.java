package de.akademie.logit.controller;

import java.util.Random;

import de.akademie.logit.model.Marktplatz;
import de.akademie.logit.model.Spieler;
import de.akademie.logit.view.Anzeige;

/**
 * 
 * @author paul, heike
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
		int angreifererfolg = (int)(Math.random()*1)+1;
		
		if (angreifererfolg == 0) {
			this.getSoldateneinsatz();
			this.aktiverSpieler.saldiereGold(sabotageKosten*(-1));
			this.aktiverSpieler.soldatenVersorgen(soldateneinsatz);
			return angreifererfolg;
		}
		else {
			ermittleSabotageAnteil();
			
		}
		
		
		
		return angreifererfolg;
	}
	
	
	public int getSoldateneinsatz()
	{
		return this.soldateneinsatz;
	}
	
	
	public int ermittleSabotageAnteil()
		
	{	int angreifersoldaten = this.getSoldateneinsatz();
		int opfersoldaten = this.opfer.getSoldaten();
		int sabotageAnteil = 0;
		
		if (opfersoldaten == 0) {
			sabotageAnteil = 100;
			
		}
		else if (opfersoldaten != 0) {
			
			sabotageAnteil = (int) (angreifersoldaten/opfersoldaten);
			
			if (sabotageAnteil < 1) {
				sabotageAnteil = 1;			
			}
			else if ( sabotageAnteil > 100) {
				sabotageAnteil = 100;
			}
		}
	
		return sabotageAnteil;
	}

}
