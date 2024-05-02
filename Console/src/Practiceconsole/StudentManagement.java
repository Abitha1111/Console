package Practiceconsole;

import java.util.ArrayList;
import java.util.Scanner;

class Student {
	private int id;
	private String name;
	private String gender;
	private String address;

	Student(int id, String name, String gender, String address) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getAddress() {
		return address;
	}
}

public class StudentManagement {
	public static void main(String[] args) {
		ArrayList<Student> student = new ArrayList<Student>();
		Scanner sc = new Scanner(System.in);
		int num;
		do {
			System.out.println("---Student Management System---");
			System.out.println("1.Add Student");
			System.out.println("2.View Students List");
			System.out.println("3.Search Student List");
			System.out.println("4.Exit");
			System.out.print("Enter your choice: ");
			num = sc.nextInt();
			switch (num) {
			case 1:
				System.out.println("Student Id:");
				int id = sc.nextInt();
				System.out.println("Student name:");
				String name = sc.next();
				System.out.println("Student Gender:");
				String gender = sc.next();
				System.out.println("Address:");
				String address = sc.next();
				student.add(new Student(id, name, gender, address));
				System.out.println("Student added successfully!\n");
				break;
			case 2:
				System.out.println("List of Students:");
				if (student.isEmpty()) {
					System.out.println("No Students not yet...");
				}
				for (Student student1 : student) {
					System.out.println("ID: " + student1.getId() + "\nName: " + student1.getName() + "\nGender: "
							+ student1.getGender() + "\nAddress: " + student1.getAddress());
				}
				break;
			case 3:
				System.out.println("Enter student ID to search:");
				int searchId = sc.nextInt();

				for (Student student2 : student) {
					if (student2.getId() == searchId) {
						System.out.println("Student found..");
						System.out.println("\nID: " + student2.getId() + "\nName: " + student2.getName() + "\nGender: "
								+ student2.getGender() + "\nAddress: " + student2.getAddress());
					} else {
						System.out.println("Student not found!");
					}
				}
				break;
			case 4:
				num = 0;
				System.out.println("Exiting Student Management System.GoodBye!");
				break;
			default:
				System.out.println("Invalid choice. Please enter a valid option.");
			}

		} while (num != 0);
		System.out.println("");

	}
}
