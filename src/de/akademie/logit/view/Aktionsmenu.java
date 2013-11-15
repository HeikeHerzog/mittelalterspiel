package de.akademie.logit.view;

/**
 * 
 * @author paul
 * 
 */
public final class Aktionsmenu extends Menu
{
	private static Aktionsmenu aktionsmenu;

	private Aktionsmenu()
	{
		addText( "Aktionen auswählen" );

		addText( "\t0\tAktion Abbrechen" );
		addText( "\t1\tSteuersatz ändern" );
		addText( "\t2\tTitel erwerben" );
		addText( "\t3\tEssensration ändern" );
		addText( "\t4\tKorn mahlen" );
	}

	public static synchronized Aktionsmenu getInstance()
	{
		if ( aktionsmenu == null )
			aktionsmenu = new Aktionsmenu();
		return aktionsmenu;
	}
}
