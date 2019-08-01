package com.dovaleac.rxjava2tutorial.services;

import org.junit.jupiter.api.Test;

class ImportServiceTest {

	@Test
	void run() throws Exception {
		ImportService importService = new ImportService();

		//importService.importAllCharacters().forEach(System.out::println);
		importService.importAllHouses().forEach(System.out::println);
	}
}