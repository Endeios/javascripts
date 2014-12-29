package io.endeios.example.javascripts.app;
import java.util.Map;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
public class SimpleJavascript {

	public static void main(String[] args) {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("javascript");
		Bindings bindings = new SimpleBindings();
		bindings.put("externalData", 100);
		String simpleProgram ="function getGreet() {"
				+ "return \"Hello World\""
				+ "} "
				+ "some_data={some:\"data\",ext:externalData}";
		engine.setBindings(bindings, ScriptContext.GLOBAL_SCOPE);
		try {
			Object res = engine.eval(simpleProgram);
			System.out.println("Engine result is "+res);
			Map some_data = (Map) engine.get("some_data");
			
			System.out.println("some_data is set to some:"+some_data.get("some")+" and ext:"+some_data.get("ext"));
			Invocable iengine = (Invocable)engine;
			System.out.println("Function Invocation: "+iengine.invokeFunction("getGreet"));
			
		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
	}

}
