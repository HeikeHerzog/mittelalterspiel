package de.akademie.logit.view;

public final class Gebaeudeuntermenu extends Menu {

		private static Gebaeudeuntermenu gebaeudeuntermenu;
		
		private Gebaeudeuntermenu()
		{
			addText( "Gebaeude" );

			addText( "\t0\tAktion Abbrechen" );
			addText( "\t1\tFeld " );
			addText( "\t2\tMühle " );
			addText( "\t3\tKornkammer " );
		}

		public static synchronized Gebaeudeuntermenu getInstance()
		{
			if ( gebaeudeuntermenu == null )
				gebaeudeuntermenu = new Gebaeudeuntermenu();
			return gebaeudeuntermenu;
		}
}

	
	
