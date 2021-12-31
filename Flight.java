package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Flight {
    
    private int id;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private String noOfSeats;
    private Double price;
    private boolean removed = false; 
    private int maxPassengers; 

    

	private final Set<Customer> passengers;

    public Flight(int id, String flightNumber, String origin, String destination, LocalDate departureDate, String noOfSeats, Double price, int maxPassengers) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.noOfSeats = noOfSeats;
        this.price = price;
        this.maxPassengers = maxPassengers;
        
        passengers = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    
    public String getOrigin() {
        return origin;
    }
    
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
    
    public String getNoOfSeats() {
    	return noOfSeats;
    }
    
    public void setNoOfSeats(String noOfSeats) {
    	this.noOfSeats = noOfSeats;
    }
    
    public Double getPrice() {
    	return price;
    }
    
    public void setPrice(Double price) {
    	this.price = price;
    }
    
    public int getmaxPassengers() {
    	return maxPassengers;
    }
    
    public void setMaxPassengers(int maxPassengers) {
    	this.maxPassengers = maxPassengers;
    }

    public List<Customer> getPassengers() {
        return new ArrayList<>(passengers);
    }
	
    public String getDetailsShort() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return "Flight #" + id + " - " + flightNumber + " - " + origin + " to " 
                + destination + " on " + departureDate.format(dtf);
    }

    public String getDetailsLong() {
        //DONE: implementation here
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return "Flight #" + id + " - " + flightNumber + " - " + origin + " to " 
        + destination + " on " + departureDate.format(dtf) + " with number of seats " + noOfSeats + " starting at a price of " + price;
    }
    
    public void addPassenger(Customer passenger) {
        passengers.add(passenger);
    }
    
    public void setRemoved(boolean removed) {
    	this.removed = removed;
    }
    
    public boolean isRemoved() {
		return removed;
	}
}
