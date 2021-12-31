package bcu.cmp5332.bookingsystem.gui;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.RemoveFlight;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

/**
 * @author Amrit Kaur and Shriya Sami
 */

/**
 * This class shows the delete flight window.
 */
public class DeleteFlight extends JFrame implements ActionListener {

	private MainWindow mw;
	private JTextField idText = new JTextField();
	private JButton addBtn = new JButton("Remove");
	private JButton cancelBtn = new JButton("Cancel");

	public DeleteFlight(MainWindow mw) {
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

		setTitle("Delete A Flight");

		setSize(800, 500);
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(3, 2));
		topPanel.add(new JLabel("Flight Id : "));
		topPanel.add(idText);


		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 3));
		bottomPanel.add(new JLabel("     "));
		bottomPanel.add(addBtn);
		bottomPanel.add(cancelBtn);

		addBtn.addActionListener(this);
		cancelBtn.addActionListener(this);

		this.getContentPane().add(topPanel, BorderLayout.CENTER);
		this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		setLocationRelativeTo(mw);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == addBtn) {
			removeFlight();
		} else if (ae.getSource() == cancelBtn) {
			this.setVisible(false);
		}

	}

	/**
	 * Method removes flight from system and display table
	 */
	private void removeFlight() {
		try {

			int id = Integer.parseInt(idText.getText());
			// create and execute the AddBook Command
			Command removeFlight = new RemoveFlight(id);
			removeFlight.execute(mw.getFlightBookingSystem());
			// refresh the view with the list of flights
			mw.displayFlights();
			// hide (close) the AddFlightWindow
			this.setVisible(false);
		} catch (FlightBookingSystemException e) {
			JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}


