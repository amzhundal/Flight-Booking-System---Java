package bcu.cmp5332.bookingsystem.tests;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Test;

import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;

class Tests {
	
	LocalDate date = LocalDate.of(2020, 1, 8);

	@Test
	public void getNoOfSeats() {
		
		Flight flight = new Flight(1, "FlightNumber", "Origin", "Destination", date , "Number Of Seats", 72.00, 70 );
		
		assertNotNull(flight.getNoOfSeats());
	}
	
	@Test
	public void getprice() {
		Flight flight = new Flight(1, "FlightNumber", "Origin", "Destination", date , "Number Of Seats", 72.00, 70 );
		
		assertNotNull(flight.getPrice());
	}
	
	@Test
	public void getEmail() {
		Customer customer = new Customer(1, "Name", "07437721312", "name@email.com" );
		
		assertNotNull(customer.getEmail());
	}
	
	

}
