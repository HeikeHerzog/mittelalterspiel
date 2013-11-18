package de.akademie.logit.model;

public class Kornkammer extends Gebaeude
{
	private final static String BEZEICHNUNG = "Kornkammer";
	private final static int MAX_LAGERMENGE = 100;

	public Kornkammer()
	{}

	public String getBezeichnung()
	{
		return BEZEICHNUNG;
	}

	public int getMaxLagermenge()
	{
		return MAX_LAGERMENGE;
	}

}
