package de.akademie.logit.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.akademie.logit.model.Marktplatz;
import de.akademie.logit.model.Spieler;
import de.akademie.logit.view.Aktionsmenu;
import de.akademie.logit.view.Anzeige;
import de.akademie.logit.view.Gebaeudemenu;
import de.akademie.logit.view.Handelswarenmenu;
import de.akademie.logit.view.Landmenu;
import de.akademie.logit.view.Sabotagemenu;
import de.akademie.logit.view.Soldatenmenu;
import de.akademie.logit.view.Spielerhauptmenumaske;

/**
 * 
 * @author jutta, paul
 * 
 */
public class EingabeController
{
	private Marktplatz marktplatz;
	private SabotageaktController sabotageakt;
	int anzSpieler;

	public EingabeController()
	{}

	public void initialisieren()
	{
		this.anzSpieler = -1;

		while ( this.anzSpieler < 0 || this.anzSpieler > 4 )
		{
			Anzeige.zeigeStringAn( "Anzahl der Spieler ( 0 bis 4 ) eingeben:" );
			try
			{
				this.anzSpieler = Integer.parseInt( getEingabe() );
			}
			catch ( NumberFormatException nfEx )
			{
				System.out.println( "Es wurde keine Zahl eingegeben." );
			}
		}

		switch ( this.anzSpieler )
		{
			case 0:
				spielBeenden();
				break;

			default:
				Marktplatz marktplatz = new Marktplatz();
				setMarktplatz( marktplatz );

				// erfassen der Spieler
				String spielerName = "";
				for ( int i = 1; i <= this.anzSpieler; i++ )
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
		
			Spieler startSpieler = this.marktplatz.ermittleStartSpieler( this.anzSpieler );
			this.marktplatz.setAktivenSpieler( startSpieler );
			
	}

	public void loop()
	{
		boolean spielen = true;
		while( spielen )
		{
			Spieler aktiverSpieler = this.marktplatz.getAktivenSpieler();
			EreignisController ereignisController = new EreignisController( aktiverSpieler );
			Anzeige.zeigeMenuAn( aktiverSpieler, Spielerhauptmenumaske.getInstance() );
			int eingabe = -1;

			while ( eingabe < 0 || eingabe > 8 )
			{
				Anzeige.zeigeStringAn( "Auswahl eingeben:" );
				try
				{
					eingabe = Integer.parseInt( getEingabe() );
				}
				catch ( NumberFormatException nfEx )
				{
					System.out.println( "Es wurde keine Zahl eingegeben." );
				}
			}
			
			switch ( eingabe )
         {
				case 0:
					spielen = false;
					spielBeenden();
					break;
				case 1:
					Anzeige.zeigeMenuAn( aktiverSpieler, Landmenu.getInstance() );
					
					break;
				case 2:
					Anzeige.zeigeMenuAn( aktiverSpieler, Gebaeudemenu.getInstance() );
					
					break;
				case 3:
					Anzeige.zeigeMenuAn( aktiverSpieler, Soldatenmenu.getInstance() );
					
					break;
				case 4:
					Anzeige.zeigeMenuAn( aktiverSpieler, Handelswarenmenu.getInstance() );
					
					break;
				case 5:
					Anzeige.zeigeMenuAn( aktiverSpieler, Sabotagemenu.getInstance() );
					
					break;
				case 6:
					Anzeige.zeigeMenuAn( aktiverSpieler, Aktionsmenu.getInstance() );
					
					break;
				case 7:
					Anzeige.zeigeStringAn( "Hier Chat" );
					
					break;
				case 8:
					aktiverSpieler.setSabotage( false );
					aktiverSpieler.setTitelflag( false );
					spielzugBeenden();
					break;
				default:
					break;
			}
		}
		
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
		return this.marktplatz;
	}

	public void spielzugBeenden()
	{

	}

	public void spielBeenden()
	{
		System.out.println( "Vielen Dank, dass Sie sich für unser Produkt entschieden haben und nicht für eines der unsäglichen Konkurrenzprodukte, die nicht im Ansatz an unser an Innovation nicht zu überbietendes Feuerwerk an bla, bla, bla, bla (Marketingfuzzi) ..." );
		System.out.println( "Spiel beendet." );
		System.exit( 0 );
	}

}
