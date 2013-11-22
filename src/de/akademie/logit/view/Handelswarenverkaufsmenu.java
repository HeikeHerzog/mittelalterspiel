/**
 * 
 */
package de.akademie.logit.view;

/**
 * @author Heike Herzog
 *
 */
public final class Handelswarenverkaufsmenu extends Menu {

	private static Handelswarenverkaufsmenu handelswarenverkaufsmenu;
	
	private Handelswarenverkaufsmenu()
	{
		addText( "Handelsware" );

		addText( "\t0\tAktion Abbrechen" );
		addText( "\t1\tKorn " );
		addText( "\t2\tMehl " );
	}

	public static synchronized Handelswarenverkaufsmenu getInstance()
	{
		if ( handelswarenverkaufsmenu == null )
			handelswarenverkaufsmenu = new Handelswarenverkaufsmenu();
		return handelswarenverkaufsmenu;
	}


}
