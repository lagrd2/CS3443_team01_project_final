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
 *         This window is specifically for the case of when a new data file is
 *         to be created. its purpose is Take in an Admin Account that the user
 *         will enter and generate an ADMIN login based on that information so
 *         that the user has access to said newly created file.
 * 
 * 
 */

@SuppressWarnings("serial")
public class CreateLoginWindowINTRO extends JFrame {

	private JLabel userLabel;
	private JTextField userField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JLabel verifyLabel;
	private JPasswordField verifyPassword;
	private JButton login;

	private CustomerController database;

	private boolean admin = true;

	public CreateLoginWindowINTRO(final MasterModel master) {
		super("Create MyCMS Login");
		// Changed to DISPOSE_ON_CLOSE so that this window will not exit the
		// whole program on exit
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

		login = new JButton("Login");
		add(login);

		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String username = "";
				String password = "";
				String verify = "";

				username = String.format(userField.getText());
				password = String.format(passwordField.getText());
				verify = String.format(verifyPassword.getText());

				if (username.equals(""))
					JOptionPane.showMessageDialog(login, "Must enter UserName");
				else if (password.equals("") || verify.equals(""))
					JOptionPane.showMessageDialog(login,
							"Password cannot be empty");
				else if (password.length() < 8 || verify.length() < 8) {
					JOptionPane.showMessageDialog(login,
							"Password must be 8 characters or more");
					passwordField.setText("");
					verifyPassword.setText("");
				}// added
				else if ((password.equals(verify))) {
					JOptionPane.showMessageDialog(login,
							"successfully created login for " + username);
					try {
						ench.addaccount(username, password, admin, master);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						database = new CustomerController(master);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					new MainWindow(username, database, admin, master);
					dispose();
					// at this point, close when they press OK... and send user
					// to
					// next window.
				} else
					JOptionPane.showMessageDialog(login,
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
						try {
							database = new CustomerController(master);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						new MainWindow(username, database, admin, master);
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