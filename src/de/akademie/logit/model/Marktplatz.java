package de.akademie.logit.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Jutta
 *
 */

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
	
	
	public Marktplatz() {
		
	}
	
	public void erzeugeMenus() {
		
	}
	
	
	public void erzeugeInfobereich() {
		
	}
	
	
	public void fuegeSpielerHinzu(Spieler spieler) {
		
	}
	
	public Spieler ermittleStartSpieler() {
		return null;
	}
	
	
	public void setAktivenSpieler(Spieler spieler) {
		
	}
	
	
	public Spieler getAktivenSpieler() {
		return null;
	}
	
	
	public void setNaechstenSpieler() {
		
	}
	
	
	public boolean kaufeLand(int menge, int gold) {
		return false;
	}
	
	
	public void fuegeSpielerHinzu(Spieler aktiverSpieler, Land land) {
		
	}
	
	public boolean zerstoereGebaeude(int auswahl) {
		return false;
	}
	
	
	public boolean kaufeGebaeude(int auswahl, int gold, Land freiesLand) {
		return false;
	}
	
	
	public boolean kaufeSoldaten(int anzahl, int gold) {
		return false;
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
		return soldatenSold;
	}
	
	
	public void preiseAnpassen() {
		
	}
	
	
	public void incRundenzaehler() {
		
	}
	
}
