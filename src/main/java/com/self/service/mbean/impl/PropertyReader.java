package com.self.service.mbean.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class PropertyReader {

	private final String PROPERTY_FILENAME="/mbean.properties";
	private final String CLASS_LOCATION="com.self.service.mbean.cmd.PropertyReader";
	private Properties propFile;
	
	public PropertyReader(){
		initProperties();
	}
	
	private InputStream loadFile() throws ClassNotFoundException, FileNotFoundException{
		InputStream input = Class.forName(
				CLASS_LOCATION)
				.getClassLoader().getResourceAsStream(
						PROPERTY_FILENAME);
		if (input == null)
			input = Class.forName(
					CLASS_LOCATION).getClass()
					.getResourceAsStream(PROPERTY_FILENAME);
		if (input == null)
			input = new java.io.FileInputStream("."+PROPERTY_FILENAME);
		return input;
	}
	
	private void initProperties() {
		Properties prop = new Properties();
		try {
			prop.load(loadFile());
			propFile = prop;
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Load property error, loading default values:"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String getProperty(String name){
		String propValue = propFile.getProperty(name, "");
		System.out.println(String.format("%s=%s",name,propValue));
		return propValue;
	}
}
