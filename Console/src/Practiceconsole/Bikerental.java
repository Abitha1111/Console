package Practiceconsole;

//public class Bikerental {
//	package ZSGS;

	import java.util.ArrayList;
	import java.util.List;
	import java.util.Scanner;

	class Customer {
	    private String name;
	    private String phoneNumber;
	    private List<String> rentedBikeNumbers;
	    private List<Integer> hoursRented;

	    public Customer(String name, String phoneNumber) {
	        this.name = name;
	        this.phoneNumber = phoneNumber;
	        this.rentedBikeNumbers = new ArrayList<>();
	        this.hoursRented = new ArrayList<>();
	    }

	    public String getName() {
	        return name;
	    }

	    public String getPhoneNumber() {
	        return phoneNumber;
	    }

	    public List<String> getRentedBikeNumbers() {
	        return rentedBikeNumbers;
	    }

	    public List<Integer> getHoursRented() {
	        return hoursRented;
	    }

	    public void addRentalInfo(String bikeNumber, int hours) {
	        rentedBikeNumbers.add(bikeNumber);
	        hoursRented.add(hours);
	    }
	}

	class BikeRental {
	    private static final double COST_PER_HOUR = 50.0;

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.println("Welcome to Bike Rental Service!");

	        System.out.print("Enter customer name: ");
	        String customerName = scanner.nextLine();

	        System.out.print("Enter customer phone number: ");
	        String phoneNumber = scanner.nextLine();

	        System.out.print("Enter the number of bikes the customer want to rent: ");
	        int numberOfBikes = scanner.nextInt();

	        Customer customer = new Customer(customerName, phoneNumber);

	        for (int i = 1; i <= numberOfBikes; i++) {
	            System.out.print("Enter bike number " + i + "to rent: ");
	            String bikeNumber = scanner.next();

	            System.out.print("Enter the hours for bike " + bikeNumber + " rental: ");
	            int hoursRented = scanner.nextInt();

	            customer.addRentalInfo(bikeNumber, hoursRented);
	        }

	        double totalCost = calculateTotalCost(customer);

	        System.out.println("\nCustomer Details:");
	        System.out.println("Name: " + customer.getName());
	        System.out.println("Phone Number: " + customer.getPhoneNumber());
	        System.out.println("\nRental Information:");
	        List<String> rentedBikes = customer.getRentedBikeNumbers();
	        List<Integer> hoursRented = customer.getHoursRented();
	        for (int i = 0; i < rentedBikes.size(); i++) {
	            System.out.println("Bike Number: " + rentedBikes.get(i) + ", Hours Rented: " + hoursRented.get(i));
	        }
	        System.out.println("\nTotal Cost: $" + totalCost);

	        scanner.close();
	    }

	    private static double calculateTotalCost(Customer customer) {
	        List<Integer> hoursRented = customer.getHoursRented();
	        double totalCost = 0;
	        for (int hours : hoursRented) {
	            totalCost += COST_PER_HOUR * hours;
	        }
	        return totalCost;
	    }
	}


