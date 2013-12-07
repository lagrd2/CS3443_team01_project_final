package CustMgtSys_1;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

/**
 * 
 * @author amartine
 * 
 *         This class is for Adding Transactions to given client ndx. The
 *         purpose of this class is to display a window that will take in
 *         transaction information to generate a transaction and add said
 *         transaction to our arraylist of transactions that will be displayed
 *         on the main window.
 * 
 *         Auto generates the date and only asks for a sku, product, price, and
 *         a JComboBox for method of payment.
 */

public class addTransactionWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField skuField;
	private JTextField itemField;
	private JTextField priceField;

	private JComboBox paymentMethod;
	private String[] payments = { "Cash", "Credit", "Check", "Debit", "VISA",
			"MasterCard" };
	private String payment = payments[0];

	private String reportDate;

	private MainWindowModel model;
	private MainWindowView view;

	public addTransactionWindow(final int ndx, final MainWindowModel model,
			final MainWindowView view) {
		super("MyCMS");

		this.model = model;
		this.view = view;

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		Date today = Calendar.getInstance().getTime();
		reportDate = df.format(today);
		JLabel date = new JLabel(reportDate);

		skuField = new JTextField(15);
		itemField = new JTextField(15);
		priceField = new JTextField(15);
		paymentMethod = new JComboBox(payments);

		String[] labels = { "Date: ", "SKU: ", "Item: ", "Price: ",
				"Payment Method" };
		int numPairs = labels.length;
		final JButton addButton;
		final JButton cancelButton;

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
				l.setLabelFor(skuField);
				p.add(skuField);
				break;
			case 2:
				l.setLabelFor(itemField);
				p.add(itemField);
				break;
			case 3:
				l.setLabelFor(priceField);
				p.add(priceField);
				break;
			case 4:
				l.setLabelFor(paymentMethod);
				p.add(paymentMethod);
				break;

			}// switch
		}// for

		addButton = new JButton("Add");
		p.add(addButton);

		cancelButton = new JButton("Cancel");
		p.add(cancelButton);

		// Lay out the panel. args (panel, rows, columns, initial x, initial y,
		// horizontal spacing, vertical spacing
		SpringUtilities.makeCompactGrid(p, numPairs + 1, 2, 6, 6, 6, 6);

		// Create and set up the window.
		final JFrame frame = new JFrame("Add Transaction");
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
				if ((skuField.getText().equals(""))
						|| (itemField.getText().equals(""))
						|| (priceField.getText().equals("")))
					JOptionPane.showMessageDialog(addButton,
							"All Fields must be filled");
				else {
					model.getDatabase().addTransaction(
							ndx,
							null,
							reportDate,
							skuField.getText(),
							itemField.getText(),
							priceField.getText(),
							payment,
							model.getDatabase().getCustomers().get(ndx)
									.getAccount());

					JOptionPane.showMessageDialog(addButton,
							"Successfully added new transaction");
					frame.dispose();
					view.updateTransactions(ndx);
					view.updateStatus("Made transaction for "
							+ model.getDatabase().getCustomers().get(ndx));
				}
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(cancelButton,
						"add Transaction Canceled");
				frame.dispose();
			}
		});

	}

}