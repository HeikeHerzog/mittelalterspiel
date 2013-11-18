package de.akademie.logit.model;

public class Feld extends Gebaeude
{
	private final static String BEZEICHNUNG = "Feld";
	private final static int KORN_PRO_RUNDE = 50;

	public Feld()
	{}

	public String getBezeichnung()
	{
		return BEZEICHNUNG;
	}

	public int getKornProRunde()
	{
		return KORN_PRO_RUNDE;
	}

}
