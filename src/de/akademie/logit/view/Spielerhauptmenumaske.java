package de.akademie.logit.view;

/**
 * 
 * @author paul
 * 
 */
public final class Spielerhauptmenumaske extends Menu
{
	private static Spielerhauptmenumaske spielerhauptmenumaske;

	private Spielerhauptmenumaske()
	{
		addText( "Spielerhauptmenümaske" );

		addText( "\t1\tMenü Ländereien" );
		addText( "\t2\tMenü Gebäude" );
		addText( "\t3\tMenü Soldaten" );
		addText( "\t4\tMenü Handelsware" );
		addText( "\t5\tMenü Sabotage" );
		addText( "\t6\tMenü Aktionen" );
		addText( "\t7\tChat" );
		addText( "\t8\tSpielzug beenden" );
		addText( "" );
		addText( "\t0\tSpiel beenden" );
	}

	public static synchronized Spielerhauptmenumaske getInstance()
	{
		if ( spielerhauptmenumaske == null )
			spielerhauptmenumaske = new Spielerhauptmenumaske();
		return spielerhauptmenumaske;
	}

}
