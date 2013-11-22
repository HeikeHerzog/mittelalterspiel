package de.akademie.logit.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public final class Marktplatz
{
	private static Marktplatz marktplatz;

	private List<Spieler> spieler = new ArrayList<Spieler>();
	private Spieler startSpieler;
	private Spieler aktiverSpieler;
	private int soldaten ;
	private int soldatenPreis=5;
	private int soldatenSold=3;
	private static int rundenzaehler;
	private HashMap<Integer, Spieler>opfermap;
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

	public void fuegeSpielerHinzu(Spieler _spieler) {
		this.spieler.add(_spieler);
	}
	
	
	public Spieler ermittleStartSpieler( int anzSpieler )
	{
		this.startSpieler = this.spieler.get( (int) ( Math.random() * anzSpieler ) );
		return startSpieler;
	}
	
	
	public void setAktivenSpieler(Spieler _spieler) {
		this.aktiverSpieler = _spieler;
	}
	
	
	public Spieler getAktivenSpieler() {
		return this.aktiverSpieler;
	}
	
	
	public void setNaechstenSpieler() {
		
		
	}
	
// 15.11.2013	
	public boolean kaufeLand(int menge, int gold) {
		int gesPreis = menge*preisLand;
		if (gesPreis <= gold) {     //gold reicht aus um die Anzahl an Land zu erwerben
			for (int i=0; i<menge; i++) {
				Land neuesLand = new Land();
				this.aktiverSpieler.saldiereGold(preisLand*(-1));
				fuegeSpielerLandHinzu(this.aktiverSpieler, neuesLand);
			}
			return true;
		}
		else return false;
	}
	
	//15.11.2013
	public void fuegeSpielerLandHinzu(Spieler aktiverSpieler, Land land) {
		aktiverSpieler.getLandListe().add(land);
	}	
	
	
	//18.11.2013
	public boolean zerstoereGebaeude(int auswahl) {
		boolean gefunden = false;
		gefunden = this.aktiverSpieler.zerstöreGesuchtesGebaeude(auswahl);
		System.out.println("Rückgabewert von zerstöreGesuchtesGebäude" + gefunden);
		if (gefunden) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
// 15.11.2013	
	public boolean kaufeGebaeude(int auswahl, int gold, Land freiesLand) {
		switch (auswahl) {
			case 0:		// kein Kauf -> zurück zum Gebäude bearbeiten
				break;	
			case 1: 	// Feld kaufen
				if (gold >= preisGebaeude) {
					Feld neuesFeld = new Feld();
					this.aktiverSpieler.besetzeLandMitGebaeude(neuesFeld, freiesLand);
					this.aktiverSpieler.saldiereGold(preisGebaeude*(-1)); // Gold beim Spieler abziehen
					return true;
				} 
				break;
			case 2: 	//Muehle kaufen
				if (gold >= preisGebaeude) {
					Muehle neueMuehle = new Muehle();
					this.aktiverSpieler.besetzeLandMitGebaeude(neueMuehle, freiesLand);
					this.aktiverSpieler.saldiereGold(preisGebaeude*(-1));
					return true;
				} 
				break;
				
			case 3: 	//Kornkammer kaufen
				if (gold >= preisGebaeude) {
					Kornkammer neueKornkammer = new Kornkammer();
					this.aktiverSpieler.besetzeLandMitGebaeude(neueKornkammer, freiesLand);
					this.aktiverSpieler.saldiereGold(preisGebaeude*(-1));
					return true;
				} 
				break;
		
			default:
				break;
		}
				
		return false;	
	}
	
	
// 15.11.2013	18.11.2013
	public boolean kaufeSoldaten(int anzahl, int gold) {
		if (gold < (soldatenPreis*anzahl)) {
			return false;			// Gold reicht nicht -> zurück zu Soldaten bearbeiten
		}
		else {
			this.aktiverSpieler.saldiereSoldaten(anzahl);  //Soldaten des aktiven Spielers erhöhen
			this.aktiverSpieler.saldiereGold(soldatenPreis*anzahl*(-1)); // Gold vom Spieler abziehen
			return true;
		}	
	}
	
	
	//15.11.2013
	public boolean handelswareKaufen(int auswahl, int menge, int gold) {
			if (auswahl == 0 || menge == 0) {
				return false;
			} else {
				switch (auswahl) {
				case 1: 	//Korn kaufen
					if (gold >= (menge*this.preisKorn)) {
						this.aktiverSpieler.saldiereHandelsware(auswahl, menge); //Kornmenge beim Spieler erhöhen
						this.aktiverSpieler.saldiereGold(menge*this.preisKorn*(-1));  //Gold wird abgezogen
						saldiereHandelsware(auswahl, menge*(-1)); //auf dem Marktplatz wird die Kornmenge verringert
						return true;
					}
					break;
				case 2: 	//Mehl kaufen 
					if (gold >= (menge*this.preisMehl)) {
						this.aktiverSpieler.saldiereHandelsware(auswahl, menge); //Mehlmenge beim Spieler erhöhen
						this.aktiverSpieler.saldiereGold(menge*this.preisMehl*(-1)); //Gold beim Spieler abziehen
						saldiereHandelsware(auswahl, menge*(-1)); //Mehl auf dem Marktplatz erhöhen
						return true;
					}
					break;
				case 3: 	// Dünger kaufen
					if (gold >= (menge*this.preisDuenger)) {
						this.aktiverSpieler.saldiereHandelsware(auswahl, menge); //Dünger beim Spieler erhöhen
						this.aktiverSpieler.saldiereGold(menge*this.preisDuenger*(-1)); //Gold beim Spieler abziehen
						return true;
					}
					break;
				default:
					break;
				}
					
				return false;
			}
	}
		
		
//15.11.2013	
	public boolean handelswareVerkaufen(int auswahl, int menge, int gold) {
		if (auswahl == 0 || menge == 0)  {
			return false;		// Abbruch
		}
		else if (auswahl == 1) { 	// Korn verkaufen
			if (this.aktiverSpieler.getKorn() >= menge) {	//Spieler hat genügend Korn zum Verkaufen
				aktiverSpieler.saldiereHandelsware(auswahl, menge*(-1));  // Kornmenge beim Spieler verringern
				aktiverSpieler.saldiereGold(menge*preisKorn);	//Gold des Spielers erhöhen
				saldiereHandelsware(auswahl, menge);	//Marktplatz bekommt Korn dazu
				return true;
			} else return false;
		} else if (auswahl == 2) {	// Mehl verkaufen
			if (this.aktiverSpieler.getMehl() >= menge) {
				aktiverSpieler.saldiereHandelsware(auswahl, menge*(-1));  //Mehlmenge beim Spieler verringern
				aktiverSpieler.saldiereGold(menge*preisMehl);  // Spieler bekommt Gold fürs Mehl
				saldiereHandelsware(auswahl, menge);  //Marktplatz bekommt Mehl
				return true;
			} else return false;
		}
		
		return false;
	}

	
	
	public void saldiereHandelsware(int auswahl, int menge) {
		
		if (auswahl == 1) {
			mengeKornEnde += menge;
		}
		else if (auswahl == 2) {
			mengeMehlEnde += menge;
		}
			
	}
	
	
	public String holeSpielernamen() {
	
			return "Hans";
	}
	
	public int getSold() {
		return this.soldatenSold;
	}
	
	
	//Preise für Korn und Mehl werden im Verhältnis Anfangsbestand : Endbestand ermittelt
	public void preiseAnpassen() {
				
		int unterschiedMehl = mengeMehlEnde - mengeMehlAnfang;
		int unterschiedKorn = mengeKornEnde - mengeKornAnfang;
		
				
		// wenn der Mehlendbestand sich um min. 50% des Anfangsbestandes erhöht hat wird das Mehl ggf. billiger
		if (unterschiedMehl >= ((int)(mengeMehlAnfang/2)) && (preisMehl > 1)) { 
			preisMehl = (int)(preisMehl/2);
		}
		// wenn der Mehlendbestand kleiner als der Mehlanfangsbestand ist wird das Mehl ggf. teurer
		// dafür muss sich der Mehlbestand um die hälfte des Anfangsbestandes verringert haben
		else if ( (unterschiedMehl < 0) && ( (unterschiedMehl*-1) >= ((int)(mengeMehlAnfang/2)) )) {
			preisMehl += (int)(preisMehl/2);	
		}		
		
		
		// wenn der Kornbestand sich um min. 50% des Anfangsbestandes erhöht hat wird das Korn ggf. billiger
		if (unterschiedKorn >= ((int)(mengeKornAnfang/2)) && (preisKorn > 1)) { 
			preisKorn = (int)(preisKorn/2);
		}
		// wenn der Kornendbestand kleiner als der Kornanfangsbestand ist wird das Korn ggf. teurer
		// dafür muss sich der Kornbestand um die hälfte des Anfangsbestandes verringert haben
		else if ( (unterschiedKorn < 0) && ( (unterschiedKorn*-1) >= ((int)(mengeKornAnfang/2)) )) {
			preisKorn += (int)(preisKorn/2);	
		}
				
	}
	
	
	public void incRundenzaehler() {
		if (aktiverSpieler.equals(startSpieler)){
			rundenzaehler += 1;
		}
	}
	
	// am Montag klären...
	public int getRundenzaehler() {
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
	
}
