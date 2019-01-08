package com.mock.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.mock.core.MockConfiguration;

/**
 * The main mock file responsible for create / read mock services.
 * 
 * @author AhmedEzz
 * @version 0.0.1
 */
public class MockUtility {
	
	/**
	 * Create Mock file for the provided service and response.
	 * 
	 * @param serviceName Service name which executed and you want to save it's response, it's used in file naming
	 * @param userId Logged in profile who execute the service, it's used in file naming
	 * @param jsonNode Response of the executed service.
	 * @return {@link Boolean} <code>True</code> in success, otherwise <code>False</code>
	 */
	public static boolean createMockService(String serviceName, String userId, JsonNode jsonNode)
	{
		System.out.println("Creating MockService");
		String fileName = serviceName + "_" + userId + FileUtility.FILE_EXT;
		if(FileUtility.createDir(MockConfiguration.getFolderPath(), userId)){
			String userPath = MockConfiguration.getFolderPath() + "/" + userId;
			System.out.println("The path of user : " + userPath);
			return FileUtility.writeToFile(userPath, fileName, jsonNode.asText());
		}else{
			System.out.println("Folder Already Exist ,, so create new file to new respons");
			String userPath = MockConfiguration.getFolderPath() + "/" + userId;
			System.out.println("The path of user : " + userPath);
			return FileUtility.writeToFile(userPath, fileName, jsonNode.asText());
		}
	}
	
	/**
	 * Read Mock file for the provided service and user id, in case the service does not have mock file it is going to search for same mock file but different user ID
	 * 
	 * @param serviceName Service name which executed and you want to read it's response.
	 * @param userId Logged in profile who execute the service.
	 * @return {@link String} response read from the mock file.
	 */
	public static String getMockResponse(String serviceName, String userId)
	{
		System.out.println("Return Mock Response ...");
		String fileName = "/" + serviceName + "_" + userId;
		if(FileUtility.checkIfDirOrFileExist(MockConfiguration.getFolderPath(), userId))
		{
			System.out.println("we found that user has mock response folder..");
			
			if(FileUtility.checkIfSerivceHasMockResponse(MockConfiguration.getFolderPath() + "/" +userId, fileName))
			{
				System.out.println("This user hase mock response service");
				String mockResponse = FileUtility.readFromFile(MockConfiguration.getFolderPath() + "/" +userId, fileName);
				System.out.println("Mock Response :: " + mockResponse);
			}else
				// Search for any JSON Response for this service name and returned back
			{
				System.out.println("This user has no mock response services .. search for any matching mock responses.");
				FileUtility.searchDirectory(MockConfiguration.getFolderPath(), serviceName);
				int noOfFiles = FileUtility.fileListResult.size();
				System.out.println("We found : " + noOfFiles + " mock response for this service.");
				if(noOfFiles == 0)
				{
					System.out.println("No Mock response saved for service : " + serviceName);
				}else
				{
					System.out.println("Mock Response after Search : " + FileUtility.readFromFile(FileUtility.fileListResult.get(0)));
				}
				
			}
		}else{
			System.out.println("File or Dir not Exist");
		}
		return "";
	}

}
