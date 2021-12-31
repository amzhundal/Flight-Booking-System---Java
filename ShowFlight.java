package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;


/**
 * @author Amrit Kaur and Shriya Sami
 */

public class ShowFlight implements Command {
	private final int flightId;

	public ShowFlight(int flightId) { 
		this.flightId = flightId;
	}

	/**
	 * This method shows the flights in the system. 
	 */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		try{
			if(flightBookingSystem.getFlightByID(flightId) != null) {

				Flight flight = flightBookingSystem.getFlightByID(flightId);
				if(flight.isRemoved() != true) {
					System.out.println(flight.getDetailsLong());
				}else {
					throw new FlightBookingSystemException("This flight has been removed");
				}

			}else {
				throw new FlightBookingSystemException("The flight does not exist in the system");
			}

		}catch (FlightBookingSystemException e){
			System.out.println(e.getMessage());
		}
	}

}