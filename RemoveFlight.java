package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/** 
 * @author Amrit Kaur and Shriya Sami
 */

public class RemoveFlight implements Command {

	private final int flightId;


	public RemoveFlight(int flightId) {
		this.flightId = flightId;
	}

	/**
	 * This method gets the flight by its id and removes it from the flight booking system. 
	 */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		// TODO Auto-generated method stub
		try {
			if(flightBookingSystem.getFlightByID(flightId) != null) {
				Flight flight = flightBookingSystem.getFlightByID(flightId);
				if(!flight.getPassengers().isEmpty()) {

					System.out.println("This flight currently has passengers so can not be removed");

				}else {
					flight.setRemoved(true);
					System.out.println("The flight " + flight.getDetailsShort() +" has been removed");
				}



			}else {
				throw new FlightBookingSystemException("The flight does not exist in the system");
			}

		}catch (FlightBookingSystemException e){
			System.out.println(e.getMessage());


		}

	}
}
