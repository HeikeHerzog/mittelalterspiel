package de.akademie.logit.view;

import de.akademie.logit.model.Spieler;

/**
 * 
 * @author paul
 * 
 */
public class Infobereich
{
	private Spieler spieler;

	public Infobereich( Spieler spieler )
	{
		this.spieler = spieler;
	}

	public void anzeigenInfo()
	{
		System.out.println( "Name:                " + this.spieler.getName() );
		System.out.println( "Titel:               " + this.spieler.getTitel() );
		System.out.println( "Gold:                " + this.spieler.getGold() );
		System.out.println( "Land mit Gebäuden:   " + this.spieler );
		System.out.println( "Soldaten:            " + this.spieler.getSoldaten() );
		System.out.println( "Steuersatz:          " + this.spieler.getSteuersatz() );
		System.out.println( "Mehl:                " + this.spieler.getMehl() );
		System.out.println( "Korn:                " + this.spieler.getKorn() );
		System.out.println( "Dünger:              " + this.spieler.getDuenger() );
		System.out.println( "Essensration:        " + this.spieler.getEssensration() );
		System.out.println( "Bevölkerungsanzahl:  " + this.spieler.getBevoelkerungsanzahl() );
		System.out.println( "Ich wurde sabotiert: " + ( this.spieler.isSabotageOpfer() == true ? "Ja" : "Nein" ) );
		System.out.println( "Zufriedenheit:       " + this.spieler.getBevoelkerungszufriedenheit() );
	}

}
