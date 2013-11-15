package de.akademie.logit.view;

/**
 * 
 * @author paul
 * 
 */
public final class Soldatenmenu extends Menu
{
	private static Soldatenmenu soldatenmenu;

	private Soldatenmenu()
	{
		addText( "Soldaten" );

		addText( "\t0\tAktion Abbrechen" );
		addText( "\t1\tSoldaten kaufen" );
		addText( "\t2\tSoldaten entlassen" );
	}

	public static synchronized Soldatenmenu getInstance()
	{
		if ( soldatenmenu == null )
			soldatenmenu = new Soldatenmenu();
		return soldatenmenu;
	}
}
