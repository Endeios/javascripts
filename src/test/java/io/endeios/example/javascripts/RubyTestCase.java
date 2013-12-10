package io.endeios.example.javascripts;

import static org.junit.Assert.*;
import io.endeios.example.javascripts.ABean;
import io.endeios.example.javascripts.TestInterface;
import io.endeios.example.javascripts.service.ExtService;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import javax.script.SimpleScriptContext;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class RubyTestCase {

	private ScriptEngine engine;
	private Logger log = Logger.getLogger(getClass());
	private FileReader script;
	private ExtService service;
	private SimpleBindings binding;
	private FileReader script2;
	private SimpleBindings binding2;
	private static String SCRIPT = "src/main/ruby/Impl.rb";
	private static String SCRIPT2 = "src/main/ruby/functions.rb";

	@Before
	public void setUp() throws Exception {
		ScriptEngineManager sem = new ScriptEngineManager();
		engine = sem.getEngineByName("ruby");
		script = new FileReader(SCRIPT);
		script2 = new FileReader(SCRIPT2);
		List<String>strings = new ArrayList<String>();
		strings.add("ruby+java");
		service = new ExtService(strings);
		binding = new SimpleBindings();
		binding.put("service", service);
		binding2 = new SimpleBindings();
		binding2.put("service", service);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		log.info("Testing if ruby engine exists");
		assertNotNull(engine);
		PASS();
		log.info("Testing if ruby file exists");
		assertTrue(Boolean.TRUE == (new File(SCRIPT).exists()));
		PASS();
	}

	@Test
	@Ignore
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
		log.info("Testing if ruby is invocable");
		Object lol = engine.eval(script,binding);
		//NOTE no return object for ruby, you have to get it out
		log.info("Return object is "+lol);
		//NOTE getting data from bindings used to feed the script
		Object thiz = binding.get("myobj");
		Invocable iengine = (Invocable) engine;
		TestInterface aClazz = iengine.getInterface(thiz, TestInterface.class);
		assertNotNull(iengine);
		PASS();
		log.info("Testing if python created the class");
		assertNotNull(aClazz);
		PASS();
		log.info("Testing if javascript created the class using the right interface");
		assertTrue(aClazz instanceof TestInterface);
		PASS();
		log.info("Testing if javascript object is really being instantiated");
		assertTrue(aClazz.isActive());
		//NOTE Python knows how to return integers!
		//assertTrue(Long.parseLong("101")!=aClazz.getNum());
		//this would work if it would return integer
		//assertTrue(101==aClazz.getNum());
		PASS();
		log.info("Testing if java object inside javascript is really being instantiated");
		ABean myBean = aClazz.getBean();
		assertTrue(Long.parseLong("101")==myBean.getNumber());
		assertTrue(Boolean.TRUE==myBean.getReady());
		assertTrue("python".contentEquals(myBean.getName()));
		PASS();
		log.info("Testing if javascript instantiatedObject has refernce to binding objects (A.k.a services)");
		assertTrue("python+java".contentEquals(aClazz.serviceableResult()));
		List<String> brandNewStrings = new ArrayList<String>();
		String e = "a-new-string";
		brandNewStrings.add(e);
		service.setStrings(brandNewStrings);
		assertTrue(e.contentEquals(aClazz.serviceableResult()));
		PASS();
	}
	
	@Test
	@Ignore
	public void invoke2() throws ScriptException, NoSuchMethodException {
		log.info("Testing if javascript is invocable");
		engine.setBindings(binding2, ScriptContext.ENGINE_SCOPE);
		Object thiz1 = engine.eval(script2);
		Invocable iengine = (Invocable) engine;
		log.info("Testing that eval result is always null ");
		assertNull(thiz1);
		PASS();
		Object res = iengine.invokeFunction("greet");
		log.info("Result ::"+res);
		Object thiz = iengine.invokeFunction("gimmeMyObject");
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
		aClazz.getNum();
		//Does not pass, is innner class java math biginteger 
		long expected = Long.parseLong("101");
		assertTrue(expected==aClazz.getNum());
		PASS();
		log.info("Testing if java object inside javascript is really being instantiated");
		ABean myBean = aClazz.getBean();
		int a = 101;
		assertTrue(a==myBean.getNumber());
		assertTrue(Boolean.TRUE==myBean.getReady());
		assertTrue("python".contentEquals(myBean.getName()));
		PASS();
		log.info("Testing if javascript instantiatedObject has refernce to binding objects (A.k.a services)");
		assertTrue("python+java".contentEquals(aClazz.serviceableResult()));
		List<String> brandNewStrings = new ArrayList<String>();
		String e = "a-new-string";
		brandNewStrings.add(e);
		service.setStrings(brandNewStrings);
		assertTrue(e.contentEquals(aClazz.serviceableResult()));
		PASS();
	}
	
	/**
	 * Awkward, but it gives the illusion we have some sort of advanced testing 
	 * framework, and i like the outcome
	 */
	void PASS(){
		log.info("\t\t\t\t\t\tPASS");
	}

}
