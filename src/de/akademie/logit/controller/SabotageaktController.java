package de.akademie.logit.controller;

import java.util.ArrayList;
import java.util.Random;

import de.akademie.logit.model.Gebaeude;
import de.akademie.logit.model.Land;
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
	
	public SabotageaktController()
	{
		
	}

	public SabotageaktController( Spieler opfer, Spieler aktiverSpieler,
	      int soldateneinsatz )
	{
		this.opfer = opfer;
		this.aktiverSpieler = aktiverSpieler;
		this.soldateneinsatz = soldateneinsatz;
	}

	private boolean goldStehlen()
	{
		boolean erfolg = false;
		int angreifererfolg = ermittleAngreiferErfolg();
		if (angreifererfolg == 0) {
			erfolg = false;
		}
		else if (angreifererfolg>0) {
			int gestohlenesGold = this.opfer.ermittleGoldBetrag(angreifererfolg);
			this.aktiverSpieler.saldiereGold(gestohlenesGold);
			this.opfer.saldiereGold(gestohlenesGold*(-1));
			erfolg = true;
		}
		
		return erfolg;
	}

	private boolean gebaeudeZerstoeren()
	{
		boolean erfolg = false;
		int angreifererfolg = ermittleAngreiferErfolg();
		
		if (angreifererfolg == 0) {
			erfolg = false;
		}
		
		else if (angreifererfolg>0) {
			
			ArrayList<Land> laendereien = this.opfer.getLandListe();
			int i = 0;
			
			while (i < laendereien.size() && i <= angreifererfolg)  {
				
							
					if ( laendereien.get( i ).getGebaeude() != null )
					
						laendereien.get(i).setGebaeude(null);
						erfolg = true;
						i++;
					}
			}
		return erfolg;
	}

	private boolean kornVergiften()
	{
		boolean erfolg = false;
		int angreifererfolg = ermittleAngreiferErfolg();
		
		if (angreifererfolg == 0) {
			erfolg = false;
		}
		else if (angreifererfolg>0) {
			int vergiftetesKorn = this.opfer.ermittleKornMenge(angreifererfolg);
			this.opfer.saldiereKorn(vergiftetesKorn*(-1));
			erfolg = true;
		}
		
		return erfolg;
	}

	private boolean zufriedenheitVerringern()
	{
		boolean erfolg = false;
		int angreifererfolg = ermittleAngreiferErfolg();
		
		if (angreifererfolg == 0) {
			erfolg = false;
		}
		else if (angreifererfolg>0) {
			
			int verloreneSoldaten = this.opfer.getSoldaten() - this.getSoldateneinsatz() *2;
			
			if (verloreneSoldaten >= this.opfer.getSoldaten()) {
				this.opfer.setSoldaten(0);
			}
			else {
				this.opfer.setSoldaten(this.opfer.getSoldaten() - this.getSoldateneinsatz() *2);
				this.opfer.zufriedenheitAnpassen();
			}
			erfolg = true;
		}

		return erfolg;
		
	}

	public boolean sabotiere( int auswahl )
	{
		boolean erfolgreich = false;
		switch (auswahl) {
			case 1:
				erfolgreich = goldStehlen();
				break;
			case 2:
				erfolgreich = gebaeudeZerstoeren();
				break;
			case 3:
				erfolgreich = kornVergiften();
				break;
			case 4:
				erfolgreich = zufriedenheitVerringern();
				break;
		}
		return erfolgreich;
	}

	public int ermittleAngreiferErfolg()
	{	
		int angreifererfolg = (int)(Math.random()*2)+1;
		
		if (angreifererfolg == 0) {
			this.getSoldateneinsatz();
			this.aktiverSpieler.saldiereGold(sabotageKosten*(-1));
			this.aktiverSpieler.saldiereSoldaten(soldateneinsatz*(-1));
			return angreifererfolg;
		}
		else {
			ermittleSabotageAnteil();
			int verloreneSoldaten = (int)(Math.random()*(this.getSoldateneinsatz()/2))+1;
			this.aktiverSpieler.saldiereSoldaten(verloreneSoldaten*(-1));
			
		}
		
		
		
		return angreifererfolg;
	}
	
	
	public int getSoldateneinsatz()
	{
		return this.soldateneinsatz;
	}
	
	public int getSabotageKosten()
	{
		return this.sabotageKosten;
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
