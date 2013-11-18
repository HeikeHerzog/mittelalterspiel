package de.akademie.logit.model;

import java.util.ArrayList;

import de.akademie.logit.controller.EreignisController;

public class Spieler
{
	private String name;
	private Titel titel = new Titel();
	// initialer Titelindex wird im Constructor um eins inc
	private int indexTitel = -1;
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
	private boolean titelflag=false;
	private int bevoelkerungszufriedenheit=0;
	
	
	public Spieler() {
		
	}
	
	
	public Spieler(String _name) {
		this.name = _name;
		// erste Stufe: Bauer
		setNextTitel();

		this.marktplatz = new Marktplatz();

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
	}
	
	public String getName() {
		return this.name;
	}
	
	// TODO: Titel aktuellerTitel durch getTitel() ersetzen
	public boolean titelErwerben(Titel aktuellerTitel, int gold, ArrayList<Land> laendereien) {
		return false;
	}
	
	
	public void spielzugBeenden() {
		
	}
	
	
	public int getGold() {
		return this.gold;
	}
	
	
	
	public int getSoldaten() {
		return this.soldaten;
	}
	
	
	public void saldiereGold(int menge) {
		
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

		for (Land land : this.laendereien)
		{
			besitz = besitz + land.getGebaeude().getBezeichnung() + " ";
		}

		return "Gebäude pro " + new Land().getBezeichnung() + ":    " + besitz;
	}

	public Land findeFreiesLand() {
		return null;
	}
	
	
	public void besetzeLandMitGebaeude(Gebaeude gebaeude, Land freiesLand) {
		
	}
	
	
	public boolean zerstöreGesuchtesGebaeude(int auswahl) {
		return false;
	}
	
	
	public void saldiereSoldaten(int anzahl) {
		
	}
	
	public void saldiereHandelsware(int auswahl, int menge) {
		
	}
	
	public void saldiereKorn(int gesMenge, int mahlMenge) {
		
	}
	
	
	public int getKorn() {
		return this.korn;
	}
	
	
	
	public int getMehl() {
		return this.mehl;
	}
	
	
	public boolean isSaboteur() {
		return false;
	}
	
	
	
	public void setSabotage(boolean _sabotageflag) {
		this.sabotageflag = _sabotageflag;
	}
	
	
	public void sabotageDurchfuehren() {
		
	}
	
	
	public int getSteuersatz() {
		return this.steuersatz;
	}
	
	
	public void setSteuersatz(int _steuersatz) {
		this.steuersatz = _steuersatz;
	}
	
	
	public void setNextTitel()
	{
		this.indexTitel = this.indexTitel + 1;
	}

	public String getTitel()
	{
		return this.titel.getTitel( this.indexTitel ) ;
	}

	

	/* Essensrationen können halb, voll oder doppelt sein.
	 * Eine volle Essensration sind 2 Einheiten Mehl.
	 * Eine halbe Essensration ist 1 Einheit Mehl.
	 * Eine doppelte Essensration sind 4 Einheiten Mehl.
	 * Soldaten bekommen immer die volle Essensration, also 2 Einheiten Mehl
	 */
	public void setEssensration(int rationssatz) {
		
		if( rationssatz == 1) {
			this.essensration = 1;
		}
		else if (rationssatz == 2) {
			this.essensration = 2;
		}
		else if (rationssatz == 3 ) {
			this.essensration = 4;
		}
		
	}

	
	public int getEssensration() {
		return this.essensration;
	}
	
	
	public void setMehl(int menge) {
		this.mehl = menge;
	}

	
	public void setKorn(int menge) {
		this.korn = menge;
	}


	public void setSabotageOpfer(boolean _sabotageOpfer) {
		
	}
	
	
	public boolean isSabotageOpfer() {
		return this.sabotageOpfer;
	}
	
	public int ermittleGoldBetrag(int inProzent) {
		return 0;
	}
	
	
	public int ermittleKornMenge(int inProzent) {
		return 0;
	}
	
	public int ermittleSoldatenAnzahl(int inProzent) {
		return 0;
	}
	
