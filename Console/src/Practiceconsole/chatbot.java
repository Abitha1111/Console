package Practiceconsole;

import java.util.Scanner;
import java.util.Stack;

public class chatbot {
	public static void main(String[] args) {
		Stack<String> conversationStack = new Stack<>();
		conversationStack.push("greeting");

		System.out.println("ChatBot:Greetings for Master Class!");

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("You: ");
			String userInput = scanner.nextLine();

			if ("exit".equalsIgnoreCase(userInput)) {
				System.out.println("ChatBot: Goodbye! Keep learning!");
				break;
			}

			String response = "";

			switch (conversationStack.peek()) {
			case "greeting":
				response = "Hi! How can I assist you.";
				conversationStack.push("main");
				break;
			case "main":
				if (userInput.contains("view courses")) {
					response = "We offer the following courses:\n" + "1. Mathematics\n" + "2. Science\n"
							+ "3. History\n" + "4. Programming\n" + "Please enter the number for course you want.";
					conversationStack.push("chooseCourse");
				} else if (userInput.contains("course details")) {
					response = "Sure! Here the course details we will provide later... ";
				} else if (userInput.contains("resources")) {
					response = "We provide various resources\nsuch as textbooks, online materials, and video lectures.\nWhat do you need?";
				} else if (userInput.contains("help")) {
					response = "I'm here to help you about Master Class.\nview courses,course details,resources...";
				} else if (userInput.contains("ok")) {
					response = "Thanks for using Master Class...";
				} else {
					response = "I'm not sure how to respond to that. Can you provide more details or ask another question related to education?";
				}
				break;
			case "chooseCourse":

				try {
					int courseNumber = Integer.parseInt(userInput);
					response = processCourseSelection(courseNumber);
					conversationStack.pop();
				} catch (NumberFormatException e) {
					response = "Please enter a valid course number.";
				}
				break;
			case "exit":
				break;
			default:
				response = "I'm not sure how to respond to that. Can you be more specific?";
				break;
			}

			System.out.println("ChatBot: " + response);
		}
	}

	private static String processCourseSelection(int courseNumber) {
		switch (courseNumber) {
		case 1:
			return "You have selected Mathematics. Let me provide you with more information about the Mathematics course.";
		case 2:
			return "You have selected Science. Let me provide you with more information about the Science course.";
		case 3:
			return "You have selected History. Let me provide you with more information about the History course.";
		case 4:
			return "You have selected Programming.\nLet me provide you with more information about the Programming course.";
		default:
			return "Please enter a valid course number.";
		}
	}
}
