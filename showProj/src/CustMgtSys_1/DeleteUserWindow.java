package CustMgtSys_1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

/**
 * 
 * @author amartine
 * 
 *         This window is to be only Accessed by a user who has ADMIN rights to
 *         the data file. if necessary the Admin may delete any users account.
 *         however must have said users password for security reasons.
 * 
 * 
 */

public class DeleteUserWindow extends JFrame {
	private JLabel userLabel;
	private JTextField userField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JLabel verifyLabel;
	private JPasswordField verifyPassword;
	private JButton delete;

	public DeleteUserWindow(final MasterModel master) {
		super("MyCMS Delete User");
		// Changed to DISPOSE_ON_CLOSE so that this window will not exit the
		// whole program on exit
		// setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(385, 155);

		userLabel = new JLabel("UserName                ");
		add(userLabel);

		userField = new JTextField("", 20);
		add(userField);

		setLayout(new FlowLayout());
		passwordLabel = new JLabel("Password                 ");
		add(passwordLabel);

		passwordField = new JPasswordField("", 20);
		add(passwordField);

		verifyLabel = new JLabel("Verify Password      ");
		add(verifyLabel);

		verifyPassword = new JPasswordField("", 20);
		add(verifyPassword);

		delete = new JButton("Delete User");
		add(delete);

		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String username = String.format(userField.getText());
				String password = String.format(passwordField.getText());
				String PasswordVerify = String.format(verifyPassword.getText());
				// if (event.getSource() == verifyPassword) {

				boolean[] flag = { false, false };
				try {
					flag = ench.logincheck(username, password, master);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (flag[0] == false)
					JOptionPane.showMessageDialog(null, "Wrong Password");
				else if (password.equals("") || PasswordVerify.equals(""))
					JOptionPane.showMessageDialog(null,
							"Password cannot be empty");
				else if (password.length() < 8 || PasswordVerify.length() < 8) {
					JOptionPane.showMessageDialog(null,
							"Password must be 8 characters or more");
					passwordField.setText("");
					verifyPassword.setText("");
				} else if ((password.equals(PasswordVerify))) {
					JOptionPane.showMessageDialog(null,
							"successfully Deleted User " + username);
					try {
						ench.deleteaccount(username, password, flag[1], master);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dispose();
					// at this point, close when they press OK... and send user
					// to next window.
				} else
					JOptionPane.showMessageDialog(null,
							"passwords do not match");
				// }
			}
		});

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(x, y);
		setVisible(true);
		// setResizable(false);
	}
}
