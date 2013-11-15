package de.akademie.logit.view;

import de.akademie.logit.model.Spieler;

/**
 * 
 * @author paul
 * 
 */
public class Anzeige
{

	private Anzeige()
	{}

	public static void zeigeStringAn( String text )
	{
		System.out.println( text );
	}

	public static void zeigeMenuAn( Spieler spieler, Menu menu )
	{
		System.out.println( "Spielerinfo" );
		System.out.println( "===========" );
		new Infobereich( spieler ).anzeigenInfo();
		System.out.println();
		menu.printText();
	}

}
