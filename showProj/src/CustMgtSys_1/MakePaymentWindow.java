package CustMgtSys_1;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
import javax.swing.text.View;

/**
 * 
 * @author amartine
 * 
 *         The purpose of this window is capture payment information. if a
 *         client pays with Credit (meaning some sort of credit with store or
 *         company using this software which would cause client to owe money to
 *         store that the would need to pay back) the the client may make a
 *         payment with either cash or check of whatever amount that they desire
 *         and be instantly credited to their account generating a new balance.
 * 
 */

public class MakePaymentWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField paymentAmount;

	private JComboBox paymentMethod;
	private String[] payments = { "Cash", "Check" };
	private String payment;

	private String reportDate;

	private MainWindowModel model;
	private MainWindowView view;

	public MakePaymentWindow(final int ndx, final MainWindowModel model,
			final MainWindowView view) {
		super("MyCMS");

		this.model = model;
		this.view = view;

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		Date today = Calendar.getInstance().getTime();
		reportDate = df.format(today);
		JLabel date = new JLabel(reportDate);

		paymentAmount = new JTextField(15);
		paymentMethod = new JComboBox(payments);

		String[] labels = { "Date: ", "Current Account Balance",
				"Payment Amount", "Payment Method" };
		int numPairs = labels.length;
		final JButton addButton;
		final JButton cancelButton;
		// JButton backButton;

		// Create and populate the panel.
		JPanel p = new JPanel(new SpringLayout());

		for (int i = 0; i < numPairs; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			p.add(l);

			switch (i) {
			case 0:
				l.setLabelFor(date);
				p.add(date);
				break;
			case 1:
				JLabel AccountBalance = new JLabel(Double.toString(model
						.getDatabase().getCustomers().get(ndx).getBalance()));
				l.setLabelFor(AccountBalance);
				p.add(AccountBalance);
				break;
			case 2:
				l.setLabelFor(paymentAmount);
				p.add(paymentAmount);
				break;
			case 3:
				l.setLabelFor(paymentMethod);
				p.add(paymentMethod);
				break;

			}// switch
		}// for

		addButton = new JButton("Make Payment");
		p.add(addButton);

		cancelButton = new JButton("Cancel");
		p.add(cancelButton);

		// Lay out the panel. args (panel, rows, columns, initial x, initial y,
		// horizontal spacing, vertical spacing
		SpringUtilities.makeCompactGrid(p, numPairs + 1, 2, 6, 6, 6, 6);

		// Create and set up the window.
		final JFrame frame = new JFrame("Make Payment");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);

		// Set up the content pane.
		p.setOpaque(true); // content panes must be opaque
		frame.setContentPane(p);

		// Display the window.
		frame.pack();
		frame.setVisible(true);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);

		paymentMethod.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					payment = payments[paymentMethod.getSelectedIndex()];
				}
			}
		});

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((paymentAmount.getText().equals("")))
					JOptionPane.showMessageDialog(addButton,
							"Payment must be entered");
				else {
					int ndx = model.getSelectedCustomer();
					model.getDatabase()
							.getCustomers()
							.get(ndx)
							.setBalance(
									model.getDatabase().getCustomers().get(ndx)
											.getBalance()
											+ (Double.parseDouble(paymentAmount
													.getText())));
					JOptionPane.showMessageDialog(
							addButton,
							"Successfully made Payment on your account. new balance is: "
									+ model.getDatabase().getCustomers()
											.get(ndx).getBalance());					
				
					view.updateStatus("Made Payment for "
							+ model.getDatabase().getCustomers()
									.get(model.getSelectedCustomer()));
					
					
					ArrayList<CustomerModel> results = new ArrayList<CustomerModel>();
					results.add( model.getDatabase().getCustomers().get(ndx));
					view.updateTable(results);
					view.updateTransactions(ndx);
					frame.dispose();
				}
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(cancelButton, "Payment Canceled");
				frame.dispose();
			}
		});

	}

}