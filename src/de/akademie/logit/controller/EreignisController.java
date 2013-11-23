package de.akademie.logit.controller;

import de.akademie.logit.model.Spieler;
import de.akademie.logit.view.Anzeige;

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

	// löst zufällig eins der Ereignisse aus oder keines
	public void ereignisTrigger()
	{
		int zufallEreignis = (int) ( Math.random() * 4 );
		switch ( zufallEreignis )
		{
			case 0:
				// kein Ereignis
				break;
			case 1:
				maeuseplage();
				Anzeige.zeigeStringAn("Eine Mäuseplage ist aufgetreten, ein Teil des Korns wurde aufgefressen!");
				break;
			case 2:
				pest();
				Anzeige.zeigeStringAn("Die Pest ist aufgetreten, ein Teil der Bevoelkerung ist gestorben!");
				break;
			case 3:
				guteErnte();
				Anzeige.zeigeStringAn("Eine gute Ernte mit mehr 50% mehr Korn als ueblich wurde eingefahren!");
				break;
		}
	}

	// Bei dem Ereignis "Mäuseplage" wird die Kornmenge
	// zufällig zwischen 0 und Kornmenge/2 reduziert

	private boolean maeuseplage()
	{
		int kornMenge = this.spieler.getKorn();
		int zufallKornmenge = (int) ( Math.random() * ( kornMenge / 2 ) );
		kornMenge = kornMenge - zufallKornmenge;
		this.spieler.setKorn( kornMenge );
		return true;
	}

	// Bei dem Ereignis "Pest" wird die Bevölkerungszahl
	// zufällig zwischen 0 und Bevölkerungszahl/2 reduziert

	private boolean pest()
	{
		int bevoelkerung = this.spieler.getBevoelkerungsanzahl();
		int zufallBevoelkerung = (int) ( Math.random() * ( bevoelkerung / 2 ) );
		bevoelkerung = bevoelkerung - zufallBevoelkerung;
		this.spieler.setBevoelkerungsanzahl( bevoelkerung );
		return true;
	}

	// Bei dem Ereignis "gute Ernte" wird die Kornmenge um korn*1.5 erhöht

	private boolean guteErnte()
	{
		double korn = this.spieler.getKorn() * 1.5;
		this.spieler.setKorn( (int) korn );
		return true;
	}

}
