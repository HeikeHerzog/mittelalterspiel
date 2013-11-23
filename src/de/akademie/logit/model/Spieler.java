package de.akademie.logit.model;

import java.util.ArrayList;

import de.akademie.logit.controller.EreignisController;

public class Spieler
{
	private String name;
	private Titel titel = new Titel();
	// initialer Titelindex wird im Constructor um eins inc
	private int indexTitel = -1;
	private int gold = 10000;
	private ArrayList<Land> laendereien = new ArrayList<Land>();
	private int soldaten;
	private int steuersatz;
	private int mehl;
	private int korn;
	private int duenger;
	private int essensration = 2;
	private int bevoelkerungsanzahl = 50;
	private Marktplatz marktplatz;
	private EreignisController ereignis;
	private boolean sabotageflag = true;
	private boolean sabotageOpfer = false;
	private boolean titelflag = false;
	private int bevoelkerungszufriedenheit = 10;

	public Spieler()
	{}

	public Spieler( String _name )
	{
		this.name = _name;
		// erste Stufe: Bauer
		setNextTitel();

		this.marktplatz = Marktplatz.getInstance();

		// initial hat jeder Spieler 3 Land mit je einem Gebäude
		Land land = new Land();
		land.setGebaeude( new Feld() );
		this.marktplatz.fuegeSpielerLandHinzu( this, land );

		land = new Land();
		land.setGebaeude( new Kornkammer() );
		this.marktplatz.fuegeSpielerLandHinzu( this, land );

		land = new Land();
		land.setGebaeude( new Muehle() );
		this.marktplatz.fuegeSpielerLandHinzu( this, land );
		
		this.soldaten = 0;
	}

	public String getName()
	{
		return this.name;
	}

	public boolean titelErwerben( int indexTitel, int gold,
	      ArrayList<Land> laendereien )
	{
		// ist bereits König
		if( this.titel.getTitel( this.indexTitel ).equals("Koenig") )
		{
			return false;
		}

		int titelKostet = titel.getGold() * indexTitel; // Titelkosten
		int benoetigteLaender = titel.getLandmin() * indexTitel; // man benötigt
		                                                         // für jeden
		                                                         // Titel
		                                                         // bestimmte
		                                                         // Anzahl an
		                                                         // Ländern

		if ( titelKostet <= gold && benoetigteLaender <= laendereien.size() )
		{
			// genug Gold und genug Land für den nächsten Titel
			setNextTitel();
			// this.gold
			this.saldiereGold( titelKostet * ( -1 ) );
			this.setTitelflag( true );
			return true;
		}

		// nicht gnügend Gold oder Land
		return false;
	}

	public int getGold()
	{
		return this.gold;
	}

	public int getSoldaten()
	{
		return this.soldaten;
	}

	public void saldiereGold( int menge )
	{

		this.gold = this.gold + menge;
	}

	public ArrayList<Land> getLandListe()
	{
		return this.laendereien;
	}

	public String getBesitz()
	{
		String besitz = new String();

		if ( this.laendereien.size() == 0 )
		{
			return "Kein Land vorhanden!";
		}

		for ( Land land : this.laendereien )
		{
			besitz = besitz
			      + ( land.getGebaeude() == null ? "ohne" : land.getGebaeude()
			            .getBezeichnung() ) + " ";
		}

		return "Gebäude pro " + new Land().getBezeichnung() + ":    " + besitz;
	}

	public Land findeFreiesLand()
	{

		for ( int i = 0; i < laendereien.size(); i++ )
		{

			if ( laendereien.get( i ).getGebaeude() == null )
			{

				return laendereien.get( i );
			}
		}

		return null;
	}

	public void besetzeLandMitGebaeude( Gebaeude gebaeude, Land freiesLand )
	{
		freiesLand.setGebaeude( gebaeude );
	}

