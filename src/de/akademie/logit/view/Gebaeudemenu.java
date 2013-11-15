package de.akademie.logit.view;

/**
 * 
 * @author paul
 * 
 */
public final class Gebaeudemenu extends Menu
{
	private static Gebaeudemenu gebaeudemenu;

	private Gebaeudemenu()
	{
		addText( "Gebäude" );

		addText( "\t0\tAktion Abbrechen" );
		addText( "\t1\tGebäude kaufen" );
		addText( "\t2\tGebäude zerstören" );
	}

	public static synchronized Gebaeudemenu getInstance()
	{
		if ( gebaeudemenu == null )
			gebaeudemenu = new Gebaeudemenu();
		return gebaeudemenu;
	}
}
