package de.akademie.logit.view;

import de.akademie.logit.model.Marktplatz;
import de.akademie.logit.model.Spieler;

/**
 * 
 * @author paul
 * 
 */
public class Infobereich
{
	private Spieler spieler;
	private Marktplatz marktplatz;

	public Infobereich( Spieler spieler )
	{
		this.spieler = spieler;
		this.marktplatz = Marktplatz.getInstance();
	}

	public void anzeigenInfo()
	{
		System.out.println( "Spielrunde: " + this.marktplatz.getRundenzaehler() );
		System.out.println( "Name:                " + this.spieler.getName() );
		System.out.println( "Titel:               " + this.spieler.getTitel() );
		System.out.println( "Gold:                " + this.spieler.getGold() );
		System.out.println( this.spieler.getBesitz() );
		System.out.println( "\t\t\t\t\t\t\t\tLand Preis: " + this.marktplatz.getPreisLand() );
		System.out.println( "\t\t\t\t\t\t\t\tGebäude Preis: " + this.marktplatz.getPreisGebaeude() );
		System.out.println( "Soldaten:            " + this.spieler.getSoldaten() + "\t\t\t\t\t\tSoldaten Preis: " + this.marktplatz.getSoldatenPreis() + "\tSold: " + this.marktplatz.getSoldatenSold() );
		System.out.println( "Steuersatz:          " + this.spieler.getSteuersatz() );
		System.out.println( "Mehl:                " + this.spieler.getMehl() + "\t\t\t\t\t\tMehl Preis: " + this.marktplatz.getPreisMehl() + "\t\tMenge: " + this.marktplatz.getMengeMehlEnde() );
		System.out.println( "Korn:                " + this.spieler.getKorn() + "\t\t\t\t\t\tKorn Preis: " + this.marktplatz.getPreisKorn() + "\t\tMenge: " + this.marktplatz.getMengeKornEnde() );
		System.out.println( "Dünger:              " + this.spieler.getDuenger() + "\t\t\t\t\t\tDünger Preis: " + this.marktplatz.getPreisDuenger() );
		System.out.println( "Essensration:        " + this.spieler.getEssensrationText() );
		System.out.println( "Bevölkerungsanzahl:  " + this.spieler.getBevoelkerungsanzahl() );
		System.out.println( "Ich wurde sabotiert: " + ( this.spieler.isSabotageOpfer() == true ? "Ja" : "Nein" ) );
		System.out.println( "Ich kann sabotieren: " + ( this.spieler.isSaboteur() == true ? "Nein" : "Ja" ) );
		System.out.println( "Zufriedenheit:       " + this.spieler.getBevoelkerungszufriedenheit() );
		System.out.println( "Ereignis:            " + this.spieler.getEreignis() );
	}

}
