package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class FlightBookingSystemData {
    
    private static final List<DataManager> dataManagers = new ArrayList<>();
    
    // runs only once when the object gets loaded to memory
    static {
        dataManagers.add(new FlightDataManager());
        
        /* Uncomment the two lines below when the implementation of their 
        loadData() and storeData() methods is complete */
        dataManagers.add(new CustomerDataManager());
        dataManagers.add(new BookingDataManager());
    }
    
    public static FlightBookingSystem load() throws FlightBookingSystemException, IOException {

    	Files.copy(Paths.get("resources/data/flights.txt"), Paths.get("temp/flightstemp.txt"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get("resources/data/customers.txt"), Paths.get("temp/customerstemp.txt"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get("resources/data/bookings.txt"), Paths.get("temp/bookingstemp.txt"), StandardCopyOption.REPLACE_EXISTING);

        FlightBookingSystem fbs = new FlightBookingSystem();
        for (DataManager dm : dataManagers) {
            dm.loadData(fbs);
        }
        return fbs;
    }

    public static void store(FlightBookingSystem fbs) throws IOException {

        for (DataManager dm : dataManagers) {
            dm.storeData(fbs);
        }
    }
    
}
