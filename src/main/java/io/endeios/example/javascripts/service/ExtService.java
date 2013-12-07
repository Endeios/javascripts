package io.endeios.example.javascripts.service;

import java.util.List;

public class ExtService {
	private List<String> Strings;

	
	public ExtService(List<String> strings) {
		super();
		Strings = strings;
	}

	public List<String> getStrings() {
		System.err.println("XXXXXXXXXXXXXXXXX Returning strings: "+Strings);
		return Strings;
	}

	public void setStrings(List<String> strings) {
		Strings = strings;
	}
}
