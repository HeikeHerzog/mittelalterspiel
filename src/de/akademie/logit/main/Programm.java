package de.akademie.logit.main;

import de.akademie.logit.controller.EingabeController;

public class Programm
{
	public static void main( String[] args )
	{
		EingabeController eingabeController = new EingabeController();
		eingabeController.initialisieren();
		eingabeController.loop();
	}
}
