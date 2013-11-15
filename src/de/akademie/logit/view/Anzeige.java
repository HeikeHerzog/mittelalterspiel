package de.akademie.logit.view;

public class Anzeige
{
	private Anzeige()
	{}

	public static void zeigeStringAn( String text )
	{
		System.out.println( text );
	}

	public static void zeigeMenuAn( Menu menu )
	{
		menu.printText();
	}

	public static void zeigeInfobereich(Infobereich infobereich)
	{

	}

}
