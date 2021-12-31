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
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.CommandParser;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;


/**
 * @author Amrit Kaur and Shriya Sami
 */

/**
 * This class shows the add customer window.
 */

public class AddCustomerWindow extends JFrame implements ActionListener {

	private MainWindow mw;
	private JTextField NameText = new JTextField();
	private JTextField PhoneText = new JTextField();
	private JTextField EmailText = new JTextField();

	private JButton addBtn = new JButton("Add");
	private JButton CancelBtn = new JButton("Cancel");

	public AddCustomerWindow(MainWindow mw) {
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

		setTitle("Add a New Customer");

		setSize(350, 220);
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(5, 2));
		topPanel.add(new JLabel("Name : "));
		topPanel.add(NameText);
		topPanel.add(new JLabel("Phone : "));
		topPanel.add(PhoneText);
		topPanel.add(new JLabel("Email : "));
		topPanel.add(EmailText); 


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
			addBook();
		} else if (ae.getSource() == CancelBtn) {
			this.setVisible(false);
		}

	}
	private void addBook() {
		try {
			String name = NameText.getText();
			String phone = PhoneText.getText();
			String email = EmailText.getText();

			// create and execute the Customer Command
			Command addCustomer = new AddCustomer(name,phone,email);
			addCustomer.execute(mw.getFlightBookingSystem());
			// refresh the view with the list of customers
			mw.displayCustomers();
			// hide (close) the CustomerWindow
			this.setVisible(false);
		} catch (FlightBookingSystemException ex) {
			JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
