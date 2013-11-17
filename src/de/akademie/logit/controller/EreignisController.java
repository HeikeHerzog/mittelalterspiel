package de.akademie.logit.controller;

import de.akademie.logit.model.Spieler;

/**
 * 
 * @author paul
 *
 */
public class EreignisController
{

	private Spieler spieler;

	public EreignisController( Spieler spieler )
	{
		this.spieler = spieler;
	}

	public void ereignisTrigger()
	{
	
	}

	// Bei dem Ereignis "Mäuseplage" wird die Kornmenge 
	//zufällig zwischen 0 und Kornmenge/2 reduziert
	
	private void maeuseplage()
	{
		int kornMenge = this.spieler.getKorn();
		int zufallKornmenge = (int)(Math.random() * (kornMenge/2));
		kornMenge = kornMenge - zufallKornmenge;
		this.spieler.setKorn(kornMenge);	
	}

	
	
	// Bei dem Ereignis "Pest" wird die Bevölkerungszahl
	// zufällig zwischen 0 und Bevölkerungszahl/2 reduziert
	
	private void pest()
	{
		int bevoelkerung = this.spieler.getBevoelkerungsanzahl();
		int zufallBevoelkerung = (int)(Math.random() * (bevoelkerung/2));
		bevoelkerung = bevoelkerung - zufallBevoelkerung;
		this.spieler.setBevoelkerungsanzahl(bevoelkerung);
	}

	// Bei dem Ereignis "gute Ernte" wird die Kornmenge um korn*1.5 erhöht
	
	private void guteErnte()
	{
		double korn = this.spieler.getKorn() * 1.5;
		this.spieler.setKorn((int)korn);
	}

}