	public boolean zerstöreGesuchtesGebaeude( int auswahl )
	{
		boolean retVal = false;

		for ( int i = 0; i < this.laendereien.size(); i++ )
		{
			switch ( auswahl )
			{
				case 1: // eigenes Feld finden und zerstören
					if ( this.laendereien.get( i ).getGebaeude() == null )
					{
						break;
					}

					if ( this.laendereien.get( i ).getGebaeude().getBezeichnung()
					      .equals( "Feld" ) )
					{
						this.laendereien.get( i ).setGebaeude( null );
						return retVal = true;
					}
					break;

				case 2: // eigene Muehle finden und zerstören
					if ( this.laendereien.get( i ).getGebaeude() == null )
					{
						break;
					}

					if ( this.laendereien.get( i ).getGebaeude().getBezeichnung()
					      .equals( "Mühle" ) )
					{
						this.laendereien.get( i ).setGebaeude( null );
						return retVal = true;
					}
					break;

				case 3: // eigene Kornkammer finden und zerstören
					if ( this.laendereien.get( i ).getGebaeude() == null )
					{
						break;
					}

					if ( this.laendereien.get( i ).getGebaeude().getBezeichnung()
					      .equals( "Kornkammer" ) )
					{
						this.laendereien.get( i ).setGebaeude( null );
						return retVal = true;
					}
					break;
			}
		}

		return retVal;
	}

	public void saldiereSoldaten( int anzahl )
	{
		this.soldaten += anzahl;
	}

	public void saldiereHandelsware( int auswahl, int menge )
	{
		switch ( auswahl )
		{
			case 1: // Korn
				this.korn += menge;
				break;
			case 2: // Mehl
				this.mehl += menge;
				break;
			case 3: // Dünger
				this.duenger += menge;
				break;
		}
	}

	// diese Methode nur fürs Korn mahlen verwenden
	public void saldiereKorn( int mahlMenge )
	{
		this.korn -= mahlMenge;
	}

	public int getKorn()
	{
		return this.korn;
	}

	public int getMehl()
	{
		return this.mehl;
	}

	public boolean isSaboteur()
	{

		return this.sabotageflag;
	}

	public void setSabotage( boolean _sabotageflag )
	{
		this.sabotageflag = _sabotageflag;
	}

	public void sabotageDurchfuehren()
	{}

	public int getSteuersatz()
	{
		return this.steuersatz;
	}

	public void setSteuersatz( int _steuersatz )
	{
		this.steuersatz = _steuersatz;
	}

	public void setNextTitel()
	{
		this.indexTitel = this.indexTitel + 1;
	}

	public int getIndexTitel()
	{
		return this.indexTitel;
	}

	public String getTitel()
	{
		return this.titel.getTitel( this.indexTitel );
	}

	/*
	 * Essensrationen können halb, voll oder doppelt sein. Eine volle
	 * Essensration sind 2 Einheiten Mehl. Eine halbe Essensration ist 1 Einheit
	 * Mehl. Eine doppelte Essensration sind 4 Einheiten Mehl. Soldaten bekommen
	 * immer die volle Essensration, also 2 Einheiten Mehl
	 */
	public void setEssensration( int rationssatz )
	{
		if ( rationssatz == 1 )
		{
			this.essensration = 1;
		}
		else if ( rationssatz == 2 )
		{
			this.essensration = 2;
		}
		else if ( rationssatz == 3 )
		{
			this.essensration = 4;
		}
	}

	public int getEssensration()
	{
		return this.essensration;
	}

	public String getEssensrationText()
	{
		String ration = "";

		if ( this.essensration == 1 )
			ration = "halb";
		if ( this.essensration == 2 )
			ration = "voll";
		if ( this.essensration == 4 )
			ration = "doppelt";

		return ration;
	}

//	public void setMehl( int menge )
//	{
//		this.mehl = menge;
//	}

	public void setKorn( int menge )
	{
		this.korn = menge;
	}

	public void setSabotageOpfer( boolean _sabotageOpfer )
	{}

	public boolean isSabotageOpfer()
	{
		return this.sabotageOpfer;
	}

	public int ermittleGoldBetrag( int inProzent )
	{
		int goldbetrag = (int) ( this.gold * inProzent ) / 100;

		return goldbetrag;
	}

	public int ermittleKornMenge( int inProzent )
	{
		int kornmenge = (int) ( this.korn * inProzent ) / 100;

		return kornmenge;

	}

	public int ermittleSoldatenAnzahl( int inProzent )
	{
		int soldatenanzahl = (int) ( this.soldaten * inProzent ) / 100;

		return soldatenanzahl;
	}

