package de.akademie.logit.model;

public class Land
{
	private final static String BEZEICHNUNG = "Land";
	private Gebaeude gebaeude;

	public Land()
	{}

	public String getBezeichnung()
	{
		return BEZEICHNUNG;
	}

	public Gebaeude getGebaeude()
	{
		return gebaeude;
	}

	public void setGebaeude( Gebaeude gebaeude )
	{
		this.gebaeude = gebaeude;
	}

}
