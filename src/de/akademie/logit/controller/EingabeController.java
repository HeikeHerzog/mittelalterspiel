package de.akademie.logit.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
	private EreignisController ereignisController;
	private Spieler aktiverSpieler;
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
				this.marktplatz = Marktplatz.getInstance();

				// erfassen der Spieler
				String spielerName = "";
				for ( int i = 1; i <= this.anzSpieler; i++ )
				{
					Anzeige.zeigeStringAn( i + ". Spielernamen eingeben:" );
					spielerName = getEingabe();
					// wenn keine Name eingegeben wurde, dann einfach den Zähler
					// nehmen
					if ( spielerName.trim().isEmpty() )
					{
						spielerName = i + ". Spieler";
					}
					Spieler spieler = new Spieler( spielerName );
					this.marktplatz.fuegeSpielerHinzu( spieler );
				}
				break;
		}

		Spieler startSpieler = this.marktplatz
		      .ermittleStartSpieler( this.anzSpieler );
		this.marktplatz.setAktivenSpieler( startSpieler );
	}

	public void loop()
	{
		boolean spielen = true;
		while ( spielen )
		{
			this.aktiverSpieler = this.marktplatz.getAktivenSpieler();
			this.ereignisController = new EreignisController( aktiverSpieler );
			Anzeige.zeigeMenuAn( aktiverSpieler,
			      Spielerhauptmenumaske.getInstance() );

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
							if ( gold < 1 )
							{
								Anzeige.zeigeStringAn( "Kein Gold - kein Land" );
								break;
							}
							else
							{
								int anzLandKauf = select( Integer.MAX_VALUE );
								boolean erfolgreich = this.marktplatz.kaufeLand(
								      anzLandKauf, gold );
								if ( erfolgreich )
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
					switch ( auswahl2 )
					{
						case 0:
							// zurück zur Spielerhauptmenümaske
							break;

						case 1: // Gebäude kaufen
							int gold = aktiverSpieler.getGold();
							Land freiesLand = aktiverSpieler.findeFreiesLand();
							if ( gold < 1 || freiesLand == null )
							{
								Anzeige
								      .zeigeStringAn( "Kein Gold oder kein freies Land vorhanden" );
							}
							else if ( gold > 0 && freiesLand != null )
							{
								Anzeige.zeigeMenuAn( aktiverSpieler,
								      Gebaeudeuntermenu.getInstance() );
								int was = select( 3 );

								if ( was == 0 )
								{
									// zurück
								}
								else
								{
									boolean erfolgreich = this.marktplatz.kaufeGebaeude(
									      was, gold, freiesLand );
									if ( erfolgreich )
									{
										Anzeige
										      .zeigeStringAn( "Gebäudekauf erfolgreich." );
									}
									else
									{
										Anzeige.zeigeStringAn( "Nicht genügend Gold." );
									}
								}
							}
							break;

						case 2: // Gebäude zerstören
							Anzeige.zeigeMenuAn( aktiverSpieler,
							      Gebaeudeuntermenu.getInstance() );
							int was = select( 3 );
							boolean erfolgreich = this.marktplatz
							      .zerstoereGebaeude( was );
							if ( erfolgreich )
							{
								Anzeige.zeigeStringAn( "Gebäude zerstört" );
							}
							else
							{
								Anzeige.zeigeStringAn( "Gebäude nicht zerstört" );
							}
							break;
					}
					break;

				case 3:
					Anzeige.zeigeMenuAn( aktiverSpieler, Soldatenmenu.getInstance() );

					int auswahl3 = select( 2 );
					switch ( auswahl3 )
					{
						case 0:
							// zurück zur Spielerhauptmenümaske
							break;
						case 1: // Soldaten kaufen
							int gold = aktiverSpieler.getGold();
							if ( gold < 1 )
							{
								Anzeige.zeigeStringAn( "Kein Gold keine Soldaten" );
							}
							else
							{

								int anzSoldatenKauf = select( Integer.MAX_VALUE );
								boolean erfolgreich = this.marktplatz.kaufeSoldaten(
								      anzSoldatenKauf, gold );

								if ( erfolgreich )
								{
									Anzeige.zeigeStringAn( "Soldaten eingestellt." );
								}
								else
								{
									Anzeige.zeigeStringAn( "Nicht genügend Gold." );
								}
							}

							break;

						case 2: // Soldaten entlassen

							int anzSoldatenEntlassen = select( Integer.MAX_VALUE );
							int anzSoldaten = aktiverSpieler.getSoldaten();
							if ( anzSoldatenEntlassen <= anzSoldaten )
							{
								aktiverSpieler.saldiereSoldaten( anzSoldatenEntlassen
								      * ( -1 ) );
								Anzeige.zeigeStringAn( "Soldaten entlassen" );
							}
							else
							{
								Anzeige.zeigeStringAn( "Nicht genügend Soldaten" );
							}
					}
					break;

				case 4:
					Anzeige.zeigeMenuAn( aktiverSpieler,
					      Handelswarenmenu.getInstance() );

					int auswahl4 = select( 2 );
					switch ( auswahl4 )
					{
						case 0:
							// zurück zur Spielerhauptmenümaske
							break;
						case 1: // Handelsware kaufen
							int gold = aktiverSpieler.getGold();
							if ( gold < 1 )
							{
								Anzeige.zeigeStringAn( "Kein Gold keine Handelsware" );
							}
							else
							{
								Anzeige.zeigeMenuAn( aktiverSpieler,
								      Handelswarenuntermenu.getInstance() );
								int welcheHandelsware = select( 3 );

								if ( welcheHandelsware == 0 )
								{
									break; // Spieler hat 0 eingegeben -> Abbruch

								}
								else
								{

									int anzHandelswarenKauf = select( Integer.MAX_VALUE );

									boolean erfolgreich = this.marktplatz
									      .handelswareKaufen( welcheHandelsware,
									            anzHandelswarenKauf, gold );
									if ( erfolgreich )
									{
										Anzeige.zeigeStringAn( "Handelsware gekauft" );
									}
									else
									{
										Anzeige.zeigeStringAn( "Nicht genügend Gold." );
									}

								}
							}
							break;

						case 2: // Handelsware verkaufen
							int spielergold = aktiverSpieler.getGold();

							Anzeige.zeigeMenuAn( aktiverSpieler,
							      Handelswarenverkaufsmenu.getInstance() );
							int welchehandelsWare = select( 2 );

							if ( welchehandelsWare == 0 )
							{
								break; // Spieler hat 0 eingegeben -> Abbruch
							}
							else
							{

								int anzHandelswarenkauf = select( Integer.MAX_VALUE );

								boolean erfolgreich = this.marktplatz
								      .handelswareVerkaufen( welchehandelsWare,
								            anzHandelswarenkauf, spielergold );
								if ( erfolgreich )
								{
									Anzeige.zeigeStringAn( "Handelsware verkauft!" );
								}
								else
								{
									Anzeige
									      .zeigeStringAn( "Nicht genügend von dieser Ware, um die gewünschte Menge zu verkaufen!" );
								}
							}
					}
					break;

				case 5:
					if (this.aktiverSpieler.isSaboteur()) {
						Anzeige.zeigeStringAn("Diese Runde ist keine Sabotage möglich!");
					}
					else if (!this.aktiverSpieler.isSaboteur()) {
						
						Anzeige.zeigeStringAn("Wer soll sabotiert werden?");
						String opferliste = this.marktplatz.holeSpielernamen();
						Anzeige.zeigeStringAn(opferliste);
						int auswahl5 = select(this.anzSpieler-1);
						
						
						
						
									
						Anzeige.zeigeMenuAn( aktiverSpieler, Sabotagemenu.getInstance() );

						
						
						
						
						
					}
					break;
					
				case 6:
					Anzeige.zeigeMenuAn( aktiverSpieler, Aktionsmenu.getInstance() );

					int auswahl6 = select( 4 );
					switch ( auswahl6 )
					{
						case 0:
							// zurück zum Hauptmenu
							break;
						case 1: // Steuersatz ändern

							int neuerSteuersatz = select( 100 );
							aktiverSpieler.setSteuersatz( neuerSteuersatz );

							break;
						case 2: // Titel erwerben

							boolean nochKeinenTitelErworben = aktiverSpieler
							      .getTitelflag();
							if ( !nochKeinenTitelErworben )
							{
								int indexTitel = aktiverSpieler.getIndexTitel();
								int gold = aktiverSpieler.getGold();
								ArrayList<Land> laendereien = aktiverSpieler
								      .getLandListe();
								boolean erfolgreich = aktiverSpieler.titelErwerben(
								      indexTitel + 1, gold, laendereien );
								if ( erfolgreich )
								{
									Anzeige.zeigeStringAn( "Neuen Titel erworben" );
								}
								else
								{
									Anzeige
									      .zeigeStringAn( "Nicht genügend Gold oder Land vorhanden um diesen Titel zu erwerben" );
								}

							}
							else
							{
								Anzeige
								      .zeigeStringAn( "In dieser Runde kann kein Titel mehr erworben werden" );
							}
							break;
						case 3: // Essensrationen ändern
							Anzeige
							      .zeigeStringAn( "Essensration eingeben: 1=halb, 2=voll, 3=doppelt" );
							int neueEssensration = select( 3 );
							aktiverSpieler.setEssensration( neueEssensration );
							Anzeige.zeigeStringAn( "Essensration wurde geändert" );

							break;
						case 4: // Korn mahlen
							int muehleVorhanden = aktiverSpieler.zaehleGebaeude( 2 );

							if ( muehleVorhanden > 0 )
							{
								int wievielKornMahlen = select( Integer.MAX_VALUE );
								int kornVomSpieler = aktiverSpieler.getKorn();
								if ( kornVomSpieler >= wievielKornMahlen )
								{
									// genug Korn zum Mahlen vorhanden
									aktiverSpieler.saldiereKorn( wievielKornMahlen );
									aktiverSpieler.setMehl( wievielKornMahlen );
									Anzeige.zeigeStringAn( "Korn mahlen erfolgreich" );
								}
								else
								{
									Anzeige.zeigeStringAn( "Nicht genug Korn" );
								}
							}
							else
							{
								Anzeige.zeigeStringAn( "Keine Mühle vorhanden" );
							}
							break;
					}
					break;

				case 7:
					Anzeige.zeigeStringAn( "Heute kein Chat" );

					break;

				case 8:
					spielzugBeenden();
					break;
			} // switch ( eingabe )
		} // while
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

	public Marktplatz getMarktplatz()
	{
		return this.marktplatz;
	}

	public void spielzugBeenden()
	{
		this.aktiverSpieler.setSabotage( false );
		this.aktiverSpieler.setTitelflag( false );
		this.ereignisController.ereignisTrigger();
		this.aktiverSpieler.soldatenVersorgen( this.marktplatz.getSold() ); // Soldaten
																								  // versorgen
																								  // (füttern
																								  // und
																								  // bezahlen)
		this.aktiverSpieler.bevoelkerungFuettern();
		this.aktiverSpieler.kornErntenUndVerteilen();
		this.aktiverSpieler.zufriedenheitAnpassen();
		this.marktplatz.preiseAnpassen();
		this.marktplatz.setNaechstenSpieler();
		this.marktplatz.incRundenzaehler();
	}

	public void spielBeenden()
	{
		System.out
		      .println( "Vielen Dank, dass Sie sich für unser Produkt entschieden haben und nicht für eines der unsäglichen Konkurrenzprodukte, die nicht im Ansatz an unser an Innovation nicht zu überbietendes Feuerwerk an bla, bla, bla, bla (Marketingfuzzi) ..." );
		System.out.println( "Spiel beendet." );
		System.exit( 0 );
	}

}
