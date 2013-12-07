package CustMgtSys_1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

/**
 * 
 * @author amartine
 * 
 *         This window is the LoginWindow to the MainWindow The purpose of this
 *         window is to check the users login to see if it matches our encrypted
 *         users.txt it also checks if the user is an ADMIN or not( ADMIN open a
 *         window with more features than a normal user) this window is to be
 *         called when a user opens a current data file
 * 
 */

@SuppressWarnings("serial")
public class LoginWindow extends JFrame {

	private JLabel userLabel;
	private JTextField userField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JButton login;
	private CustomerController database;

	// private introviewController intro;

	public LoginWindow(final MasterModel masterModel) {
		super("MyCMS Login");
		// this.intro = intro;
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				dispose();
				masterModel.intro.rebirthIntro();
			}
		});
		// Changed to DISPOSE_ON_CLOSE so that this window will not exit the
		// whole program on exit
		// setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(335, 120);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(x, y);

		JPanel center = new JPanel();
		setLayout(new GridLayout(1, 3));
		Font font = new Font("SansSerif", Font.BOLD, 14);

		userLabel = new JLabel("UserName");
		userLabel.setFont(font);
		center.add(userLabel);

		userField = new JTextField("", 16);
		userField.setFont(font);
		center.add(userField);

		passwordLabel = new JLabel(" Password");
		passwordLabel.setFont(font);
		center.add(passwordLabel);

		passwordField = new JPasswordField("", 16);
		passwordField.setFont(font);
		center.add(passwordField);

		login = new JButton("Login");
		login.setFont(font);
		center.add(login);

		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String username = "";
				String password = "";

				username = String.format(userField.getText());
				password = String.format(passwordField.getText());
				boolean[] flag = { false, false };
				try {
					flag = ench.logincheck(username, password, masterModel);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (flag[0]) {// checks if login was correct
					dispose();
					if (flag[1]) {
						JOptionPane.showMessageDialog(login,
								"ADMIN Login Successful");
					} else
						JOptionPane
								.showMessageDialog(login, "Login Successful");

					try {
						database = new CustomerController(masterModel);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					new MainWindow(username, database, flag[1], masterModel); // open
																				// our
					// main
					// program
				} else
					// login was incorrect
					JOptionPane.showMessageDialog(login,
							"username and password is incorrect");
			}
		});

		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String username = "";
				String password = "";

				username = String.format(userField.getText());
				password = String.format(passwordField.getText());

				boolean[] flag = { false, false };
				try {
					flag = ench.logincheck(username, password, masterModel);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (event.getSource() == passwordField) {
					// this needs to get replaced with the ench.logincheck
					// method for now we leave in for testing
					if (flag[0]) {// checks if login was correct
						dispose();
						if (flag[1]) {
							JOptionPane.showMessageDialog(null,
									"ADMIN Login Successful");
						} else
							JOptionPane.showMessageDialog(null,
									"Login Successful");

						try {
							database = new CustomerController(masterModel);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						new MainWindow(username, database, flag[1], masterModel); // open
																					// our
						// main
						// program
					} else
						// login was incorrect
						JOptionPane.showMessageDialog(null,
								"username and password is incorrect");
				}
			}
		});

		add(center, BorderLayout.CENTER);
		setResizable(false);
		setVisible(true);
	}
}