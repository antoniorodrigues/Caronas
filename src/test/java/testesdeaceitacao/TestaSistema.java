package testesdeaceitacao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import sistema.SistemaController;

import easyaccept.EasyAcceptFacade;


public class TestaSistema {

	@Test
	public void testarEasyAcceptScript() throws Exception {

		List<String> files = new ArrayList<String>();

		// Put the us1.txt file into the "test scripts" list
		files.add("scripts/US01.txt");
		files.add("scripts/US02.txt");
		files.add("scripts/US03.txt");
		files.add("scripts/US04.txt");
		files.add("scripts/US05.txt");
		files.add("scripts/US06.txt");
		files.add("scripts/US07.txt");
		files.add("scripts/US08.txt");
		files.add("scripts/US09.txt");
		files.add("scripts/US10.txt");
		files.add("scripts/US11.txt");
		files.add("scripts/US12.txt");

		// Instantiate the System façade
		SistemaController sf = new SistemaController();

		// Instantiate EasyAccept façade
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(sf,files);

		// Execute the tests
		eaFacade.executeTests();

		// Print the tests execution results
		System.out.println(eaFacade.getCompleteResults());
		
		assertTrue(eaFacade.getTotalNumberOfNotPassedTests() == 0);
	}

}