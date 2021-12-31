package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.IssueBooking;
import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

public class MainWindow extends JFrame implements ActionListener {

	private JMenuBar menuBar;
	private JMenu adminMenu;
	private JMenu flightsMenu;
	private JMenu bookingsMenu;
	private JMenu customersMenu;

	private JMenuItem adminExit;

	private JMenuItem flightsView;
	private JMenuItem flightsAdd;
	private JMenuItem flightsDel;

	private JMenuItem bookingsIssue;
	private JMenuItem bookingsUpdate;
	private JMenuItem bookingsCancel;

	private JMenuItem custView;
	private JMenuItem custAdd;
	private JMenuItem custDel;

	private FlightBookingSystem fbs;

	public MainWindow(FlightBookingSystem fbs) {

		initialize();
		this.fbs = fbs;
	}

	public FlightBookingSystem getFlightBookingSystem() {
		return fbs;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {

		}

		setTitle("Flight Booking Management System");

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		//adding adminMenu menu and menu items
		adminMenu = new JMenu("Admin");
		menuBar.add(adminMenu);

		adminExit = new JMenuItem("Exit");
		adminMenu.add(adminExit);
		adminExit.addActionListener(this);

		// adding Flights menu and menu items
		flightsMenu = new JMenu("Flights");
		menuBar.add(flightsMenu);

		flightsView = new JMenuItem("View");
		flightsAdd = new JMenuItem("Add");
		flightsDel = new JMenuItem("Delete");
		flightsMenu.add(flightsView);
		flightsMenu.add(flightsAdd);
		flightsMenu.add(flightsDel);
		// adding action listener for Flights menu items
		for (int i = 0; i < flightsMenu.getItemCount(); i++) {
			flightsMenu.getItem(i).addActionListener(this);
		}

		// adding Bookings menu and menu items
		bookingsMenu = new JMenu("Bookings");

		bookingsIssue = new JMenuItem("Issue");
		bookingsUpdate = new JMenuItem("Update");
		bookingsCancel = new JMenuItem("Cancel");
		bookingsMenu.add(bookingsIssue);
		bookingsMenu.add(bookingsUpdate);
		bookingsMenu.add(bookingsCancel);
		// adding action listener for Bookings menu items
		for (int i = 0; i < bookingsMenu.getItemCount(); i++) {
			bookingsMenu.getItem(i).addActionListener(this);
		}

		// adding Customers menu and menu items
		customersMenu = new JMenu("Customers");
		menuBar.add(customersMenu);

		custView = new JMenuItem("View");
		custAdd = new JMenuItem("Add");
		custDel = new JMenuItem("Delete");

		customersMenu.add(custView);
		customersMenu.add(custAdd);
		customersMenu.add(custDel);
		// adding action listener for Customers menu items
		custView.addActionListener(this);
		custAdd.addActionListener(this);
		custDel.addActionListener(this);

		setSize(800, 500);

		setVisible(true);
		setAutoRequestFocus(true);
		toFront();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		/* Uncomment the following line to not terminate the console app when the window is closed */
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);        

	}	

	/* Uncomment the following code to run the GUI version directly from the IDE */
	public static void main(String[] args) throws IOException, FlightBookingSystemException {
		FlightBookingSystem fbs = FlightBookingSystemData.load();
		new MainWindow(fbs);			
	}



	@Override
	public void actionPerformed(ActionEvent ae) {
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                super.windowClosed(windowEvent);
                try {
                    FlightBookingSystemData.store(fbs);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
		if (ae.getSource() == adminExit) {
			try {
				FlightBookingSystemData.store(fbs);
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
			}
			System.exit(0);
		} else if (ae.getSource() == flightsView) {
			displayFlights();

		} else if (ae.getSource() == flightsAdd) {
			new AddFlightWindow(this);

		} else if (ae.getSource() == flightsDel) {
			new DeleteFlight(this);

		} else if (ae.getSource() == bookingsIssue) {


		} else if (ae.getSource() == bookingsCancel) {


		} else if (ae.getSource() == custView) {
			displayCustomers();


		} else if (ae.getSource() == custAdd) {
			new AddCustomerWindow(this);

		} else if (ae.getSource() == custDel) {
			new DeleteCustomer(this);


		}
	}

	public void displayFlights() {
		List<Flight> flightsList = fbs.getFlights();
		// headers for the table
		String[] columns = new String[]{"Flight Id", "Flight No", "Origin", "Destination", "Departure Date"};

		Object[][] data = new Object[flightsList.size()][6];
		for (int i = 0; i < flightsList.size(); i++) {
			Flight flight = flightsList.get(i);
			if(!flight.isRemoved()) {
			data[i][0] = flight.getId();
			data[i][1] = flight.getFlightNumber();
			data[i][2] = flight.getOrigin();
			data[i][3] = flight.getDestination();
			data[i][4] = flight.getDepartureDate();


		JTable table = new JTable(data, columns);
		this.getContentPane().removeAll();
		this.getContentPane().add(new JScrollPane(table));
		this.revalidate();
		table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                displayCustomerPopUp(table);
            }
        });
			}
		}
	}

	public void displayCustomers() {
		List<Customer> CustomersList = fbs.getCustomers();
		String[] columns = new String[] {"ID", "Name", "Phone", "Number of Bookings"};

		Object[][] data = new Object[CustomersList.size()][6];
		for (int i = 0; i < CustomersList.size(); i++) {
			Customer customer = CustomersList.get(i);
			if(!customer.isRemoved()) {
			data[i][0] = customer.getId();
			data[i][1] = customer.getName();
			data[i][2] = customer.getPhone();
			data[i][3] = customer.getBookings().size();

			
			JTable table = new JTable(data, columns);
			this.getContentPane().removeAll();
			this.getContentPane().add(new JScrollPane(table));
			this.revalidate();
			table.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent mouseEvent) {
	                super.mouseClicked(mouseEvent);
	                	displayBookings(table);

	            }
	        });
		}
		}
	}	

	
	public void displayBookings(JTable table) {
		int row = table.getSelectedRow();
        if(table.getValueAt(row, 0) instanceof Integer){
            String info = " ";
            int id = ((Integer) table.getValueAt(row, 0)).intValue();
            
			try {
				System.out.print(id);
				Customer customer = fbs.getCustomerByID(id);
				System.out.print(customer);
				 info = info.concat("\nThis customer has " + customer.getBookings().size() + " bookings made");
		            info = info.concat("\n");
	    			for (int j =0; j < customer.getBookings().size(); j++) {
	    				Booking booking = customer.getBookings().get(j);
	    				info = info.concat("\n" + booking.getCustomer().getName() + " " + booking.getFlight().getDetailsShort());
	                    info = info.concat("\n------------------------------\n");
	    			}
		    		
		            new JOptionPane();
					JOptionPane.showMessageDialog(new JFrame(), info);
			} catch (FlightBookingSystemException e) {
				e.printStackTrace();
			}
           
        }
		
		}
	
	public void displayCustomerPopUp(JTable table) {
		int row = table.getSelectedRow();
        if(table.getValueAt(row, 0) instanceof Integer){
            String info = " ";
            int id = ((Integer) table.getValueAt(row, 0)).intValue();
            
			try {
				System.out.print(id);
				Flight flight = fbs.getFlightByID(id);
				 info = info.concat("\nThe Flight has " + flight.getPassengers().size() + " passengers");
		            info = info.concat("\n");
	    			for (int j =0; j < flight.getPassengers().size(); j++) {
	    				Customer customer = flight.getPassengers().get(j);
	    				info = info.concat("\n" + customer.getName() + " " + customer.getPhone() + " ");
	                    info = info.concat("\n------------------------------\n");
	    			}
		    		
		            new JOptionPane();
					JOptionPane.showMessageDialog(new JFrame(), info);
			} catch (FlightBookingSystemException e) {
				e.printStackTrace();
			}  
        }	
	}  
}
		
				
		