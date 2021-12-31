package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * @author Amrit Kaur and Shriya Sami
 */

public class AddCustomer implements Command {

	private final String name;
	private final String phone;
	private final String email;

	public AddCustomer(String name, String phone, String email) { 
		this.name = name;
		this.phone = phone;        
		this.email = email;
	}

	/**
	 * This method gets the customer's details and adds them to the flight booking system. 
	 */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		//DONE: implementation here
		if (flightBookingSystem.getCustomers().size() > 0) {
			int lastIndex = flightBookingSystem.getCustomers().size() - 1;
			int maxId = flightBookingSystem.getCustomers().get(lastIndex).getId()+1;
			Customer customer = new Customer(maxId, name, phone, email);
			flightBookingSystem.addCustomer(customer);
			System.out.println("Customer #" + customer.getId() + " added.");
		}
		else {
			Customer customer = new Customer(0, name, phone, email);
			flightBookingSystem.addCustomer(customer);
			System.out.println("Customer #" + customer.getId() + " added.");
		}
	}


}
