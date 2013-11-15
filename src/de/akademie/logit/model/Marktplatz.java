package de.akademie.logit.model;

import java.util.ArrayList;
import java.util.HashMap;


public class Marktplatz
{

	private ArrayList<Spieler> spieler;
	private Spieler startSpieler;
	private Spieler aktiverSpieler;
	private int soldaten;
	private int soldatenPreis=5;
	private int soldatenSold=3;
	private static int rundenzaehler;
	private HashMap<Integer, Spieler>opfermap;
	private int preisKorn;
	private int mengeKornAnfang;
	private int mengeKornEnde;
	private int preisMehl;
	private int mengeMehlAnfang;
	private int mengeMehlEnde;
	private int preisDuenger;
	private int preisGebaeude = 10;
	private int preisLand = 20;
	
	
	public Marktplatz() {
		
	}
	
	public void erzeugeMenus() {
		
	}
	
	
	public void erzeugeInfobereich() {
		
	}
	
	
	public void fuegeSpielerHinzu(Spieler _spieler) {
		this.spieler.add(_spieler);
	}
	
	
	public Spieler ermittleStartSpieler() {
		return null;
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
		if (gesPreis <= gold) { //gold reicht aus um die Anzahl an Land zu erwerben
			for (int i=0; i<menge; i++) {
				Land neuesLand = new Land();
				this.aktiverSpieler.saldiereGold(gesPreis);
				fuegeSpielerLandHinzu(aktiverSpieler, neuesLand);
			}
			return true;
		}
		else return false;
	}
	
	
	public void fuegeSpielerLandHinzu(Spieler aktiverSpieler, Land land) {
		
	}
	
	public boolean zerstoereGebaeude(int auswahl) {
		return false;
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
					this.aktiverSpieler.saldiereGold(preisGebaeude);
					return true;
				} 
				break;
			case 2: 	//Muehle kaufen
				if (gold >= preisGebaeude) {
					Muehle neueMuehle = new Muehle();
					this.aktiverSpieler.besetzeLandMitGebaeude(neueMuehle, freiesLand);
					this.aktiverSpieler.saldiereGold(preisGebaeude);
					return true;
				} 
				break;
				
			case 3: 	//Kornkammer kaufen
				if (gold >= preisGebaeude) {
					Kornkammer neueKornkammer = new Kornkammer();
					this.aktiverSpieler.besetzeLandMitGebaeude(neueKornkammer, freiesLand);
					this.aktiverSpieler.saldiereGold(preisGebaeude);
					return true;
				} 
				break;
		
			default:
				break;
		}
				
		return false;
		
	}
	
// 15.11.2013	
	public boolean kaufeSoldaten(int anzahl, int gold) {
		if (gold < soldatenPreis) {
			return false;			// Gold reicht nicht -> zurück zu Soldaten bearbeiten
		}
		else {
			this.aktiverSpieler.saldiereSoldaten(anzahl);
			this.aktiverSpieler.saldiereGold(gold);
			return true;
		}
		
	}
	
	
	public boolean handelswareKaufen(int auswahl, int menge, int gold) {
		return false;
	}
	
	public boolean handelswareVerkaufen(int auswahl, int menge, int gold) {
		return false;
	}
	
	public void saldiereHandelsware(int auswahl, int menge) {
		
	}
	
	public String holeSpielernamen() {
		return "Hans";
	}
	
	public int getSold() {
		return this.soldatenSold;
	}
	
	
	public void preiseAnpassen() {
		
	}
	
	
	public void incRundenzaehler() {
		
	}
	
}
