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

public class UpdateBooking implements Command{

	private final int customerId;
	private final int flightId;
	private final LocalDate bookingdate;
	private final LocalDate newDate;

	public UpdateBooking(int customerId, int flightId, LocalDate bookingdate, LocalDate newDate) {
		this.customerId = customerId;
		this.flightId = flightId;
		this.bookingdate = bookingdate;
		this.newDate = newDate;
	}

	/**
	 * This method updates the booking. 
	 */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		try{
			if(flightBookingSystem.getFlightByID(flightId) != null) {
				Flight flight = flightBookingSystem.getFlightByID(flightId);

				if(flight.isRemoved() != true) {

					if (flightBookingSystem.getCustomerByID(customerId) != null) {
						Customer customer = flightBookingSystem.getCustomerByID(customerId);
						if(customer.isRemoved() == false) {

							Booking booking = new Booking(customer, flight, bookingdate);

							if (customer.getBookings().contains(booking)) {

								customer.updateBooking(booking, newDate);

								System.out.println("The booking has been updated for the customer " + customerId + " successfully.");

							} else {
								throw new FlightBookingSystemException("This customer has not made this booking");
							}
						}else {
							throw new FlightBookingSystemException("This customer has been removed");
						}
					} else {
						throw new FlightBookingSystemException("This customer does not exist in the system");
					}
				}else {
					throw new FlightBookingSystemException("The booking has been removed");
				}
			}else {
				throw new FlightBookingSystemException("This booking does not exist in the system");
			}

		}catch (FlightBookingSystemException e){
			System.out.println(e.getMessage());
		}
	}}