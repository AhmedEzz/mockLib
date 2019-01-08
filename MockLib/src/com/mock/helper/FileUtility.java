package com.mock.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.mock.core.MockConfiguration;

/**
 * File Utility class, contain helper methods working with files and directory.
 * 
 * @author AhmedEzz
 * @version 0.0.1
 */
public class FileUtility {

	private static final int maxSubDir = Integer.parseInt(MockConfiguration.getFolderMaxEntries());

	public static final String FILE_EXT = MockConfiguration.getFileExtension();
	
	public static List<String> fileListResult = new ArrayList<String>();

	/**
	 * Check if the directory or file exist on the providing path 
	 * 
	 * @param path The path which you need to check the file or directory exist or not
	 * @param dirName File, Directory name
	 * 
	 * @return {@link Boolean} <code>true</code> if exist, otherwise <code>false</code>
	 */
	public static boolean checkIfDirOrFileExist(String path, String dirName) {
		File file = new File(path + "/" + dirName);
		System.out.println("File path inside checkIfDirOrFileExist : " + file.getAbsolutePath());
		return file.exists();
	}

	/**
	 * Check if the service provided has mock response file saved or not.
	 * 
	 * @param path The path which you need to check the service file
	 * @param serviceName Your service name ex: allPlacesService, updateMyTime.
	 * @return {@link Boolean} <code>true</code> if exist, otherwise <code>false</code>
	 */
	public static boolean checkIfSerivceHasMockResponse(String path, String serviceName)
	{
		String service =  serviceName + FILE_EXT;
		File file = new File(path+service);
		System.out.println("Check Service mock response exist or not." + file.getAbsolutePath());
		return file.exists();
	}
	
	/**
	 * Create a directory in provided path with the provided name
	 * 
	 * @param path The path to create the directory in.
	 * @param dirName Directory name
	 * @return {@link Boolean} <code>true</code> if success, otherwise <code>false</code>
	 */
	public static boolean createDir(String path, String dirName) {
		System.out.println("Create Dir");
		File file = new File(path, dirName);
		return file.mkdirs();
	}

	/**
	 * Check if the directory reached the max number of sub directories or files, which is configured in your .properties file.
	 *  
	 * @param path Path to the main directory
	 * 
	 * @return {@link Boolean} <code>true</code> if reached the max, otherwise <code>false</code>
	 */
	public static boolean isDirReachMaxEntries(String path) {
		File file = new File(path);
		File[] subFiles = file.listFiles(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isDirectory();
			}
		});
		return (subFiles.length >= maxSubDir);
	}

	/**
	 * Write the response or any content into new file, in specific path.
	 * 
	 * @param path Path to create the new file.
	 * @param fileName File name, mainly be the service name
	 * @param fileContent The service response or any file content.
	 * @return{@link Boolean} <code>true</code> if success, otherwise <code>false</code>
	 */
	public static boolean writeToFile(String path, String fileName, String fileContent) {
		System.out.println("writeToFile");
		BufferedWriter bw = null;
		FileWriter fw = null;
		File file = new File(path, fileName);
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(fileContent);

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return true;
	}

	/**
	 * Read the file content from the provided path and provided name.
	 * 
	 * @param path Path to check the file you want to read
	 * @param fileName File name you want to read the content
	 * @return {@link String} hold the file content.
	 */
	public static String readFromFile(String path, String fileName) 
	{
		String file = path + fileName + FILE_EXT;
		byte [] fileContent = null;
		
		try {
			fileContent = Files.readAllBytes(Paths.get(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(fileContent, StandardCharsets.UTF_8);
	}
	
	/**
	 * Read the file content from the provided name.
	 * 
	 * @param fileName File name you want to read the content
	 * @return {@link String} hold the file content.
	 */
	public static String readFromFile(String fileName) 
	{
		byte [] fileContent = null;
		
		try {
			fileContent = Files.readAllBytes(Paths.get(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(fileContent, StandardCharsets.UTF_8);
	}
	
	/**
	 * Search method used to search inside the directory for list of files.
	 * 
	 * @param path Path of the directory you want to search for a file.
	 * @param fileToSearch File name you want to search for.
	 */
	public static void searchDirectory(String path, String fileToSearch)
	{
		File dir = new File(path);
		if(dir.isDirectory())
		{
			search(dir, fileToSearch);
		}
	}
	
	private static void search(File file, String fileToSearch)
	{
		if(file.isDirectory())
		{
			System.out.println("Searching directory .." + file.getAbsoluteFile());
			
			// Check file permission
			if(file.canRead())
			{
				for(File tempFile : file.listFiles())
				{
					if(tempFile.isDirectory())
					{
						search(tempFile, fileToSearch);
					}else
					{
						System.out.println("Temp File Name : " + tempFile.getName() + " <==> And i search for : " + fileToSearch);
						if(tempFile.getName().contains(fileToSearch))
						{
							fileListResult.add(tempFile.getAbsoluteFile().toString());
						}
					}
				}
			}else
			{
				System.out.println(file.getAbsoluteFile() + "Permission Denied");
			}
		}
	}
	
	/**
	 * 
	 * @return {@link List} contain all files returned for the search method.
	 */
	public List<String> getResult() {
		return fileListResult;
	  }
}
