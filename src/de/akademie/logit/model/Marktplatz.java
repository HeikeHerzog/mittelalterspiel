package de.akademie.logit.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Marktplatz
{
	private static Marktplatz marktplatz;

	private List<Spieler> spieler = new ArrayList<Spieler>();
	private int indexSpieler = 0;
	private Spieler startSpieler;
	private Spieler aktiverSpieler;
	// private int soldaten;
	private int soldatenPreis = 5;
	private int soldatenSold = 3;
	private static int rundenzaehler = 1;
	private Map<Integer, Spieler> opfermap = new HashMap<Integer, Spieler>();;
	private int preisKorn = 1;
	private int mengeKornAnfang = 1000;
	private int mengeKornEnde = 1000;
	private int preisMehl = 2;
	private int mengeMehlAnfang = 1000;
	private int mengeMehlEnde = 1000;
	private int preisDuenger = 1;
	private int preisGebaeude = 10;
	private int preisLand = 20;

	private Marktplatz()
	{}

	public static synchronized Marktplatz getInstance()
	{
		if ( marktplatz == null )
			marktplatz = new Marktplatz();
		return marktplatz;
	}

	public void fuegeSpielerHinzu( Spieler spieler )
	{
		this.spieler.add( spieler );
	}

	public Spieler ermittleStartSpieler( int anzSpieler )
	{
		this.indexSpieler = (int) ( Math.random() * anzSpieler );
		this.startSpieler = this.spieler.get( this.indexSpieler );
		return this.startSpieler;
	}

	public void setAktivenSpieler( Spieler spieler )
	{
		this.aktiverSpieler = spieler;
	}

	public Spieler getAktivenSpieler()
	{
		return this.aktiverSpieler;
	}

	public void setNaechstenSpieler()
	{
		this.indexSpieler++;
		if ( this.indexSpieler >= this.spieler.size() )
		{
			this.indexSpieler = 0;
		}
		this.aktiverSpieler = this.spieler.get( this.indexSpieler );
	}

	// 15.11.2013
	public boolean kaufeLand( int menge, int gold )
	{
		int gesPreis = menge * this.preisLand;
		if ( gesPreis <= gold )
		{ // gold reicht aus um die Anzahl an Land zu erwerben
			for ( int i = 0; i < menge; i++ )
			{
				Land neuesLand = new Land();
				this.aktiverSpieler.saldiereGold( this.preisLand * ( -1 ) );
				fuegeSpielerLandHinzu( this.aktiverSpieler, neuesLand );
			}
			return true;
		}
		else
			return false;
	}

	// 15.11.2013
	public void fuegeSpielerLandHinzu( Spieler aktiverSpieler, Land land )
	{
		aktiverSpieler.getLandListe().add( land );
	}

	// 18.11.2013
	public boolean zerstoereGebaeude( int auswahl )
	{
		boolean gefunden = false;
		gefunden = this.aktiverSpieler.zerstöreGesuchtesGebaeude( auswahl );

		if ( gefunden )
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	// 15.11.2013
	public boolean kaufeGebaeude( int auswahl, int gold, Land freiesLand )
	{
		switch ( auswahl )
		{
			case 0: // kein Kauf -> zurück zum Gebäude bearbeiten
				break;
			case 1: // Feld kaufen
				if ( gold >= this.preisGebaeude )
				{
					Feld neuesFeld = new Feld();
					this.aktiverSpieler.besetzeLandMitGebaeude( neuesFeld,
					      freiesLand );
					this.aktiverSpieler.saldiereGold( this.preisGebaeude * ( -1 ) ); // Gold
					                                                                 // beim
					                                                                 // Spieler
					                                                                 // abziehen
					return true;
				}
				break;
			case 2: // Muehle kaufen
				if ( gold >= this.preisGebaeude )
				{
					Muehle neueMuehle = new Muehle();
					this.aktiverSpieler.besetzeLandMitGebaeude( neueMuehle,
					      freiesLand );
					this.aktiverSpieler.saldiereGold( this.preisGebaeude * ( -1 ) );
					return true;
				}
				break;

			case 3: // Kornkammer kaufen
				if ( gold >= this.preisGebaeude )
				{
					Kornkammer neueKornkammer = new Kornkammer();
					this.aktiverSpieler.besetzeLandMitGebaeude( neueKornkammer,
					      freiesLand );
					this.aktiverSpieler.saldiereGold( this.preisGebaeude * ( -1 ) );
					return true;
				}
				break;

			default:
				break;
		}

		return false;
	}

	// 15.11.2013 18.11.2013
	public boolean kaufeSoldaten( int anzahl, int gold )
	{
		if ( gold < ( this.soldatenPreis * anzahl ) )
		{
			return false; // Gold reicht nicht -> zurück zu Soldaten bearbeiten
		}
		else
		{
			this.aktiverSpieler.saldiereSoldaten( anzahl ); // Soldaten des aktiven
			                                                // Spielers erhöhen
			this.aktiverSpieler
			      .saldiereGold( this.soldatenPreis * anzahl * ( -1 ) ); // Gold
			                                                             // vom
			                                                             // Spieler
			                                                             // abziehen
			return true;
		}
	}

	// 15.11.2013
	public boolean handelswareKaufen( int auswahl, int menge, int gold )
	{
		if ( auswahl == 0 || menge == 0 )
		{
			return false;
		}
		else
		{
			switch ( auswahl )
			{
				case 1: // Korn kaufen
					if ( gold >= ( menge * this.preisKorn ) )
					{
						this.aktiverSpieler.saldiereHandelsware( auswahl, menge ); // Kornmenge
						                                                           // beim
						                                                           // Spieler
						                                                           // erhöhen
						this.aktiverSpieler.saldiereGold( menge * this.preisKorn
						      * ( -1 ) ); // Gold wird abgezogen
						saldiereHandelsware( auswahl, menge * ( -1 ) ); // auf dem
						                                                // Marktplatz
						                                                // wird die
						                                                // Kornmenge
						                                                // verringert
						return true;
					}
					break;
				case 2: // Mehl kaufen
					if ( gold >= ( menge * this.preisMehl ) )
					{
						this.aktiverSpieler.saldiereHandelsware( auswahl, menge ); // Mehlmenge
						                                                           // beim
						                                                           // Spieler
						                                                           // erhöhen
						this.aktiverSpieler.saldiereGold( menge * this.preisMehl
						      * ( -1 ) ); // Gold beim Spieler abziehen
						saldiereHandelsware( auswahl, menge * ( -1 ) ); // Mehl auf
						                                                // dem
						                                                // Marktplatz
						                                                // erhöhen
						return true;
					}
					break;
				case 3: // Dünger kaufen
					if ( gold >= ( menge * this.preisDuenger ) )
					{
						this.aktiverSpieler.saldiereHandelsware( auswahl, menge ); // Dünger
						                                                           // beim
						                                                           // Spieler
						                                                           // erhöhen
						this.aktiverSpieler.saldiereGold( menge * this.preisDuenger
						      * ( -1 ) ); // Gold beim Spieler abziehen
						return true;
					}
					break;
				default:
					break;
			}

			return false;
		}
	}

	// 15.11.2013
	public boolean handelswareVerkaufen( int auswahl, int menge )
	{
		if ( auswahl == 0 || menge == 0 )
		{
			return false; // Abbruch
		}
		else if ( auswahl == 1 )
		{ // Korn verkaufen
			if ( this.aktiverSpieler.getKorn() >= menge )
			{ // Spieler hat genügend Korn zum Verkaufen
				this.aktiverSpieler.saldiereHandelsware( auswahl, menge * ( -1 ) ); // Kornmenge
				                                                                    // beim
				                                                                    // Spieler
				                                                                    // verringern
				this.aktiverSpieler.saldiereGold( menge * this.preisKorn ); // Gold
				                                                            // des
				                                                            // Spielers
				                                                            // erhöhen
				saldiereHandelsware( auswahl, menge ); // Marktplatz bekommt Korn
				                                       // dazu
				return true;
			}
			else
				return false;
		}
		else if ( auswahl == 2 )
		{ // Mehl verkaufen
			if ( this.aktiverSpieler.getMehl() >= menge )
			{
				this.aktiverSpieler.saldiereHandelsware( auswahl, menge * ( -1 ) ); // Mehlmenge
				                                                                    // beim
				                                                                    // Spieler
				                                                                    // verringern
				this.aktiverSpieler.saldiereGold( menge * this.preisMehl ); // Spieler
				                                                            // bekommt
				                                                            // Gold
				                                                            // fürs
				                                                            // Mehl
				saldiereHandelsware( auswahl, menge ); // Marktplatz bekommt Mehl
				return true;
			}
			else
				return false;
		}

		return false;
	}

	public void saldiereHandelsware( int auswahl, int menge )
	{

		if ( auswahl == 1 )
		{
			this.mengeKornEnde += menge;
		}
		else if ( auswahl == 2 )
		{
			this.mengeMehlEnde += menge;
		}

	}

	public String holeSpielernamen()
	{
		String namen = new String();
		List<Spieler> spielerNamen = new ArrayList<Spieler>();

		for ( Spieler spieler : this.spieler )
		{
			spielerNamen.add( spieler );
		}

		spielerNamen.remove( this.aktiverSpieler );

		for ( int i = 0; i < spielerNamen.size(); i++ )
		{
			namen = namen + ( i + 1 ) + ". " + spielerNamen.get( i ).getName()
			      + "\n";

			this.opfermap.put( i + 1, spielerNamen.get( i ) );
		}

		return namen;
	}

	public int getSold()
	{
		return this.soldatenSold;
	}

	// Preise für Korn und Mehl werden im Verhältnis Anfangsbestand : Endbestand
	// ermittelt
	public void preiseAnpassen()
	{
		int unterschiedMehl = this.mengeMehlEnde - this.mengeMehlAnfang;
		int unterschiedKorn = this.mengeKornEnde - this.mengeKornAnfang;

		// wenn der Mehlendbestand sich um min. 50% des Anfangsbestandes erhöht
		// hat wird das Mehl ggf. billiger
		if ( unterschiedMehl >= ( (int) ( this.mengeMehlAnfang / 2 ) )
		      && ( this.preisMehl > 1 ) )
		{
			this.preisMehl = (int) ( this.preisMehl / 2 );
		}
		// wenn der Mehlendbestand kleiner als der Mehlanfangsbestand ist wird das
		// Mehl ggf. teurer
		// dafür muss sich der Mehlbestand um die hälfte des Anfangsbestandes
		// verringert haben
		else if ( ( unterschiedMehl < 0 )
		      && ( ( unterschiedMehl * -1 ) >= ( (int) ( this.mengeMehlAnfang / 2 ) ) ) )
		{
			this.preisMehl += (int) ( this.preisMehl / 2 );
		}

		// wenn der Kornbestand sich um min. 50% des Anfangsbestandes erhöht hat
		// wird das Korn ggf. billiger
		if ( unterschiedKorn >= ( (int) ( this.mengeKornAnfang / 2 ) )
		      && ( this.preisKorn > 1 ) )
		{
			this.preisKorn = (int) ( this.preisKorn / 2 );
		}
		// wenn der Kornendbestand kleiner als der Kornanfangsbestand ist wird das
		// Korn ggf. teurer
		// dafür muss sich der Kornbestand um die hälfte des Anfangsbestandes
		// verringert haben
		else if ( ( unterschiedKorn < 0 )
		      && ( ( unterschiedKorn * -1 ) >= ( (int) ( this.mengeKornAnfang / 2 ) ) ) )
		{
			this.preisKorn += (int) ( this.preisKorn / 2 );
		}
	}

	public void incRundenzaehler()
	{
		if ( this.aktiverSpieler.equals( this.startSpieler ) )
		{
			rundenzaehler += 1;
		}
	}

	public int getRundenzaehler()
	{
		return rundenzaehler;
	}

	public List<Spieler> getSpieler()
	{
		return this.spieler;
	}

	public void setSpieler( List<Spieler> spieler )
	{
		this.spieler = spieler;
	}

	public int getSoldatenPreis()
	{
		return this.soldatenPreis;
	}

	public int getSoldatenSold()
	{
		return this.soldatenSold;
	}

	public int getPreisKorn()
	{
		return this.preisKorn;
	}

	public int getMengeKornEnde()
	{
		return this.mengeKornEnde;
	}

	public int getPreisMehl()
	{
		return this.preisMehl;
	}

	public int getMengeMehlEnde()
	{
		return this.mengeMehlEnde;
	}

	public int getPreisDuenger()
	{
		return this.preisDuenger;
	}

	public int getPreisGebaeude()
	{
		return this.preisGebaeude;
	}

	public int getPreisLand()
	{
		return this.preisLand;
	}

	public Spieler getOpfer( int auswahl )
	{
		return this.opfermap.get( auswahl );
	}

}
