package io.endeios.example.javascripts;

import static org.junit.Assert.*;
import io.endeios.example.javascripts.ABean;
import io.endeios.example.javascripts.TestInterface;
import io.endeios.example.javascripts.service.ExtService;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import javax.script.SimpleScriptContext;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JavaScriptTestCase {

	private ScriptEngine engine;
	private Logger log = Logger.getLogger(getClass());
	private FileReader script;
	private ExtService service;
	private SimpleBindings binding;
	private static String SCRIPT = "src/main/javascript/Impl.js";

	@Before
	public void setUp() throws Exception {
		ScriptEngineManager sem = new ScriptEngineManager();
		engine = sem.getEngineByName("javascript");
		script = new FileReader(SCRIPT);
		List<String>strings = new ArrayList<String>();
		strings.add("javascript+java");
		service = new ExtService(strings);
		binding = new SimpleBindings();
		binding.put("service", service);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		log.info("Testing if javascript engine exists");
		assertNotNull(engine);
		PASS();
		log.info("Testing if javascript file exists");
		assertTrue(Boolean.TRUE == (new File(SCRIPT).exists()));
		PASS();
	}

	@Test
	public void compile() throws ScriptException {
		log.info("Testing if javascript compiles to bytecode");
		Compilable cengine = (Compilable) engine;
		CompiledScript cscript = cengine.compile(script);
		assertNotNull(cscript);
//		log.info("Testing if javascript compiles to bytecode");
		PASS();
	}
	
	@Test
	public void invoke() throws ScriptException {
		log.info("Testing if javascript is invocable");
		Object thiz = engine.eval(script,binding);
		Invocable iengine = (Invocable) engine;
		TestInterface aClazz = iengine.getInterface(thiz, TestInterface.class);
		assertNotNull(iengine);
		PASS();
		log.info("Testing if javascript created the class");
		assertNotNull(aClazz);
		PASS();
		log.info("Testing if javascript created the class using the right interface");
		assertTrue(aClazz instanceof TestInterface);
		PASS();
		log.info("Testing if javascript object is really being instantiated");
		assertTrue(aClazz.isActive());
		assertTrue(Long.parseLong("101")==aClazz.getNum());
		PASS();
		log.info("Testing if java object inside javascript is really being instantiated");
		ABean myBean = aClazz.getBean();
		assertTrue(Long.parseLong("101")==myBean.getNumber());
		assertTrue(Boolean.TRUE==myBean.getReady());
		assertTrue("javascript".contentEquals(myBean.getName()));
		PASS();
		log.info("Testing if javascript instantiatedObject has refernce to binding objects (A.k.a services)");
		assertTrue("javascript+java".contentEquals(aClazz.serviceableResult()));
		List<String> brandNewStrings = new ArrayList<String>();
		String e = "a-new-string";
		brandNewStrings.add(e);
		service.setStrings(brandNewStrings);
		assertTrue(e.contentEquals(aClazz.serviceableResult()));
		PASS();
	}
	
	@Test
	public void invoke2() throws ScriptException, NoSuchMethodException {
		log.info("Testing if javascript is invocable");
		engine.eval(script,binding);
		
		Invocable iengine = (Invocable) engine;
		
		Object thiz = iengine.invokeFunction("gimmeMyObject",Void.TYPE);
		TestInterface aClazz = iengine.getInterface(thiz, TestInterface.class);
		assertNotNull(iengine);
		PASS();
		log.info("Testing if javascript created the class");
		assertNotNull(aClazz);
		PASS();
		log.info("Testing if javascript created the class using the right interface");
		assertTrue(aClazz instanceof TestInterface);
		PASS();
		log.info("Testing if javascript object is really being instantiated");
		assertTrue(aClazz.isActive());
		assertTrue(Long.parseLong("101")==aClazz.getNum());
		PASS();
		log.info("Testing if java object inside javascript is really being instantiated");
		ABean myBean = aClazz.getBean();
		assertTrue(Long.parseLong("101")==myBean.getNumber());
		assertTrue(Boolean.TRUE==myBean.getReady());
		assertTrue("javascript".contentEquals(myBean.getName()));
		PASS();
		log.info("Testing if javascript instantiatedObject has refernce to binding objects (A.k.a services)");
		assertTrue("javascript+java".contentEquals(aClazz.serviceableResult()));
		List<String> brandNewStrings = new ArrayList<String>();
		String e = "a-new-string";
		brandNewStrings.add(e);
		service.setStrings(brandNewStrings);
		assertTrue(e.contentEquals(aClazz.serviceableResult()));
		PASS();
	}
	
	/**
	 * Awkward, but it gives the illusion we have some sort of edvanced testing 
	 * framework, and i like the outcome
	 */
	void PASS(){
		log.info("\t\t\t\t\t\tPASS");
	}

}
