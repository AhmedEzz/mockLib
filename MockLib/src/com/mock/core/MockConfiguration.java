package com.mock.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Main Configuration class for mocking API, you have three constructors:
 * <ul>
 * <li>{@link #initMockConfiguration()} by default the configuration going to check
 * your mockConfig.properties file which take place on your project path.
 * <li>{@link #initMockConfiguration(String)} MockConfiguration class will search
 * for your mockConfig.properties file using the provided file path.
 * <li>{@link #initMockConfiguration(String, String)} MockConfiguration class will
 * search for your .properties file using the provided file name and file path.
 * </ul>
 * 
 * @author Ahmed Ezz
 * @version 0.0.1
 */
public class MockConfiguration {

	private static String folderPath;
	private static String folderMaxEntries;
	private static String fileExtension;

	
	/**
	 * By default MockConfiguration will search for mockConfig.properties in
	 * your project path and load the configuration.
	 */
	public static void initMockConfiguration() {

		File configFile = new File("mockConfig.properties");

		try {
			FileReader reader = new FileReader(configFile);
			Properties props = new Properties();
			props.load(reader);
			
			String mockFolderPath = props.getProperty("mockFolderPath");
			String mockFolderMaxEntries = props.getProperty("mockFolderMaxEntries");
			String fileExtension = props.getProperty("fileExtension");
			
			if(!isNullOrEmpty(mockFolderPath))
				setFolderPath(mockFolderPath);
			else
				throw new FileNotFoundException("Cannot Read / Found the mockConfig.properties in Build Path : \n");
			
			if(!isNullOrEmpty(mockFolderMaxEntries))
				setFolderMaxEntries(mockFolderMaxEntries);
			else
				setFolderMaxEntries("5");
			
			if(isNullOrEmpty(fileExtension))
				setFileExtension(fileExtension);
			else
				setFileExtension(".json");
			
			
			reader.close();
		} catch (FileNotFoundException ex) {
			System.err.println("Cannot Read / Found the mockConfig.properties in Build Path : \n" + ex.getMessage());
		} catch (IOException ex) {
			System.err.println("Cannot Read / Found the mockConfig.properties in Build Path : \n" + ex.getMessage());
		}
	}

	/**
	 * MockConfiguration will search for mockConfig.properties using the provided path
	 * 
	 * @param propertiesPath Path to the properties file.
	 */
	public static void initMockConfiguration(String propertiesPath) {

		File configFile = new File(propertiesPath + "/mockConfig.properties");

		try {
			FileReader reader = new FileReader(configFile);
			Properties props = new Properties();
			props.load(reader);
			
			String mockFolderPath = props.getProperty("mockFolderPath");
			String mockFolderMaxEntries = props.getProperty("mockFolderMaxEntries");
			String fileExtension = props.getProperty("fileExtension");
			
			if(!isNullOrEmpty(mockFolderPath))
				setFolderPath(mockFolderPath);
			else
				throw new FileNotFoundException("Cannot Read / Found the mockConfig.properties in Build Path : \n");
			
			if(!isNullOrEmpty(mockFolderMaxEntries))
				setFolderMaxEntries(mockFolderMaxEntries);
			else
				setFolderMaxEntries("5");
			
			if(isNullOrEmpty(fileExtension))
				setFileExtension(fileExtension);
			else
				setFileExtension(".json");
			
			reader.close();
		} catch (FileNotFoundException ex) {
			System.err.println(
					"Cannot Read / Found the mockConfig.properties in the provided Path : \n" + ex.getMessage());
		} catch (IOException ex) {
			System.err.println(
					"Cannot Read / Found the mockConfig.properties in the provided Path : \n" + ex.getMessage());
		}
	}

	/**
	 * MockConfiguration will search for .properties using the provided path, file name
	 * 
	 * @param fileName File name of the .properties file.
	 * @param propertiesPath Path to the properties file.
	 */
	public static void initMockConfiguration(String fileName, String propertiesPath) {

		File configFile = new File(propertiesPath + "/" + fileName + ".properties");

		try {
			FileReader reader = new FileReader(configFile);
			Properties props = new Properties();
			props.load(reader);
			
			String mockFolderPath = props.getProperty("mockFolderPath");
			String mockFolderMaxEntries = props.getProperty("mockFolderMaxEntries");
			String fileExtension = props.getProperty("fileExtension");
			
			if(!isNullOrEmpty(mockFolderPath))
				setFolderPath(mockFolderPath);
			else
				throw new FileNotFoundException("Cannot Read / Found the mockConfig.properties in Build Path : \n");
			
			if(!isNullOrEmpty(mockFolderMaxEntries))
				setFolderMaxEntries(mockFolderMaxEntries);
			else
				setFolderMaxEntries("5");
			
			if(isNullOrEmpty(fileExtension))
				setFileExtension(fileExtension);
			else
				setFileExtension(".json");
			
			reader.close();
		} catch (FileNotFoundException ex) {
			System.err.println("Cannot Read / Found the " + fileName + ".properties in the provided Path :"
					+ propertiesPath + " \n" + ex.getMessage());
		} catch (IOException ex) {
			System.err.println("Cannot Read / Found the " + fileName + ".properties in the provided Path :"
					+ propertiesPath + " \n" + ex.getMessage());
		}

	}

	public static String getFolderPath() {
		return folderPath;
	}

	private static void setFolderPath(String folderPath) {
		MockConfiguration.folderPath = folderPath;
	}

	public static String getFolderMaxEntries() {
		return folderMaxEntries;
	}

	private static void setFolderMaxEntries(String folderMaxEntries) {
		MockConfiguration.folderMaxEntries = folderMaxEntries;
	}

	public static String getFileExtension() {
		return fileExtension;
	}

	private static void setFileExtension(String fileExtension) {
		MockConfiguration.fileExtension = fileExtension;
	}

	private static boolean isNullOrEmpty(String value)
	{
		if(value == null )
			return true;
		if(value.trim().equals(""))
			return true;
		
		return false;
	}
}
