package com.bham.pij.assignments.candidates;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

//Aldea Paul 2210814

public class CleaningUp {
	
	public ArrayList<String> userdetails;
	public void cleanUpFile() {	
		int count = 0;
		userdetails = new ArrayList<>();
		String userinfo = new String();
		
		Path fileIn = Paths.get("dirtycv.txt");
		
		try {
			
			BufferedReader getInfo = new BufferedReader(
					new FileReader(fileIn.toFile()));
			
			String userInfo = getInfo.readLine();
			
			while(userInfo != null) {
				String[] userdata = userInfo.split(":");
				
				for(int i = 0; i < userdata.length; i++) {
					switch(userdata[i]) {
						case "Surname":
						{
							count ++;
							userdetails.add(userdata[i+1] + "000" + count);
							break;
						}
						case "Qualification":
						{
							userdetails.add(userdata[i+1]);
							break;
						}
						case "Position":
						{
							userdetails.add(userdata[i+1]);
							break;
						}
						case "Experience":
						{
							userdetails.add(userdata[i+1]);
							break;
						}
						case "eMail":
						{
							userdetails.add(userdata[i+1]);
							break;
						}
						case "End":
						{
							for (String user: userdetails)
								userinfo += user + ",";
							userinfo += "\n";
							userdetails.clear();
							break;
						}
					}//end of switch
				}//end of for
				userInfo = getInfo.readLine();
			}//end of while
			getInfo.close();
		}//end of try
		catch(FileNotFoundException e) {
			System.out.println("Couldn't find the file!");
			System.exit(0);
		}//end of catch
		catch(IOException e) {
			System.out.println("An I/O error occurred!");
			System.exit(0);
		}//end of catch
		
		//Write the cleancv.txt
		try(BufferedWriter writer = new BufferedWriter(
				new FileWriter("cleancv.txt"))){
			writer.write(userinfo);
		}//end of try
		catch(IOException e) {
			System.out.println("An I/O error occurred!");
			System.exit(0);
		}//end of catch
	}//end of public void cleanUpFile
}//end of CleaningUp
