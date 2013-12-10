package io.endeios.example.javascripts;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SpamScriptTest {
	
	private static String SCRIPT = "src/main/python/Impl.py";

	private Logger log = Logger.getLogger(getClass());
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws IOException {
		FileReader a = new FileReader(SCRIPT);
		BufferedReader r = new BufferedReader(a);
		String strLine;
		while((strLine = r.readLine()) != null){
			log.info(strLine);
		} 
	}

}
