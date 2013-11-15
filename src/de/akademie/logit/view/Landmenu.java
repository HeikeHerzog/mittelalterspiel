package de.akademie.logit.view;

/**
 * 
 * @author paul
 * 
 */
public final class Landmenu extends Menu
{
	private static Landmenu landmenu;

	private Landmenu()
	{
		addText( "Ländereien" );

		addText( "\t0\tAktion Abbrechen" );
		addText( "\t1\tLand kaufen" );
	}

	public static synchronized Landmenu getInstance()
	{
		if ( landmenu == null )
			landmenu = new Landmenu();
		return landmenu;
	}
}
