package com.bham.pij.assignments.candidates;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

//Aldea Paul 2210814

public class CandidatesToInterview {

	public ArrayList<String> candidates;
	public ArrayList<ArrayList<String>> candidatesToInterview = new ArrayList<>();
	int n = 0;
	
	public void findCandidates() {
		String[] keywordsDegree = {"Degree in Computer Science", "Masters in Computer Science"};
		String[] keywordsExperience = {"Data Analyst", "Programmer", "Computer programmer", "Operator"};
		
		candidates = new ArrayList<>();
		String interview = new String();
		
		Path fileIn = Paths.get("cleancv.txt");
		
		try {
			BufferedReader getInfo = new BufferedReader(
					new FileReader(fileIn.toFile()));
			
			String userInfo = getInfo.readLine();
			
			while(userInfo != null) {
				String[] userdata = userInfo.split(",");
				
				for(int i = 0; i < userdata.length; i++) {
					for(String degree: keywordsDegree)
						if(userdata[i].equals(degree))
							for(String exp: keywordsExperience)
								if(userdata[i+1].contains(exp))
									candidates.add(userdata[i-1]);
				}//end of for
				for(int i = 0; i < userdata.length; i++) {
					if(candidates.contains(userdata[i])) {
						for(int j = i; j < userdata.length; j++)
						{
							if(!userdata[j].equals(" "))
							{
								candidatesToInterview.add(new ArrayList<String>());
								candidatesToInterview.get(n).add(userdata[j]);
							}//end of if
							interview += userdata[j] + " ";
						}//end of for
						n++;
						interview += "\n";
					}//end of if
				}//end of first for
				userInfo = getInfo.readLine();
			}//end of while
			getInfo.close();
		}//end of try
		catch(FileNotFoundException e){
			System.out.println("Couldn't find the file");
			System.exit(0);
		}//end of catch
		catch(IOException e) {
			System.out.println("An I/O error occurred");
			System.exit(0);
		}//end of catch
		
		//Write the to-interview.txt
		try(BufferedWriter writer = new BufferedWriter(
				new FileWriter("to-interview.txt"))){
			writer.write(interview);
		}//end of try
		catch(IOException e) {
			System.out.println("An I/O error occurred!");
			System.exit(0);
		}//end of catch
	}//end of findCandidates
	
	
	//Check method for numbers
	public static boolean isNumeric(String Num) {
		if(Num == null) {
			return false;
		}//end of if
		try {
			int numb = Integer.parseInt(Num);
		}//end of try
		catch(NumberFormatException nfe) {
			return false;
		}//end of catch
		return true;
	}//end of isNumeric
	
	
	public void candidatesWithExperience() {
		
		int num = 0, ok = 0;
		String toInterview = new String();
		Path fileIn = Paths.get("to-interview.txt");
		
		try {
			BufferedReader getInfo = new BufferedReader(
					new FileReader(fileIn.toFile()));
			
			String userInfo = getInfo.readLine();
			
			while(userInfo != null) {
				
				String[] userdata = userInfo.split(" ");
				
				for(int i = 0; i < userdata.length; i++){
					if(isNumeric(userdata[i])) {
						num = Integer.parseInt(userdata[i]);
						ok ++;
						if(num > 5 && ok == 1) {
							toInterview += userdata[0] + " " + userdata[i];
							toInterview += "\n";
						}//end of if
					}//end of if
				}//end of for
				ok = 0;
				userInfo = getInfo.readLine();
			}//end of while
		getInfo.close();	
		}//end of try
		catch(FileNotFoundException e) {
			System.out.println("Couldn't find the file!");
			System.exit(0);
		}//end of catch
		catch(IOException e) {
			System.out.println("An I/O error occureed!");
			System.exit(0);
		}//end of catch
		
		//Write the to-interview-experience.txt
		try(BufferedWriter writer = new BufferedWriter(
				new FileWriter("to-interview-experience.txt"))){
			writer.write(toInterview);
		}//end of try
		catch(IOException e) {
			System.out.println("An I/O error occurred!");
			System.exit(0);
		}//end of catch
	}//end of candidatesWithExperience
	
	public void createCSVFile() {
		
		//Write into the CSV
		try(PrintWriter csvWriter = new PrintWriter(
				new FileWriter("to-interview-table-format.csv"))){
			
			String header = "Identifier,Qualification,Position1,Experience1,Position2,Experience2,eMail";
			String line = new String();
			
			StringBuilder builder = new StringBuilder();
			builder.append(header + "\n");
			
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < candidatesToInterview.get(i).size(); j++) {
					if(candidatesToInterview.get(i).size() >= 6) {
						line += candidatesToInterview.get(i).get(j).toString() + ",";
					}//end of if
					else
					{
						if(j < 4)
						{
							line += candidatesToInterview.get(i).get(j).toString() + ",";
						}
						else if(j >= 4)
						{
							line += "," + ",";	
							line += candidatesToInterview.get(i).get(4) + ",";
						}
					}//end of else
				}//end of second for
				line += "\n";
			}//end of first for
			builder.append(line);
			csvWriter.write(builder.toString());
		}//end of try
		catch(IOException e) {
			System.out.println("An I/O error occureed!");
			System.exit(0);
		}//end of catch
	}//end of createCSVFile
	
	public void createReport() {
		
		String text = new String();
		Path fileIn = Paths.get("to-interview-table-format.csv");
		
		try {
			BufferedReader getInfo = new BufferedReader(
					new FileReader(fileIn.toFile()));
			
			String userInfo = getInfo.readLine();
			userInfo = getInfo.readLine();
			
			text += String.format("%10s%19s%27s%13s%16s","Identifier", "Qualification", "Position", "Experience", "eMail");
			text += "\n";
			
			while(userInfo != null) {
				String[] userdata = userInfo.split(",");
				
				for (int i = 0; i < userdata.length; i++)
				{
					if(i < 4) {
						text += userdata[i] + "\t";
					}
					if(i == 6)
						text += "\t" + userdata[i];
				}//end of for
				text += "\n";
				userInfo = getInfo.readLine();
			}//end of while
			getInfo.close();
			System.out.println(text);
		}//end of try
		catch(FileNotFoundException e) {
			System.out.println("Couldn't find the file!");
			System.exit(0);
		}//end of catch
		catch(IOException e) {
			System.out.println("An I/O error occureed!");
			System.exit(0);
		}//end of catch
	}//end of createReport
}
