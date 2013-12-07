package CustMgtSys_1;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.text.MaskFormatter;

/**
 * 
 * @author amartine
 * 
 *         This windows purpose is to edit a current clients information, to
 *         change any field that may have been changed it throws usage errors if
 *         the user leaves any fields blank.
 * 
 *         once client has been edited they are placed back into the ArrayList
 *         of customers.
 * 
 */

public class EditClientWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	// buttons to be on page
	private JButton submit;
	private JButton cancel;

	private JTextField firstNameField;
	private JTextField lastNameField;
	private JFormattedTextField phoneField;
	private JTextField emailField;
	private JTextField addressField;
	private JTextField cityField;
	private JTextField zipField;

	private MainWindowModel model;
	private CustomerModel customer;

	public EditClientWindow(final MainWindowModel model, final MainWindowView view, final MainWindowController listen, final int ndx) {
		super("MyCMS");

		this.model = model;
		this.customer = model.getDatabase().getCustomers().get(ndx);

        MaskFormatter mask = null;
        try {
                mask = new MaskFormatter("(###) ###-####");
                mask.setPlaceholderCharacter('x');
        } catch (ParseException e) {
                e.printStackTrace();
        }     
		
		firstNameField = new JTextField(15);
		firstNameField.setText(customer.getFirst());

		lastNameField = new JTextField(15);
		lastNameField.setText(customer.getLast());

		phoneField = new JFormattedTextField(mask);
		phoneField.setText(customer.getPhone());

		emailField = new JTextField(15);
		emailField.setText(customer.getEmail());

		addressField = new JTextField(15);
		addressField.setText(customer.getAddress());

		cityField = new JTextField(15);
		cityField.setText(customer.getCity());

		zipField = new JTextField(15);
		zipField.setText(customer.getZip());

		String[] labels = { "First Name: ", "Last Name: ", "Phone Number: ",
				"Email: ", "Address: ", "City: ", "Zip: " };
		int numPairs = labels.length;

		JButton cancelButton;

		// Create and populate the panel.
		JPanel p = new JPanel(new SpringLayout());

		for (int i = 0; i < numPairs; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			p.add(l);

			switch (i) {
			case 0:
				l.setLabelFor(firstNameField);
				p.add(firstNameField);
				break;
			case 1:
				l.setLabelFor(lastNameField);
				p.add(lastNameField);
				break;
			case 2:
				l.setLabelFor(phoneField);
				p.add(phoneField);
				break;
			case 3:
				l.setLabelFor(emailField);
				p.add(emailField);
				break;
			case 4:
				l.setLabelFor(addressField);
				p.add(addressField);
				break;
			case 5:
				l.setLabelFor(cityField);
				p.add(cityField);
				break;
			case 6:
				l.setLabelFor(zipField);
				p.add(zipField);
				break;

			}// switch
		}// for

		submit = new JButton("Submit");
		p.add(submit);

		cancelButton = new JButton("Cancel");
		p.add(cancelButton);

		// Lay out the panel. args (panel, rows, columns, initial x, initial y,
		// horizontal spacing, vertical spacing
		SpringUtilities.makeCompactGrid(p, numPairs + 1, 2, 6, 6, 6, 6);

		// Create and set up the window.
		final JFrame frame = new JFrame("Edit Client ");
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

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((firstNameField.getText().equals(""))
						|| (lastNameField.getText().equals(""))
						|| (phoneField.getValue().equals(""))
						|| (emailField.getText().equals(""))
						|| (addressField.getText().equals(""))
						|| (cityField.getText().equals(""))
						|| (zipField.getText().equals(""))) {
					JOptionPane.showMessageDialog(submit,
							"All Fields must be filled");
				} else {
					model.getDatabase().editCustomer(ndx,
							firstNameField.getText(), lastNameField.getText(),
							(String) phoneField.getValue(), emailField.getText(),
							addressField.getText(), cityField.getText(),
							zipField.getText());
					JOptionPane.showMessageDialog(
							submit,
							"Successfully edited Client "
									+ firstNameField.getText() + " "
									+ lastNameField.getText());
                    model.getDatabase().printCustomers();
                    view.updateTable(model.getDatabase().getCustomers());
                    view.restoreListListener(listen);
                    frame.dispose();
				}
			}

		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

	}
}
