package io.endeios.example.javascripts;

import static org.junit.Assert.*;

import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test to explore java scripting capabilities
 * 
 * ref to {@link https://today.java.net/pub/a/today/2006/04/11/scripting-for-java-platform.html}
 * 
 * @author bveronesi
 *
 */
public class ScriptEnginesTestCases {
	
	private Logger log = Logger.getLogger(getClass());

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCapabilities() {
		ScriptEngineManager sem = new ScriptEngineManager();
	    List<ScriptEngineFactory> list = sem.getEngineFactories();
	    ScriptEngineFactory f;
	    for (int i = 0; i < list.size(); i++) {
	      f = (ScriptEngineFactory) list.get(i);
	      String engineName = f.getEngineName();
	      String engineVersion = f.getEngineVersion();
	      String langName = f.getLanguageName();
	      String langVersion = f.getLanguageVersion();
	      log.info(engineName + " " +
	        engineVersion + " " +
	        langName + " " +
	        langVersion);
	      String statement = f.getOutputStatement("hello, world");
	      log.info(statement);
	      ScriptEngine e = f.getScriptEngine();
	      try {
	        e.eval(statement);
	      } catch (ScriptException ex) {
	        ex.printStackTrace();
	      }
	    }
	}

}