	public int getBevoelkerungsanzahl()
	{
		return this.bevoelkerungsanzahl;
	}

	public void setBevoelkerungsanzahl( int _bevoelkerungsanzahl )
	{
		this.bevoelkerungsanzahl = _bevoelkerungsanzahl;
	}

	public void steuernEintreiben()
	{
		this.gold = ( this.gold * this.steuersatz / 100 ) + this.gold;
	}

	/*
	 * Wenn Soldaten nicht genug zu essen haben oder nicht bezahlt werden können,
	 * wandert die "unterversorgte" Anzahl von Soldaten ab. Soldaten bekommen
	 * immer die volle Essensration von 2 Mehleinheiten als Nahrung.
	 */
	public void soldatenVersorgen( int sold )
	{
		int benötigtesMehl = this.soldaten * 2;
		int benötigtesGold = this.soldaten * sold;

		int satteSoldaten = Integer.valueOf( Math.round( this.mehl / 2 ) );
		int hungrigeSoldaten = this.soldaten - satteSoldaten;

		int bezahlteSoldaten = Integer.valueOf( Math.round( this.gold / sold ) );
		int unbezahlteSoldaten = this.soldaten - bezahlteSoldaten;

		if ( ( benötigtesMehl > this.mehl ) || ( benötigtesGold > this.gold ) )
		{
			if ( hungrigeSoldaten > unbezahlteSoldaten )
			{
				this.soldaten = this.soldaten - hungrigeSoldaten;
				this.mehl = 0;
				this.gold = this.gold - benötigtesGold;
			}
			else if ( hungrigeSoldaten <= unbezahlteSoldaten )
			{
				this.soldaten = this.soldaten - unbezahlteSoldaten;
				this.mehl = this.mehl - benötigtesMehl;
				this.gold = 0;
			}
		}
		else if ( ( benötigtesMehl <= this.mehl )
		      && ( benötigtesGold <= this.gold ) )
		{
			this.mehl = this.mehl - benötigtesMehl;
			this.gold = this.gold - benötigtesGold;
		}
	}

	/*
	 * Wenn die Bevölkerung nicht genug zu essen hat, wandern die hungrigen
	 * Bewohner ab
	 */
	public void bevoelkerungFuettern()
	{
		int benötigtesMehl = this.bevoelkerungsanzahl * this.essensration;

		if ( benötigtesMehl < this.mehl )
		{
			int satteBevoelkerung = Integer.valueOf( Math.round( this.mehl
			      / this.essensration ) );
			this.bevoelkerungsanzahl = this.bevoelkerungsanzahl
			      - ( this.bevoelkerungsanzahl - satteBevoelkerung );
		}
		this.mehl = 0; // Mehl wird bei Spielzug beenden immer auf 0 gesetzt, da
		               // übriges Mehl verdirbt
	}

	public int zaehleGebaeude( int auswahl )
	{
		int anzahl = 0;

		for ( int i = 0; i < this.laendereien.size(); i++ )
		{
			switch ( auswahl )
			{
				case 1: // Felder zaehlen - wenn kein Gebäude drauf steht, nächstes
					     // Land aus der Liste nehmen
					if ( this.laendereien.get( i ).getGebaeude() == null )
					{
						break;
					}

					if ( this.laendereien.get( i ).getGebaeude().getBezeichnung() == "Feld" )
					{
						anzahl++;
					}
					break;

				case 2: // Muehlen zaehlen
					if ( this.laendereien.get( i ).getGebaeude() == null )
					{
						break;
					}

					if ( this.laendereien.get( i ).getGebaeude().getBezeichnung() == "Mühle" )
					{
						anzahl++;
					}
					break;

				case 3: // Kornkammern zaehlen
					if ( this.laendereien.get( i ).getGebaeude() == null )
					{
						break;
					}

					if ( this.laendereien.get( i ).getGebaeude().getBezeichnung() == "Kornkammer" )
					{
						anzahl++;
					}
					break;

				default:
					break;
			}
		}
		return anzahl;
	}

