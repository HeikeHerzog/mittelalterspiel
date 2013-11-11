package de.akademie.logit.model;

import java.util.ArrayList;

import de.akademie.logit.controller.Ereignis;

public class Spieler
{
	private String name;
	private Titel titel;
	private int gold;
	private ArrayList<Land> laendereien;
	private int soldaten;
	private int steuersatz;
	private int mehl;
	private int korn;
	private int duenger;
	private int essensration;
	private int bevoelkerungszahl=50;
	private Marktplatz marktplatz;
	private Ereignis ereignis;
	private boolean sabotageflag=true;
	private boolean sabotageOpfer=false;
	private int bevoelkerungszufriedenheit=0;
	
	
	public Spieler() {
		
	}
	
	
	public Spieler(String _name) {
		this.name = _name;
	}
	
	
	public boolean titelErwerben(Titel aktuellerTitel, int gold, ArrayList<Land> laendereienen) {
		return false;
	}
	
	
	public void spielzugBeenden() {
		
	}
	
	
	public boolean landKaufen(int menge) {
		return false;
	}
	
	
	public int getGold() {
		return 0;
	}
	
	
	public int getSoldaten() {
		return 0;
	}
	
	
	public void saldiereGold(int menge) {
		
	}
	
	
	public ArrayList<Land> getLandListe() {
		return null;
	}
	
	
	public Land findeFreiesLand() {
		return null;
	}
	
	
	public void besetzeLandMitGebaeude(Gebaeude gebaeude, Land freiesLand) {
		
	}
	
	
	public boolean findeGesuchtesGebaeude(int auswahl) {
		return false;
	}
	
	
	public void saldiereSoldaten(int anzahl) {
		
	}
	
	public void saldiereHandelsware(int auswahl, int menge) {
		
	}
	
	public void saldiereKorn(int gesMenge, int mahlMenge) {
		
	}
	
	
	public int getKorn() {
		return 0;
	}
	
	
	
	public int getMehl() {
		return 0;
	}
	
	
	public boolean isSaboteur() {
		return false;
	}
	
	
	public void setSabotage(boolean sabotageFlag) {
		
	}
	
	
	public void sabotageDurchfuehren() {
		
	}
	
	
	public int getSteuersatz() {
		return 0;
	}
	
	
	public void setSteuersatz(int steuersatz) {
		
	}
	
	
	public Titel getTitel() {
		return null;
	}
	
	
	public void setTitel(Titel neuerTitel) {
		
	}


	public void setEssensration(int rationssatz) {
		
	}

	
	public int getEssensration() {
		return 0;
	}
	
	
	public void setMehl(int menge) {
		
	}

	
	public void setKorn(int menge) {
		
	}


	public void setSabotageOpfer(boolean sabotageOpfer) {
		
	}
	
	
	public boolean isSabotageOpfer() {
		return false;
	}
}
