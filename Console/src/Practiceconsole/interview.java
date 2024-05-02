package Practiceconsole;

import java.util.*;

class Candidate {
    private String name;

    public Candidate(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Interviewer {
    private String name;
    private String role;

    public Interviewer(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}

class Panel {
    private Queue<Candidate> candidateQueue;
    private List<Interviewer> interviewers;

    public Panel() {
        this.candidateQueue = new LinkedList<>();
        this.interviewers = new ArrayList<>();
    }

    public void addCandidate(Candidate candidate) {
        candidateQueue.offer(candidate);
        System.out.println("Candidate " + candidate.getName() + " added to the queue.");
    }

    public void addInterviewer(Interviewer interviewer) {
        interviewers.add(interviewer);
        System.out.println("Interviewer " + interviewer.getName() + " added to the panel.");
    }

    public void conductInterview() {
        if (candidateQueue.isEmpty()) {
            System.out.println("No candidates in the queue for interview.");
        } else {
            Candidate candidate = candidateQueue.poll();
            System.out.println("Interviewing candidate: " + candidate.getName());

            if (!interviewers.isEmpty()) {
                Interviewer interviewer = interviewers.get(0); // Assign the first available interviewer
                System.out.println("Interviewer: " + interviewer.getName() + ", Role: " + interviewer.getRole());
            } else {
                System.out.println("No interviewers available.");
            }
        }
    }
}

public class interview {
    public static void main(String[]args) {
        Scanner scanner = new Scanner(System.in);
        Panel interviewPanel = new Panel();
        System.out.println("-----Interview Panel-----");

        while (true) {
            System.out.println("1. Add Candidate");
            System.out.println("2. Add Interviewer");
            System.out.println("3. Conduct Interview");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter candidate name: ");
                    String candidateName = scanner.next();
                    Candidate candidate = new Candidate(candidateName);
                    interviewPanel.addCandidate(candidate);
                    break;

                case 2:
                    System.out.print("Enter interviewer name: ");
                    String interviewerName = scanner.next();
                    System.out.print("Enter interviewer role: ");
                    String interviewerRole = scanner.next();
                    Interviewer interviewer = new Interviewer(interviewerName, interviewerRole);
                    interviewPanel.addInterviewer(interviewer);
                    break;

                case 3:
                    interviewPanel.conductInterview();
                    break;

                case 4:
                    System.out.println("Exiting the interview panel");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}









//package com.zsgs.interviewpanel.addcandidate;
//
//import java.util.Scanner;
//
//import com.zsgs.interviewpanel.dto.Candidate;
//
//public class AddCandidate {
//	private AddCandidateViewMode addCandidateViewModel;  
//
//	public AddCandidate(){
//		addCandidateViewModel = new AddCandidateViewMode(this);
//	}
//	
//	public void getCandidateInfo() {
//		//getinfo logic
//		Scanner sc = new Scanner(System.in);
//		Candidate candidate = new Candidate();
//		candidate.setName(sc.nextLine());
//		addCandidateViewModel.validate(candidate);
//	}
//
//	public void onSuccess() {
//		System.out.println("Inserted Successfully");
//
//	}
//
//	public void showError(String errorMessage) {
//		System.out.println(errorMessage);
//	}
//}