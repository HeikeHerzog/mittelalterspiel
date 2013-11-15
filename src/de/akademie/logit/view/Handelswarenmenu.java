package de.akademie.logit.view;

/**
 * 
 * @author paul
 * 
 */
public final class Handelswarenmenu extends Menu
{
	private static Handelswarenmenu handelswarenmenu;

	private Handelswarenmenu()
	{
		addText( "Handelsware" );

		addText( "\t0\tAktion Abbrechen" );
		addText( "\t1\tWare kaufen" );
		addText( "\t2\tWare verkaufen" );
	}

	public static synchronized Handelswarenmenu getInstance()
	{
		if ( handelswarenmenu == null )
			handelswarenmenu = new Handelswarenmenu();
		return handelswarenmenu;
	}
}
