package de.akademie.logit.main;

import de.akademie.logit.controller.EingabeController;
import de.akademie.logit.model.Spieler;
import de.akademie.logit.view.Aktionsmenu;
import de.akademie.logit.view.Anzeige;
import de.akademie.logit.view.Gebaeudemenu;
import de.akademie.logit.view.Handelswarenmenu;
import de.akademie.logit.view.Landmenu;
import de.akademie.logit.view.Sabotagemenu;
import de.akademie.logit.view.Soldatenmenu;
import de.akademie.logit.view.Spielerhauptmenumaske;

public class Programm
{
	public static void main( String[] args )
	{
		EingabeController eingabeController = new EingabeController();
		eingabeController.initialisieren();
		eingabeController.loop();
		
//		Spieler spieler = new Spieler("Paul");
//		spieler.setNextTitel();
		
//		Anzeige.zeigeMenuAn( spieler, Spielerhauptmenumaske.getInstance() );
//		Anzeige.zeigeMenuAn( spieler, Landmenu.getInstance() );
//		Anzeige.zeigeMenuAn( spieler, Gebaeudemenu.getInstance() );
//		Anzeige.zeigeMenuAn( spieler, Soldatenmenu.getInstance() );
//		Anzeige.zeigeMenuAn( spieler, Handelswarenmenu.getInstance() );
//		Anzeige.zeigeMenuAn( spieler, Sabotagemenu.getInstance() );
//		Anzeige.zeigeMenuAn( spieler, Aktionsmenu.getInstance() );
	}
}
