package de.akademie.logit.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.akademie.logit.model.Land;
import de.akademie.logit.model.Marktplatz;
import de.akademie.logit.model.Spieler;
import de.akademie.logit.view.Aktionsmenu;
import de.akademie.logit.view.Anzeige;
import de.akademie.logit.view.Gebaeudemenu;
import de.akademie.logit.view.Gebaeudeuntermenu;
import de.akademie.logit.view.Handelswarenmenu;
import de.akademie.logit.view.Handelswarenuntermenu;
import de.akademie.logit.view.Handelswarenverkaufsmenu;
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
				Marktplatz marktplatz = Marktplatz.getInstance();
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

			// [0..8]
			int eingabe = select( 8 );
			switch ( eingabe )
         {
				case 0:
					spielen = false;
					spielBeenden();
					break;
				case 1:
					Anzeige.zeigeMenuAn( aktiverSpieler, Landmenu.getInstance() );
					int auswahl = select( 1 );
				   // [0..1]
					switch ( auswahl )
               {
						case 0:
							// zurück zur Spielerhauptmenümaske
							break;
						case 1:
							// Land kaufen
							int gold = aktiverSpieler.getGold();
							if( gold < 1 )
							{
								Anzeige.zeigeStringAn( "Kein Gold - kein Land" );
								break;
							}
							else
							{
								int anzLandKauf = select( Integer.MAX_VALUE );
								boolean erfolgreich = this.marktplatz.kaufeLand( anzLandKauf, gold );
								if( erfolgreich )
								{
									Anzeige.zeigeStringAn( "Landkauf erfolgreich." );
								}
								else
								{
									Anzeige.zeigeStringAn( "Nicht genügend Gold." );
								}
							}
							break;
					}
					break;
				case 2:	
					Anzeige.zeigeMenuAn( aktiverSpieler, Gebaeudemenu.getInstance() );
					
					int auswahl2 = select( 2 );
					   // [0..2]
						switch ( auswahl2 ) {
						case 0:
							// zurück zur Spielerhauptmenümaske
							break;
						case 1: 		// Gebäude kaufen
							int gold = aktiverSpieler.getGold();
							Land freiesLand = aktiverSpieler.findeFreiesLand();
							if (gold < 1 || freiesLand==null) {
								Anzeige.zeigeStringAn("Kein Gold oder kein freies Land vorhanden");
							} else if (gold > 0 && freiesLand!=null) {
								Anzeige.zeigeMenuAn(aktiverSpieler, Gebaeudeuntermenu.getInstance());
								int was = select(3);
								
								if (was == 0) {
									// zurück
								} else {
									boolean erfolgreich = this.marktplatz.kaufeGebaeude(was, gold, freiesLand);
									if( erfolgreich ) {
										Anzeige.zeigeStringAn( "Gebäudekauf erfolgreich." );
									}
									else {
										Anzeige.zeigeStringAn( "Nicht genügend Gold." );
									}	
								}
							}
							break;
						case 2: 	// Gebäude zerstören
							Anzeige.zeigeMenuAn(aktiverSpieler, Gebaeudeuntermenu.getInstance());
							int was  = select(3);
							boolean erfolgreich = this.marktplatz.zerstoereGebaeude(was);
							if (erfolgreich) {
								Anzeige.zeigeStringAn("Gebäude zerstört");	
							} else {
								Anzeige.zeigeStringAn("Gebäude nicht zerstört");
							}
							
							break;
						
						
						}
					
					break;
				case 3:
					Anzeige.zeigeMenuAn( aktiverSpieler, Soldatenmenu.getInstance() );
					
					int auswahl3 = select(2);
					switch ( auswahl3 ) {
					case 0:
						// zurück zur Spielerhauptmenümaske
						break;
					case 1: 		// Soldaten kaufen
						int gold = aktiverSpieler.getGold();
						if (gold < 1 ) {
							Anzeige.zeigeStringAn("Kein Gold keine Soldaten");
						} else {
							
							
							int anzSoldatenKauf = select( Integer.MAX_VALUE );
							boolean erfolgreich = this.marktplatz.kaufeSoldaten(anzSoldatenKauf, gold);
							
							if( erfolgreich )
							{
								Anzeige.zeigeStringAn( "Soldaten eingestellt." );
							}
							else
							{
								Anzeige.zeigeStringAn( "Nicht genügend Gold." );
							}
						}
						
						break;
					case 2: 	// Soldaten entlassen
						
						int anzSoldatenEntlassen = select( Integer.MAX_VALUE );
						int anzSoldaten = aktiverSpieler.getSoldaten();
						if (anzSoldatenEntlassen <= anzSoldaten) {
							aktiverSpieler.saldiereSoldaten(anzSoldatenEntlassen * (-1));
							Anzeige.zeigeStringAn("Soldaten entlassen");
						} else {
							Anzeige.zeigeStringAn("Nicht genügend Soldaten");
						}
						
						
					}
					
					break;
				case 4:
					Anzeige.zeigeMenuAn( aktiverSpieler, Handelswarenmenu.getInstance() );
					
					int auswahl4 = select(2);
					switch ( auswahl4 ) {
					case 0:
						// zurück zur Spielerhauptmenümaske
						break;
					case 1: 		// Handelsware kaufen
						int gold = aktiverSpieler.getGold();
						if (gold < 1 ) {
							Anzeige.zeigeStringAn("Kein Gold keine Handelsware");
						} else {
							Anzeige.zeigeMenuAn(aktiverSpieler, Handelswarenuntermenu.getInstance());
							int welcheHandelsware = select (3);		
							
							if (welcheHandelsware == 0) {
								break;   // Spieler hat 0 eingegeben -> Abbruch
								
							} else {
								
								int anzHandelswarenKauf = select( Integer.MAX_VALUE );
								
								boolean erfolgreich = this.marktplatz.handelswareKaufen(welcheHandelsware, anzHandelswarenKauf, gold);
								if( erfolgreich ) {
									Anzeige.zeigeStringAn( "Handelsware gekauft" );
								}
								else {
									Anzeige.zeigeStringAn( "Nicht genügend Gold." );
								}
							
							}
						}
						
					case 2: 	// Handelsware verkaufen
						int spielergold = aktiverSpieler.getGold();
						
						Anzeige.zeigeMenuAn(aktiverSpieler, Handelswarenverkaufsmenu.getInstance());
						int welchehandelsWare = select (2);
						
						if (welchehandelsWare == 0) {
							break; // Spieler hat 0 eingegeben -> Abbruch
						}
						else {
							
							int anzHandelswarenkauf = select(Integer.MAX_VALUE);
							
							boolean erfolgreich = this.marktplatz.handelswareVerkaufen(welchehandelsWare, anzHandelswarenkauf, spielergold);
							if (erfolgreich) {
								Anzeige.zeigeStringAn("Handelsware verkauft!");
							}
							else {
								Anzeige.zeigeStringAn("Nicht genügend von dieser Ware, um die gewünschte Menge zu verkaufen!");
							}
						}
						
					}
						
						
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
			}   // switch ( eingabe )
		}   // while

	}

	// liefert für die Menüs die eingegebene Auswahl von [0..maxEingabe] zurück. 
	private int select( int maxEingabe )
   {
		int eingabe = -1;
	   while ( eingabe < 0 || eingabe > maxEingabe )
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

	   return eingabe;
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