	/*
	 * Pro Feld werden 50 Korn geerntet. Wenn genügend Dünger vorhanden ist, kann
	 * die zu erntende Menge an Korn verdoppelt werden. Ist Dünger nur anteilig
	 * vorhanden, wird die zur erntende Menge entsprechend anteilig verdoppelt.
	 * Korn, dass ausserhalb einer Kornkammer gelagert werden muss, verdirbt zu
	 * 50%.
	 */
	public void kornErntenUndVerteilen()
	{
		int anzahlFelder = zaehleGebaeude( 1 );
		int anzahlKornkammern = zaehleGebaeude( 3 );
		int maxLagerMengeInKornkammer = new Kornkammer().getMaxLagermenge();
		int ernteMitDuenger = 0;
		int benoetigteKornkammern = 0;

		int ernte = anzahlFelder * new Feld().getKornProRunde();

		if ( this.getDuenger() >= ernte )
		{
			ernteMitDuenger = ernte * 2;
		}

		else if ( this.getDuenger() < ernte )
		{
			ernteMitDuenger = ernte + this.getDuenger();
		}

		this.korn = this.korn + ernteMitDuenger;

		if ( this.duenger - ernte < 0 )
		{
			this.duenger = 0;
		}
		else
		{
			this.duenger = this.duenger - ernte;
		}

		benoetigteKornkammern = (int) ( this.korn / maxLagerMengeInKornkammer );

		if ( anzahlKornkammern < benoetigteKornkammern )
		{
			int kornInnerhalbDerKornkammern = anzahlKornkammern
			      * maxLagerMengeInKornkammer;
			int kornAusserhalbDerKornkammern = this.korn
			      - kornInnerhalbDerKornkammern;
			this.korn = this.korn - ( kornAusserhalbDerKornkammern / 2 );
		}
	}

	/**
	 * 
	 * Zufriedenheit anpassen mit den Werten von: den aktuellen Soldaten der
	 * Landgröße der Bevölkerungsanzahl dem Steuersatz der Essensration
	 * 
	 * Wenn Anzahl Soldaten oder Landgröße == 0 ist, dann sinkt die Zufriedenheit
	 * sonst steigt sie. Wenn Volk == 0 dann Zufriedenheit = 0 Mehl wird nicht
	 * berücksichtigt, da es zu diesem Zeitpunkt bereits weg ist.
	 * 
	 * Berechnet wird das mit: N(t) = N(0) * (1+p) ** t siehe
	 * http://de.wikipedia.org/wiki/Zinseszins#Exponentielles_Wachstum
	 */
	public void zufriedenheitAnpassen()
	{
		// N(0)
		double indexAlt = (double) this.bevoelkerungszufriedenheit;
		// N(t)
		int indexNeu = 0;
		// der Rundenzähler als Zeit
		double t = (double) this.marktplatz.getRundenzaehler() / 10.0d;
		// p wird durch die fünf Parameter errechnet
		double p1 = (double) getSoldaten() / 10.0d;
		double p2 = (double) this.laendereien.size() / 10.0d;
		double p3 = (double) getBevoelkerungsanzahl() / 10.0d;
		double p4 = (double) getSteuersatz() / 10.0d;
		double p5 = (double) getEssensration() / 10.0d;

		double x = 1.0;
		if ( p1 == 0.0d || p2 == 0.0d )
		{
			x = -1.0d;
		}

		if ( p3 == 0.0d )
		{
			x = 0.0d;
		}

		double p = ( p1 + p2 + p3 + p4 + p5 ) * x;

		// wenn indexAlt == 0 und p > 0
		// dann indexAlt auf 1.0 setzen
		// da indexNeu sonst auf 0 bleibt
		if ( indexAlt == 0.0d && p > 0.0d )
		{
			indexAlt = 1.0d;
		}

		indexNeu = (int) ( indexAlt * Math.pow( ( 1 + p ), t ) );

		this.bevoelkerungszufriedenheit = indexNeu;
	}

	public void setTitelflag( boolean _titelflag )
	{
		this.titelflag = _titelflag;
	}

	public boolean getTitelflag()
	{
		return titelflag;
	}

	public int getDuenger()
	{
		return this.duenger;
	}

	public int getBevoelkerungszufriedenheit()
	{
		return this.bevoelkerungszufriedenheit;
	}
	
	public void setSoldaten(int soldaten) {
		this.soldaten = soldaten;
	}

}
