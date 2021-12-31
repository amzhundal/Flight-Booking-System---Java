package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    
    private int id;
    private String name;
    private String phone;
    private String email;
    private final List<Booking> bookings = new ArrayList<>();
    private boolean removed = false; 
    
    // TODO: implement constructor here
    public Customer(int id, String name,String phone,String email) {
    	this.id = id; 
    	this.name = name;
    	this.phone = phone;
    	this.email = email;
    }
    
    // TODO: implementation of Getter and Setter methods
    public int getId() {
    	return id;
    }
        
    public String getName() {
    	return name;
    }
    
    public String getPhone() {
    	return phone;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public List<Booking> getBookings() {
    	return bookings;
    }
    
    public void addBooking(Booking booking) {
        // DONE: implementation here
    	this.bookings.add(booking);
    }
    
    public void setRemoved(boolean removed) {
    	this.removed = removed;
    }
    
    public boolean isRemoved() {
		return removed;
	}
    
    public void removeBooking(Flight flight, LocalDate date) {
    	Booking bookingTemp = null;
    	for(Booking booking: this.bookings) {
    		if(booking.getFlight() == flight && booking.getBookingDate() == date) {
    			bookingTemp = booking;
    		}
    	}
    	if(bookingTemp != null) {
    		this.bookings.remove(bookingTemp);
    	}
    }
    
    public void updateBooking(Booking booking, LocalDate newDate) throws FlightBookingSystemException{
    	if(bookings.contains(booking)) {
    		booking.setBookingDate(newDate);
    	}
    }
}

