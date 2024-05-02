package Practiceconsole;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Ticket {
	private int id;
	private String name;
	private String from;
	private String to;

	Ticket(int id, String name, String from, String to) {
		this.id = id;
		this.name = name;
		this.from = from;
		this.to = to;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getfrom() {
		return from;
	}

	public String getto() {
		return to;
	}

	public String toString() {
		return id + " " + name + " " + from + " " + to;
	}

	public static void main(String[] args) {
		ArrayList<Ticket> ticket = new ArrayList<Ticket>();
		Scanner sc = new Scanner(System.in);
		int num;
		do {
			System.out.println("---Ticket Reservation---");
			System.out.println("1.Add Passengers");
			System.out.println("2.Passengers List");
			System.out.println("3.Exit");
			System.out.println("Enter your choice :");
			num = sc.nextInt();
			switch (num) {
			case 1:
				System.out.println("Passenger Id :");
				int id = sc.nextInt();
				System.out.println("Passenger Name:");
				String name = sc.next();
				System.out.println("Boarding point : ");
				String from = sc.next();
				System.out.println("Dropping point :");
				String to = sc.next();
				ticket.add(new Ticket(id, name, from, to));
				System.out.println("Passenger added successfully!");
				break;
			case 2:
				System.out.println("Passengers List\n");
				System.out.println("------------------");
				Iterator<Ticket> list = ticket.iterator();
				while (list.hasNext()) {
					Ticket t = list.next();
					System.out.println(t);
				}
				System.out.println("------------------\n");
				break;
			case 3:
				System.out.println("Exit");
			}
		} while (num != 3);

	}
}
