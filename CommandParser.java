package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.commands.LoadGUI;
import bcu.cmp5332.bookingsystem.commands.ListFlights;
import bcu.cmp5332.bookingsystem.commands.AddFlight;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.Help;
import bcu.cmp5332.bookingsystem.commands.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CommandParser {

	public static Command parse(String line) throws IOException, FlightBookingSystemException {
		try {
			String[] parts = line.split(" ", 3);
			String cmd = parts[0];

			if (cmd.equals("addflight")) {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Flight Number: ");
				String flightNumber = br.readLine();
				System.out.print("Origin: ");
				String origin = br.readLine();
				System.out.print("Destination: ");
				String destination = br.readLine();
				System.out.print("Number Of Seats: ");
				String noOfSeats = br.readLine();
				System.out.println("Price: ");
				Double price = Double.parseDouble(br.readLine());
				System.out.println("Maximum Number Of Passengers: ");
				int maxPassengers = Integer.parseInt(br.readLine()); 

				LocalDate departureDate = parseDateWithAttempts(br);
				return new AddFlight(flightNumber, origin, destination, departureDate, noOfSeats, price, maxPassengers);
				
			} else if (cmd.equals("addcustomer")) {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Name: ");
				String name = br.readLine();
				System.out.print("Phone: ");
				String phone = br.readLine();
				System.out.print("Email: ");
				String email = br.readLine();
				
				return new AddCustomer(name, phone, email);
			

			} else if (cmd.equals("loadgui")) {
				return new LoadGUI();
			} else if (parts.length == 1) {
				if (line.equals("listflights")) {
					return new ListFlights();
				} else if (line.equals("listcustomers")) {
					return new ListCustomers();
				} else if (line.equals("help")) {
					return new Help();
				}
			} else if (parts.length == 2) {
				int id = Integer.parseInt(parts[1]);

				if (cmd.equals("showflight")) {
					
				} else if (cmd.equals("showcustomer")) {

				}
			} else if (parts.length == 3) {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				int customerId = Integer.parseInt(parts[1]);
				int flightId = Integer.parseInt(parts[2]);
				
				if (cmd.equals("addbooking")) {
					LocalDate bookingDate = parseDateWithAttempts(br);
					return new IssueBooking(customerId, flightId , bookingDate);

				} else if (cmd.equals("editbooking")) {
					System.out.println("Enter the booking date:  ");
					LocalDate bookingDate = parseDateWithAttempts(br);
					System.out.println("Enter the new date:");
					LocalDate newDate = parseDateWithAttempts(br);
					return new UpdateBooking(customerId, flightId, bookingDate, newDate);

				} else if (cmd.equals("cancelbooking")) {
					LocalDate bookingDate = parseDateWithAttempts(br, 3);
					return new CancelBooking(customerId, flightId, bookingDate);
				}
			}
		} catch (NumberFormatException ex) {

		}

		throw new FlightBookingSystemException("Invalid command.");
	}

	private static LocalDate parseDateWithAttempts(BufferedReader br, int attempts) throws IOException, FlightBookingSystemException {
		if (attempts < 1) {
			throw new IllegalArgumentException("Number of attempts should be higher that 0");
		}
		while (attempts > 0) {
			attempts--;
			System.out.print("Departure Date (\"YYYY-MM-DD\" format): ");
			try {
				LocalDate departureDate = LocalDate.parse(br.readLine());
				return departureDate;
			} catch (DateTimeParseException dtpe) {
				System.out.println("Date must be in YYYY-MM-DD format. " + attempts + " attempts remaining...");
			}
		}

		throw new FlightBookingSystemException("Incorrect departure date provided. Cannot create flight.");
	}

	private static LocalDate parseDateWithAttempts(BufferedReader br) throws IOException, FlightBookingSystemException {
		return parseDateWithAttempts(br, 3);
	}
}
