package io.endeios.example.javascripts;

/**
 * 
 * A test interface, used to implement in different scripting languages
 * 
 * @author bveronesi
 *
 */
public interface TestInterface {
	
	/**
	 * Lets the script instantiate a bean and read it in java
	 * 
	 * @return
	 */
	ABean getBean();
	
	/**
	 * Lets get a boolean from the scripts
	 * @return
	 */
	Boolean isActive();
	
	/**
	 * Lets get a number from the scripts
	 * N.B.: In javscript, integers are long
	 * 
	 */
	Long getNum();
	
	/**
	 * The Result of Something serviceable (elaborated from a "service" interface);
	 * 
	 * @return concatenation of service strings
	 */
	String serviceableResult();
}
