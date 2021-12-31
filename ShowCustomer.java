package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * @author Amrit Kaur and Shriya Sami
 */

public class ShowCustomer implements Command {
	private final int customerId;

	public ShowCustomer(int customerId) { 
		this.customerId = customerId;
	}
	
	/**
	 * This method shows the customers in the system. 
	 */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		// TODO Auto-generated method stub
		try{

			if (flightBookingSystem.getCustomerByID(customerId) != null) {
				Customer customer = flightBookingSystem.getCustomerByID(customerId);
				if(customer.isRemoved() != true) {
					System.out.println(customer.getName());
					System.out.println(customer.getId());
					System.out.println(customer.getPhone());
					System.out.println(customer.getEmail());
					int size = customer.getBookings().size();

					for (Booking booking : customer.getBookings()) {

							System.out.println(booking.getBookingDate());
							System.out.println();
					}
					

					System.out.println("The customer has " + size + " bookings made");
				}
			}else {
				throw new FlightBookingSystemException("The customer does not exist in the system");
			}


		}catch (FlightBookingSystemException e){
			System.out.println(e.getMessage());
		}
	}

}


