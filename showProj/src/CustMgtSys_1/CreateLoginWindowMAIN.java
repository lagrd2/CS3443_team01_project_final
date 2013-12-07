package CustMgtSys_1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.*;

/**
 * 
 * @author amartine
 * 
 *         This window is made specifically for the event that an Admin user for
 *         a data file would like to add another account to access said data
 *         file. the user may enter new users "username" and a temporary
 *         password (any user may change their password with the ChangePassword
 *         option in the JMenuBar) and choose whether to grant the new user
 *         Admin rights or normal access rights. upon completion, "create"
 *         button is pressed and new user is encrypted and added to the .auth
 *         file of the data file.
 */

@SuppressWarnings("serial")
public class CreateLoginWindowMAIN extends JFrame {

	private JLabel userLabel;
	private JTextField userField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JLabel verifyLabel;
	private JPasswordField verifyPassword;
	private JButton create;
	private JLabel checkAdmin;

	private CustomerController database;

	private String choice[] = { "no", "yes" };
	private JComboBox adminCheck;
	private boolean admin = false;

	public CreateLoginWindowMAIN(final MasterModel master) {
		super("Create MyCMS Login");
		// Changed to DISPOSE_ON_CLOSE so that this window will not exit the
		// whole program on exit
		// setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(385, 135);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				dispose();
				master.intro.rebirthIntro();
			}
		});

		setLayout(new FlowLayout());

		userLabel = new JLabel("Create UserName");
		add(userLabel);

		userField = new JTextField("", 20);
		add(userField);

		passwordLabel = new JLabel(" Create Password");
		add(passwordLabel);

		passwordField = new JPasswordField("", 20);
		add(passwordField);

		verifyLabel = new JLabel("  Verify Password");
		add(verifyLabel);

		verifyPassword = new JPasswordField("", 20);
		add(verifyPassword);

		checkAdmin = new JLabel(" Administrator");
		add(checkAdmin);

		adminCheck = new JComboBox(choice);
		add(adminCheck);

		adminCheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					if (adminCheck.getSelectedIndex() == 1)
						admin = true;
				}
			}
		});

		create = new JButton("Create");
		add(create);

		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String username = "";
				String password = "";
				String verify = "";

				username = String.format(userField.getText());
				password = String.format(passwordField.getText());
				verify = String.format(verifyPassword.getText());

				if (username.equals(""))
					JOptionPane
							.showMessageDialog(create, "Must enter UserName");
				else if (password.equals("") || verify.equals(""))
					JOptionPane.showMessageDialog(create,
							"Password cannot be empty");
				else if (password.length() < 8 || verify.length() < 8) {
					JOptionPane.showMessageDialog(create,
							"Password must be 8 characters or more");
					passwordField.setText("");
					verifyPassword.setText("");
				}// added
				else if ((password.equals(verify))) {
					JOptionPane.showMessageDialog(create,
							"successfully created login for " + username);
					try {
						ench.addaccount(username, password, admin, master);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dispose();
					// at this point, close when they press OK... and send user
					// to
					// next window.
				} else
					JOptionPane.showMessageDialog(create,
							"passwords do not match");
			}
		});

		verifyPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String username = "";
				String password = "";
				String verify = "";

				username = String.format(userField.getText());
				password = String.format(passwordField.getText());
				verify = String.format(verifyPassword.getText());

				if (event.getSource() == verifyPassword) {
					if (username.equals(""))
						JOptionPane.showMessageDialog(null,
								"Must enter UserName");
					else if (password.equals("") || verify.equals(""))
						JOptionPane.showMessageDialog(null,
								"Password cannot be empty");
					else if (password.length() < 8 || verify.length() < 8) {
						JOptionPane.showMessageDialog(null,
								"Password must be 8 characters or more");
						passwordField.setText("");
						verifyPassword.setText("");
					} else if ((password.equals(verify))) {
						JOptionPane.showMessageDialog(null,
								"successfully created login for " + username);
						try {
							ench.addaccount(username, password, admin, master);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						dispose();
						// at this point, close when they press OK... and send
						// user
						// to next window.
					} else
						JOptionPane.showMessageDialog(null,
								"passwords do not match");
				}
			}
		});

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(x, y);
		setVisible(true);
		setResizable(false);

	}
}