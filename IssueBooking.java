package bcu.cmp5332.bookingsystem.commands;

import java.time.LocalDate;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * @author Amrit Kaur and Shriya Sami
 */

public class IssueBooking implements Command {
	private final int customerString;
	private final int flightString;
	private final LocalDate bookingDate;

	public IssueBooking(int customerString, int flightString, LocalDate bookingDate) {
		this.customerString = customerString; 
		this.flightString = flightString; 
		this.bookingDate = bookingDate;
	}

	/**
	 * This method gets the flight's id and customer's id to issue a booking, only if they both meet the condition. 
	 */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		// TODO Auto-generated method stub
		int flightId = flightString;

		if(flightBookingSystem.getFlightByID(flightId) != null) {
			Flight flight = flightBookingSystem.getFlightByID(flightId);
			if(!flight.isRemoved()) {

				int customerId = customerString;
				if(flightBookingSystem.getCustomerByID(customerId) != null) {
					Customer customer = flightBookingSystem.getCustomerByID(customerId);
					if(!customer.isRemoved()) {
						if(flight.getPassengers().size() +1 > flight.getmaxPassengers()) {
							System.out.println("Sorry, this booking is full");
						}else {
							Booking booking = new Booking(customer, flight, bookingDate);
							customer.addBooking(booking);
							flight.addPassenger(customer);

							System.out.println("Booking has been made.");
						}
					}else {
						throw new FlightBookingSystemException("Customer has been removed");
					}

				} else {
					throw new FlightBookingSystemException("Customer does not exist");
				}
			}else {
				throw new FlightBookingSystemException("Flight has been removed");
			}
		} else {

			throw new FlightBookingSystemException("Flight does not exist");
		}
	}
}