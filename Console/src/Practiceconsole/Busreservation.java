package Practiceconsole;

//public class BusReservationSystem {
	

	import java.util.Scanner;
	class Bus {
	    private String busNumber;
	    private int totalSeats;
	    private boolean[] seats;

	    // Constructor
	    public Bus(String busNumber, int totalSeats) {
	        this.busNumber = busNumber;
	        this.totalSeats = totalSeats;
	        seats = new boolean[totalSeats];
	        // Initialize all seats as available
	        for (int i = 0; i < totalSeats; i++) {
	            seats[i] = true;
	        }
	    }

	    // Method to check seat availability
	    public boolean checkAvailability(int seatNumber) {
	        if (seatNumber < 1 || seatNumber > totalSeats) {
	            return false;
	        }
	        return seats[seatNumber - 1];
	    }

	    // Method to book a seat
	    public boolean bookSeat(int seatNumber) {
	        if (seatNumber < 1 || seatNumber > totalSeats || !seats[seatNumber - 1]) {
	            return false;
	        }
	        seats[seatNumber - 1] = false;
	        return true;
	    }

	    // Getters
	    
	    public String getBusNumber() {
	        return busNumber;
	    }

	    public int getTotalSeats() {
	        return totalSeats;
	    }
	}


	public class Busreservation {
	    private Bus bus;
	    private boolean[] seats;

	    public Busreservation(Bus bus) {
	        this.bus = bus;
	        seats = new boolean[bus.getTotalSeats()];
	    }

	    public void displayAvailableSeats() {
	        System.out.println("Available seats for Bus Number " + bus.getBusNumber() + ":");
	        for (int i = 0; i < bus.getTotalSeats(); i++) {
	            if (seats[i]) {
	                System.out.print("X ");
	            } else {
	                System.out.print(i + 1 + " ");
	            }
	            if ((i + 1) % 4 == 0) {
	                System.out.println();
	            }
	        }
	        System.out.println();
	    }

	    public void bookTicket(int seatNumber, String passengerName, String passengerContact) {
	        if (seatNumber < 1 || seatNumber > bus.getTotalSeats()) {
	            System.out.println("Invalid seat number.");
	            return;
	        }
	        if (seats[seatNumber - 1]) {
	            System.out.println("XXXX Seat " + seatNumber + " is not available XXXX\n");
	            return;
	        }

	        seats[seatNumber - 1] = true;
	        System.out.println("Seat " + seatNumber + " booked successfully for " + passengerName);

	        // Display confirmation ticket details
	        
	        System.out.println("\nConfirmation Ticket Details:");
	        System.out.println("Bus Number: " + bus.getBusNumber());
	        System.out.println("Seat Number: " + seatNumber);
	        System.out.println("Passenger Name: " + passengerName);
	        System.out.println("Passenger Contact: " + passengerContact);
	        System.out.println("\n");
	    }

	    public static void main(String[] args) {
	        Bus bus = new Bus("TN68Y5675", 20); // Example bus with 20 seats
	        Busreservation reservationSystem = new Busreservation(bus);
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("-----Welcome to the BusReservation System-----");

	        while (true) {
	        	
	            System.out.println("1. Display Available Seats");
	            System.out.println("2. Book a Seat");
	            System.out.println("3. Exit");
	            System.out.print("Enter your choice: ");
	            int choice = scanner.nextInt();
	            System.out.println("");

	            switch (choice) {
	                case 1:
	                    reservationSystem.displayAvailableSeats();
	                    break;
	                case 2:
	                    System.out.print("Enter seat number: ");
	                    int seatNumber = scanner.nextInt();
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter passenger name: ");
	                    String passengerName = scanner.nextLine();
	                    System.out.print("Enter passenger contact: ");
	                    String passengerContact = scanner.nextLine();
	                    System.out.println("");
	                    reservationSystem.bookTicket(seatNumber, passengerName, passengerContact);
	                    break;
	                case 3:
	                    System.out.println("Exiting...");
	                    scanner.close();
	                    System.exit(0);
	                    break;
	                default:
	                    System.out.println("Invalid choice! Try again.");
	            }
	        }
	    }
	}
