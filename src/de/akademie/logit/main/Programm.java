package de.akademie.logit.main;

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
		Anzeige.zeigeMenuAn( Spielerhauptmenumaske.getInstance() );
		Anzeige.zeigeMenuAn( Landmenu.getInstance() );
		Anzeige.zeigeMenuAn( Gebaeudemenu.getInstance() );
		Anzeige.zeigeMenuAn( Soldatenmenu.getInstance() );
		Anzeige.zeigeMenuAn( Handelswarenmenu.getInstance() );
		Anzeige.zeigeMenuAn( Sabotagemenu.getInstance() );
		Anzeige.zeigeMenuAn( Aktionsmenu.getInstance() );
	}
}
