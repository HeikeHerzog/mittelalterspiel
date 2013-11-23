package de.akademie.logit.view;

public final class Handelswarenuntermenu extends Menu
{
	private static Handelswarenuntermenu handelswarenuntermenu;

	private Handelswarenuntermenu()
	{
		addText( "Handelsware" );

		addText( "\t0\tAktion Abbrechen" );
		addText( "\t1\tKorn" );
		addText( "\t2\tMehl" );
		addText( "\t3\tDünger" );
	}

	public static synchronized Handelswarenuntermenu getInstance()
	{
		if ( handelswarenuntermenu == null )
			handelswarenuntermenu = new Handelswarenuntermenu();
		return handelswarenuntermenu;
	}

}
