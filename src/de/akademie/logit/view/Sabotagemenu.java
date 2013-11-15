package de.akademie.logit.view;

/**
 * 
 * @author paul
 * 
 */
public final class Sabotagemenu extends Menu
{
	private static Sabotagemenu sabotagemenu;

	private Sabotagemenu()
	{
		addText( "Sabotage" );

		addText( "\t0\tAktion Abbrechen" );
		addText( "\t1\tGold stehlen" );
		addText( "\t2\tGebäude zerstören" );
		addText( "\t3\tKorn vergiften" );
		addText( "\t4\tZufriedenheit verringern" );
	}

	public static synchronized Sabotagemenu getInstance()
	{
		if ( sabotagemenu == null )
			sabotagemenu = new Sabotagemenu();
		return sabotagemenu;
	}
}
