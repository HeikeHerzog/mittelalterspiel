package de.akademie.logit.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.akademie.logit.model.Marktplatz;
import de.akademie.logit.model.Spieler;
import de.akademie.logit.view.Anzeige;

/**
 * 
 * @author jutta, paul
 * 
 */
public class EingabeController
{

	private Marktplatz marktplatz;
	private SabotageaktController sabotageakt;

	public EingabeController()
	{}

	public void initialisieren()
	{
		int anzSpieler = -1;

		while ( anzSpieler < 0 || anzSpieler > 4 )
		{
			Anzeige.zeigeStringAn( "Anzahl der Spieler ( 0 bis 4 ) eingeben:" );
			try
			{
				anzSpieler = Integer.parseInt( getEingabe() );
			}
			catch ( NumberFormatException nfEx )
			{
				System.out.println( "Es wurde keine Zahl eingegeben." );
			}
		}

		switch ( anzSpieler )
		{
			case 0:
				spielBeenden();
				break;

			default:
				Marktplatz marktplatz = new Marktplatz();
				setMarktplatz( marktplatz );

				// erfassen der Spieler
				String spielerName = "";
				for ( int i = 1; i < anzSpieler; i++ )
				{
					Anzeige.zeigeStringAn( i + ". Spielernamen eingeben:" );
					spielerName = getEingabe();
					// wenn keine Name eingegeben wurde, dann einfach den Zähler nehmen
					if ( spielerName.trim().isEmpty() )
					{
						spielerName = i + ". Spieler";
					}
					Spieler spieler = new Spieler( spielerName );
					this.marktplatz.fuegeSpielerHinzu( spieler );
				}
				break;
		}

	}

	public void loop()
	{

	}

	public String getEingabe()
	{
		InputStreamReader isr = new InputStreamReader( System.in );
		BufferedReader br = new BufferedReader( isr );
		try
		{
			String eingabe = br.readLine();
			return eingabe;
		}
		catch ( IOException e )
		{
			e.printStackTrace();
			return null;
		}
	}

	public void setMarktplatz( Marktplatz marktplatz )
	{
		this.marktplatz = marktplatz;
	}

	public Marktplatz getMarktplatz()
	{
		return null;
	}

	public void spielzugBeenden()
	{

	}

	public void spielBeenden()
	{
		System.out
		      .println( "Vielen Dank, dass Sie sich für unser Produkt entschieden haben und nicht für eines der unsäglichen Konkurrenzprodukte, die nicht im Ansatz an unser an Innovation nicht zu überbietendes Feuerwerk an bla, bla, bla, bla (Marketingfuzzi) ..." );
		System.out.println( "Spiel beendet." );
		System.exit( 0 );
	}
}