	public int getBevoelkerungsanzahl() {
		return this.bevoelkerungsanzahl;
	}
	
	
	public void setBevoelkerungsanzahl(int _bevoelkerungsanzahl) {
		this.bevoelkerungsanzahl = _bevoelkerungsanzahl;
	}
	
	public void steuernEintreiben() {
		
		this.gold = (this.gold*this.steuersatz/100)+this.gold;
		
	}
	
	
	/* Wenn Soldaten nicht genug zu essen haben oder nicht bezahlt werden können,
	 * wandert die "unterversorgte" Anzahl von Soldaten ab
	 * 	 */
	public void soldatenVersorgen(int sold) {
		
		int benötigtesMehl = this.soldaten * 2;  //Soldaten bekommen immer die volle Essensration von 2 Mehleinheiten
		int benötigtesGold = this.soldaten * sold;
		
		int satteSoldaten = Integer.valueOf(Math.round(this.mehl/2));
		int hungrigeSoldaten = this.soldaten - satteSoldaten;
		
		int bezahlteSoldaten = Integer.valueOf(Math.round(this.gold/sold));
		int unbezahlteSoldaten = this.soldaten - bezahlteSoldaten;
		
		
		
		if ((benötigtesMehl > this.mehl) || (benötigtesGold > this.gold)) {
			
			if (hungrigeSoldaten > unbezahlteSoldaten) {
				
				this.soldaten = this.soldaten - hungrigeSoldaten;
				this.mehl = 0;
				this.gold = this.gold - benötigtesGold;
			}
			else if (hungrigeSoldaten <= unbezahlteSoldaten) {
				this.soldaten = this.soldaten - unbezahlteSoldaten;
				this.mehl = this.mehl - benötigtesMehl;
				this.gold = 0;
			}
								
		}
		else if ((benötigtesMehl <= this.mehl) && (benötigtesGold <= this.gold)) {
			
			this.mehl = this.mehl - benötigtesMehl;
			this.gold = this.gold - benötigtesGold;
			
		}
	}
	
	/* Wenn die Bevölkerung nicht genug zu essen hat,
	 * wandern die hungrigen Bewohner ab
	 * 	 */
	public void bevoelkerungFuettern() {
		
		int benötigtesMehl = this.bevoelkerungsanzahl * this.essensration;
		
		if (benötigtesMehl < this.mehl) {
			
			int satteBevoelkerung = Integer.valueOf(Math.round(this.mehl/this.essensration));
			this.bevoelkerungsanzahl = this.bevoelkerungsanzahl - (this.bevoelkerungsanzahl - satteBevoelkerung);
						
		}
		this.mehl = 0; // Mehl wird bei Spielzug beenden immer auf 0 gesetzt, da übriges Mehl verdirbt
		
	}
	
	public int zaehleGebaeude(int auswahl) {
		int anzahl = 0;
		
		for (int i = 0; i < this.laendereien.size(); i++) {
			switch (auswahl) {
			
			case 1: 	// Felder zaehlen
				if (this.laendereien.get(i).getBezeichnung() == "Feld") {
					anzahl++;
				}
				break;
				
			case 2: 	//Muehlen zaehlen
				if (this.laendereien.get(i).getBezeichnung() == "Mühle") {
					anzahl++;
				}
				break;
				
			case 3: 	//Kornkammern zaehlen
				
					
				} 
				break;
		
			default:
				break;
		}
				
		return false;
		
	}
	
	
	public void kornErntenUndVerteilen() {
		
		int anzahlFelder = this.laendereien.size();
		
		for (Land land : this.laendereien)
		{
			besitz = besitz + land.getGebaeude().getBezeichnung() + " ";
		}
		
	}
	
	
	public void zufriedenheitAnpassen() {
		
	}
	
	public void setTitelflag (boolean _titelflag) {
		this.titelflag = _titelflag;
	}
	
	public boolean getTitelflag () {
		return titelflag;
	}
	
	public int getDuenger() {
		return this.duenger;
	}
	
	public int getBevoelkerungszufriedenheit() {
		return this.bevoelkerungszufriedenheit;
	}
	
	
	
}
