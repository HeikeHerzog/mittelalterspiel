package de.akademie.logit.controller;

import de.akademie.logit.model.Marktplatz;
import de.akademie.logit.model.Spieler;
import de.akademie.logit.view.Anzeige;

public class Sabotageakt
{

	private Spieler opfer;
	private Spieler aktiverSpieler;
	private Anzeige anzeige;
	private Marktplatz marktplatz;
	private int soldateneinsatz;
	private int sabotageKosten=10;
	
	public Sabotageakt(Spieler _opfer, Spieler _aktiverSpieler, int _soldateneinsatz) {
		this.opfer = _opfer;
		this.aktiverSpieler = _aktiverSpieler;
		this.soldateneinsatz = _soldateneinsatz;
	}
	
	private boolean goldStehlen() {
		return false;
	}
	
	private boolean gebaeudeZerstoeren() {
		return false;
	}
	
	
	private boolean kornVergiften() {
		return false;
	}
	
	
	private boolean zufriedenheitVerringern() {
		return false;
	}
	
	public boolean sabotiere (int auswahl) {
		return false;
	}
	
	
	public boolean ermittleErfolg() {
		return false;
	}
	
	
}
