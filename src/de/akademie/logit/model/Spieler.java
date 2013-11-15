package de.akademie.logit.model;

import java.util.ArrayList;

import de.akademie.logit.controller.EreignisController;

public class Spieler
{
	private String name;
	private Titel titel;
	private int gold=100;
	private ArrayList<Land> laendereien = new ArrayList<Land>();
	private int soldaten;
	private int steuersatz;
	private int mehl;
	private int korn;
	private int duenger;
	private int essensration=2;
	private int bevoelkerungsanzahl=50;
	private Marktplatz marktplatz;
	private EreignisController ereignis;
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
		return gold;
	}
	
	
	public int getSoldaten() {
		return soldaten;
	}
	
	
	public void saldiereGold(int menge) {
		
	}
	
	
	public ArrayList<Land> getLandListe() {
		return laendereien;
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
		return korn;
	}
	
	
	
	public int getMehl() {
		return mehl;
	}
	
	
	public boolean isSaboteur() {
		return false;
	}
	
	
	
	public void setSabotage(boolean _sabotageflag) {
		sabotageflag = _sabotageflag;
	}
	
	
	public void sabotageDurchfuehren() {
		
	}
	
	
	public int getSteuersatz() {
		return steuersatz;
	}
	
	
	public void setSteuersatz(int _steuersatz) {
		steuersatz = _steuersatz;
	}
	
	
	public Titel getTitel() {
		return titel;
	}
	
	
	public void setTitel(Titel neuerTitel) {
		
	}


	public void setEssensration(int rationssatz) {
		
	}

	
	public int getEssensration() {
		return essensration;
	}
	
	
	public void setMehl(int menge) {
		mehl = menge;
	}

	
	public void setKorn(int menge) {
		korn = menge;
	}


	public void setSabotageOpfer(boolean _sabotageOpfer) {
		
	}
	
	
	public boolean isSabotageOpfer() {
		return sabotageOpfer;
	}
	
	public int ermittleGoldBetrag(int inProzent) {
		return 0;
	}
	
	
	public int getBevoelkerungsanzahl() {
		return bevoelkerungsanzahl;
	}
	
	
	public void setBevoelkerungsanzahl(int _bevoelkerungsanzahl) {
		bevoelkerungsanzahl = _bevoelkerungsanzahl;
	}
	
	public void steuernEintreiben() {
		
	}
	
	
	public void soldatenVersorgen(int sold) {
		
	}
	
	
	public void bevoelkerungFuettern() {
		
	}
	
	
	public void kornErntenUndVerteilen() {
		
	}
	
	
	public void zufriedenheitErmitteln() {
		
	}
	
	
	
}
