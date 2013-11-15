package de.akademie.logit.view;

/**
 * 
 * @author paul
 * 
 */
public class Menu
{
	private StringBuffer text = new StringBuffer();

	public Menu()
	{}

	public void addText( String text )
	{
		this.text.append( text );
		this.text.append( System.lineSeparator() );
	}

	public void printText()
	{
		System.out.println( this.text.toString() );
	}
}
