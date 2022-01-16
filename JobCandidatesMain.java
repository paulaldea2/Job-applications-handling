package com.bham.pij.assignments.candidates;

//Aldea Paul 2210814

public class JobCandidatesMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CleaningUp clean = new CleaningUp();
		clean.cleanUpFile();
		
		CandidatesToInterview candidates = new CandidatesToInterview();
		candidates.findCandidates();
		candidates.candidatesWithExperience();
		candidates.createCSVFile();
		candidates.createReport();
	}

}
