package bcu.cmp5332.bookingsystem.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import bcu.cmp5332.bookingsystem.commands.AddCustomer;
import bcu.cmp5332.bookingsystem.commands.AddFlight;
import bcu.cmp5332.bookingsystem.commands.IssueBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.CommandParser;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

/**
 * @author Amrit Kaur and Shriya Sami
 */

/**
 * This class shows the add booking window. 
 */
public class AddBookingWindow extends JFrame implements ActionListener {

	private MainWindow mw;
	private JTextField idText = new JTextField();
	private JTextField customerText = new JTextField();
	private JTextField flightText = new JTextField();
	private JTextField bookingDateText = new JTextField();

	private JButton addBtn = new JButton("Add");
	private JButton CancelBtn = new JButton("Cancel");

	public AddBookingWindow(MainWindow mw) {
		this.mw = mw;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {

		}

		setTitle("Add a Booking");

		setSize(350, 220);
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(5, 2));
		topPanel.add(new JLabel("Customer : "));
		topPanel.add(customerText);
		topPanel.add(new JLabel("Flight : "));
		topPanel.add(flightText);
		topPanel.add(new JLabel("BookingDate (YYYY-MM-DD) : "));
		topPanel.add(bookingDateText);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 3));
		bottomPanel.add(new JLabel("     "));
		bottomPanel.add(addBtn);
		bottomPanel.add(CancelBtn);

		addBtn.addActionListener(this);
		CancelBtn.addActionListener(this);

		this.getContentPane().add(topPanel, BorderLayout.CENTER);
		this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		setLocationRelativeTo(mw);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == addBtn) {
			IssueBooking();
		} else if (ae.getSource() == CancelBtn) {
			this.setVisible(false);
		}

	}

	private void IssueBooking() {
		try {
			int customer = Integer.parseInt(customerText.getText());
			int flight = Integer.parseInt(flightText.getText());
			LocalDate bookingDate = LocalDate.parse(bookingDateText.getText());;

			// create and execute the Customer Command
			Command IssueBooking = new IssueBooking (customer, flight, bookingDate);
			IssueBooking.execute(mw.getFlightBookingSystem());
			// refresh the view with the list of customers
			mw.displayCustomers();
			// hide (close) the CustomerWindow
			this.setVisible(false);
		} catch (FlightBookingSystemException ex) {
			JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
