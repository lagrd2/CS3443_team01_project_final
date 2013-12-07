package CustMgtSys_1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import com.sun.media.sound.ModelAbstractChannelMixer;

/**
 * 
 * @author Adrian & Oscar
 * 
 * 
 *         "This is where ALL the magic happens ;) " This entire window is built
 *         on a BorderLayout The purpose of this window is to display the MAIN
 *         PROGRAM WINDOW upon correct user login access or new login access
 *         this window will appear. The CENTER Panel displays all customers and
 *         a transaction list of each customer in a splitPane of 2 JTables The
 *         NORTH Panel displays the list of buttons the user can implement The
 *         WEST Panel displays the Search field with different searching options
 *         for both clients and transactions The South Panel displays a status
 *         bar that displays any action the user implements throughout the use
 *         of the program.
 * 
 * 
 */

public class MainWindowView {

	public JFrame frame;

	public JMenu fileMenu, AdminMenu, help, reports;

	public JMenuItem exitButton, logout, save, about, developers, addUser,
			deleteUser, changePassword;

	public JTextField AccountField, FirstField, LastField, AddressField,
			ZipField, EmailField, transactionfield;
	JFormattedTextField PhoneField;

	public JTable table, tranTable;

	private JPanel titlePanel;

	private JPanel buttonsPanel, searchClientPanel;

	public JButton addClientButton, editClientButton, searchClientButton,
			deleteClientButton, makePaymentButton, viewTransactionsButton,
			ClientInfoButton, addTransactionButton, optionsButton,
			searchTranButton;

	private JLabel statusLabel;

	private CustomTableModel tablemodel, tranTableModel;
	private JScrollPane tableScroll, tranScroll;
	private JSplitPane splitPane;

	private boolean flag;

	private MainWindowModel mainwindowmodel;
	public String userName;

	Color normal = new Color(128, 0, 0);

	@SuppressWarnings("deprecation")
	public MainWindowView(String user, MainWindowModel model, final MasterModel master, boolean flag) {

		this.userName = user;
		this.mainwindowmodel = model;
		this.flag = flag;

		frame = new JFrame("MyCMS");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setSize(1300, 700);

		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	int choice = JOptionPane.showConfirmDialog(frame, 
			            "Would you like to save before exiting?", "MyCMS exit", 
			            JOptionPane.YES_NO_OPTION,
			            JOptionPane.QUESTION_MESSAGE);
		        if (choice == JOptionPane.YES_OPTION){
		        	ench.saving(master);
		            System.exit(0);
		        }
		        else if (choice == JOptionPane.NO_OPTION){
		        	System.exit(0);
		        }
		    }
		});
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);

		// ********************* MENUBAR
		// ********************************************************************************
		Font font = new Font("SansSerif", Font.BOLD, 20);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		changePassword = new JMenuItem("Change Password");
		fileMenu.add(changePassword);

		save = new JMenuItem("Save");
		fileMenu.add(save);

		logout = new JMenuItem("Logout");
		fileMenu.add(logout);

		exitButton = new JMenuItem("Save & Exit");
		fileMenu.add(exitButton);

		if (flag) {
			AdminMenu = new JMenu("Admin");
			menuBar.add(AdminMenu);
			addUser = new JMenuItem("Add User Account");
			AdminMenu.add(addUser);
			deleteUser = new JMenuItem("Delete User Account");
			AdminMenu.add(deleteUser);
		}

		help = new JMenu("Help");
		menuBar.add(help);
		about = new JMenuItem("About MyCMS");
		help.add(about);
		developers = new JMenuItem("Developers");
		help.add(developers);

		// ****************************** NORTH
		// ************************************************************************************
		// this is the buttons panel used to store add client button

		font = new Font("SansSerif", Font.BOLD, 12);

		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1, 5));
		if (flag)
			buttonsPanel.setBackground(normal);
		else
			buttonsPanel.setBackground(Color.GRAY);

		JLabel filler22 = new JLabel();
		buttonsPanel.add(filler22);

		Icon paymentIcon = new ImageIcon("payment.png");
		makePaymentButton = new JButton("Make Payment", paymentIcon);
		makePaymentButton.setFont(font);
		buttonsPanel.add(makePaymentButton);

		Icon addTran = new ImageIcon("addtransaction.png");
		addTransactionButton = new JButton("Add Transaction", addTran);
		addTransactionButton.setFont(font);
		buttonsPanel.add(addTransactionButton);

		Icon addUser = new ImageIcon("adduser.png");
		addClientButton = new JButton("Add Client", addUser);
		addClientButton.setFont(font);
		buttonsPanel.add(addClientButton);

		Icon edituser = new ImageIcon("edituser.png");
		editClientButton = new JButton("Edit Client", edituser);
		editClientButton.setFont(font);
		buttonsPanel.add(editClientButton);

		if (flag) {
			Icon deleteuser = new ImageIcon("deleteuser.png");
			deleteClientButton = new JButton("Delete Client", deleteuser);
			deleteClientButton.setFont(font);
			buttonsPanel.add(deleteClientButton);
		}

		frame.add(buttonsPanel, BorderLayout.NORTH);

		// ********************************** EAST
		// **************************************************************************************

		// Search Client Panel
		font = new Font("Verdana", Font.BOLD, 13);
		searchClientPanel = new JPanel();
		searchClientPanel.setSize(20, 50);
		searchClientPanel.setLayout(new GridLayout(12, 2));
		searchClientPanel.setPreferredSize(new Dimension(260, 600));
		searchClientPanel.setMaximumSize(new Dimension(260, 600));
		searchClientPanel.setBackground(Color.GRAY);

		String[] labels = { "Account Number:", "First Name:", "Last Name:",
				"Phone Number:", "Address:", "Zip Code:", "Email:" };

		// MaskFormatter for phone number field
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter("(###) ###-####");
			mask.setPlaceholderCharacter('x');
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int numLabels = labels.length;
		for (int i = 0; i < numLabels; i++) {

			JLabel label3 = new JLabel(labels[i]);
			label3.setSize(7, 2);
			searchClientPanel.add(label3);
			label3.setFont(font);

			switch (i) {
			case 0:
				AccountField = new JTextField(5);
				AccountField.setFont(font);
				searchClientPanel.add(AccountField);
				break;
			case 1:
				FirstField = new JTextField(5);
				FirstField.setFont(font);
				FirstField.setBackground(Color.WHITE);
				label3.setLabelFor(FirstField);
				searchClientPanel.add(FirstField);
				break;
			case 2:
				LastField = new JTextField(5);
				LastField.setFont(font);
				LastField.setBackground(Color.WHITE);
				label3.setLabelFor(LastField);
				searchClientPanel.add(LastField);
				break;
			case 3:
				PhoneField = new JFormattedTextField(mask);
				PhoneField.setFont(font);
				PhoneField.setValue("");
				PhoneField.setBackground(Color.WHITE);
				label3.setLabelFor(PhoneField);
				searchClientPanel.add(PhoneField);
				break;
			case 4:
				AddressField = new JTextField(5);
				AddressField.setFont(font);
				AddressField.setBackground(Color.WHITE);
				label3.setLabelFor(AddressField);
				searchClientPanel.add(AddressField);
				break;
			case 5:
				ZipField = new JTextField(5);
				ZipField.setFont(font);
				ZipField.setBackground(Color.WHITE);
				label3.setLabelFor(ZipField);
				searchClientPanel.add(ZipField);
				break;
			case 6:
				EmailField = new JTextField(5);
				EmailField.setFont(font);
				EmailField.setBackground(Color.WHITE);
				label3.setLabelFor(EmailField);
				searchClientPanel.add(EmailField);
				break;
			}
		}
		// ***************************************** WEST
		// ***********************************************************************************/
		// fillers
		JLabel filler1 = new JLabel();
		searchClientPanel.add(filler1);
		JLabel filler2 = new JLabel();
		JLabel filler3 = new JLabel();
		JLabel filler4 = new JLabel();

		// Search Client Button
		Icon search = new ImageIcon("search.png");
		searchClientButton = new JButton("Search", search);
		searchClientButton.setFont(font);
		searchClientPanel.add(searchClientButton);
		searchClientPanel.add(filler2);
		searchClientPanel.add(filler4);

		JLabel transactionLabel = new JLabel("Transaction:");
		transactionLabel.setFont(font);
		searchClientPanel.add(transactionLabel);
		transactionfield = new JTextField(5);
		transactionfield.setFont(font);
		searchClientPanel.add(transactionfield);
		searchClientPanel.add(filler3);

		searchTranButton = new JButton("Search", search);
		searchTranButton.setFont(font);
		searchClientPanel.add(searchTranButton);
		JLabel filler5 = new JLabel();
		searchClientPanel.add(filler5);

		// Image on top left
		JPanel imagePanel = new JPanel();
		ImageIcon icon = new ImageIcon("MyCMSLogo.JPG");
		JLabel IconLabel = new JLabel();
		IconLabel.setIcon(icon);
		imagePanel.add(IconLabel);

		// create SplitPane with the image and search client panel on it
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				imagePanel, searchClientPanel);
		split.disable();

		frame.add(split, BorderLayout.BEFORE_LINE_BEGINS);

		// ********************************** CENTER
		// **********************************************************************************************

		table = new JTable();

		// CustomTableModel: turns cell editing off
		tablemodel = new CustomTableModel(new Object[] {"Account Number", "First Name",
				"Last Name", "Phone", "Address", "City", "Zip", "Email", "Balance" }, 0);

		table.setModel(tablemodel);

		// loops through database ArrayList and makes strings out of the
		// Customer information so that it can be passed to the addRow method
		for (Object o : mainwindowmodel.getDatabase().getCustomers())
			tablemodel.addRow(new String[] { 
					((CustomerModel) o).getAccount(),
					((CustomerModel) o).getFirst(),
					((CustomerModel) o).getLast(),
					((CustomerModel) o).getPhone(),
					((CustomerModel) o).getAddress(),
					((CustomerModel) o).getCity(),
					((CustomerModel) o).getZip(),
					((CustomerModel) o).getEmail(),
					(Double.toString(((CustomerModel) o).getBalance()))
			});

		/** end of code to add ArrayList to JTabble **/

		// these three statements allow for row selection
		table.setEnabled(true);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);

		// set background color.. ect
		if (flag)
			table.setGridColor(normal);
		else
			table.setGridColor(Color.CYAN);

		table.setBackground(Color.LIGHT_GRAY);
		table.setAutoCreateRowSorter(true);

		// creates JScrollPane with JTable inside it
		tableScroll = new JScrollPane(table);
		Dimension tablePreferred = tableScroll.getPreferredSize();
		tableScroll.setPreferredSize(new Dimension(tablePreferred.width,
				tablePreferred.height / 3));

		// this segment creates the JTable for the transactions part on the
		// lower end of CENTER
		tranTable = new JTable();
		tranTableModel = new CustomTableModel(new String[] {
				"Transaction Number", "AccountNumber", "Date", "SKU", "Item", "Price",
				"Payment Method" }, 0);
		tranTable.setModel(tranTableModel);

		// sets row selection
		tranTable.setEnabled(true);
		tranTable.setRowSelectionAllowed(true);
		tranTable.setColumnSelectionAllowed(false);

		// COLOR
		tranTable.setGridColor(Color.CYAN);
		tranTable.setBackground(Color.LIGHT_GRAY);

		tranScroll = new JScrollPane(tranTable);
		Dimension tranTablePreferred = tranScroll.getPreferredSize();
		tranScroll.setPreferredSize(new Dimension(tranTablePreferred.width,
				tranTablePreferred.height / 3));

		// this splits the two tables into one panel. should be changed to only
		// split if a client has been selected.
		// to show the transactions of that specific client.
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tableScroll,
				tranScroll);
		splitPane.setDividerLocation(300); // ????

		// frame.add(splitPane, BorderLayout.CENTER);
		frame.add(splitPane, BorderLayout.CENTER);

		// **************************** SOUTH:
		// **********************************************************************************
		// this is just the header label of the window
		// meant to display the database logged in to
		// and the user who is logged in
		titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout(2, 1));
		titlePanel.setBackground(Color.YELLOW);

		JLabel userLabel = new JLabel("Welcome to MyCMS " + user); // add //
																	// string
		titlePanel.add(userLabel);

		statusLabel = new JLabel("Database load successful");
		titlePanel.add(statusLabel);

		frame.add(titlePanel, BorderLayout.SOUTH);
		frame.setVisible(true);

		model.setSelectedCustomer(-1); // sets selected Customer to no selected
										// customer

	}

	// ***************************** END OF CONSTRUCTOR
	// *******************************************************

	/**
	 * Updates the status bar located at the SOUTH of the frame
	 * 
	 * @param status
	 */
	public void updateStatus(String status) {
		statusLabel.setText(status);
	}

	/**
	 * Updates the CustomerTable with the specified ArrayList of customers
	 * 
	 * @param results
	 */
	public void updateTable(ArrayList<CustomerModel> results) {

		frame.remove(splitPane);
		table = new JTable();

		tablemodel = new CustomTableModel(new Object[] {"Account Number", "First Name",
				"Last Name", "Phone", "Address", "City", "Zip", "Email","Balance" }, 0);

		table.setModel(tablemodel);

		for (Object o : results)
			tablemodel.addRow(new String[] { 
					((CustomerModel) o).getAccount(),
					((CustomerModel) o).getFirst(),
					((CustomerModel) o).getLast(),
					((CustomerModel) o).getPhone(),
					((CustomerModel) o).getAddress(),
					((CustomerModel) o).getCity(),
					((CustomerModel) o).getZip(),
					((CustomerModel) o).getEmail(),
					(Double.toString(((CustomerModel) o).getBalance())) });

		table.setEnabled(true);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);

		if (flag)
			table.setGridColor(normal);
		else
			table.setGridColor(Color.CYAN);
		table.setBackground(Color.LIGHT_GRAY);
		table.setAutoCreateRowSorter(true);

		tableScroll = new JScrollPane(table);
		Dimension tablePreferred = tableScroll.getPreferredSize();
		tableScroll.setPreferredSize(new Dimension(tablePreferred.width,
				tablePreferred.height / 3));

		tranTable = new JTable();
		tranTableModel = new CustomTableModel(new Object[] {
				"Transaction Number", "AccountNumber", "Date", "SKU", "Item", "Price",
				"Payment Method" }, 0);
		tranTable.setModel(tranTableModel);

		tranScroll = new JScrollPane(tranTable);
		Dimension tranTablePreferred = tranScroll.getPreferredSize();
		tranScroll.setPreferredSize(new Dimension(tranTablePreferred.width,
				tranTablePreferred.height / 3));

		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tableScroll,
				tranScroll);
		splitPane.setDividerLocation(300);

		mainwindowmodel.setSelectedCustomer(-1);
		frame.add(splitPane);
		frame.setVisible(true);
	}

	/**
	 * Updates the transactions table with the customer ndx transactions.
	 * 
	 * @param ndx
	 */
	public void updateTransactions(int ndx) {
		tranTableModel.setRowCount(0);

		if (ndx >= 0) {
			ArrayList<Integer> results = mainwindowmodel.getDatabase()
					.getCustomers().get(ndx).getTransactions();

			for (Integer result : results) {
				Object o = mainwindowmodel.getDatabase().getTransactions()
						.get(result);
				tranTableModel.addRow(new String[] {
						((TransactionsModel) o).getTransactionNumber(),
						((TransactionsModel) o).getAccountNumber(),
						((TransactionsModel) o).getDate(),
						((TransactionsModel) o).getSKU(),
						((TransactionsModel) o).getItem(),
						((TransactionsModel) o).getPrice(),
						((TransactionsModel) o).getPaymentMethod() });
			}
			if (flag)
				tranTable.setGridColor(normal);
			else
				tranTable.setGridColor(Color.CYAN);
			tranTable.setBackground(Color.LIGHT_GRAY);
		}
	}

	/**
	 * Restores the ListListener to the customer tableModel This should be
	 * called every time the customer table is updated
	 * 
	 * @param listen
	 */
	public void restoreListListener(MainWindowController listen) {
		table.getSelectionModel().addListSelectionListener(listen);
	}

	/**
	 * Registers all intractable components with the specified class listener
	 * This class must implement ActionListener and ListSelectionListener
	 * 
	 * @param listen
	 */
	public void registerControllers(MainWindowController listen) {

		// registers for JMenu items
		exitButton.addActionListener(listen);
		save.addActionListener(listen);
		logout.addActionListener(listen);
		changePassword.addActionListener(listen);
		developers.addActionListener(listen);
		about.addActionListener(listen);
		if (flag) {
			addUser.addActionListener(listen);
			deleteUser.addActionListener(listen);
		}

		// registers all search JTextFields
		AccountField.addActionListener(listen);
		FirstField.addActionListener(listen);
		LastField.addActionListener(listen);
		PhoneField.addActionListener(listen);
		AddressField.addActionListener(listen);
		ZipField.addActionListener(listen);
		EmailField.addActionListener(listen);

		searchTranButton.addActionListener(listen);
		transactionfield.addActionListener(listen);

		// listener for row currently selected
		table.getSelectionModel().addListSelectionListener(listen);

		// listener for all buttons
		addClientButton.addActionListener(listen);
		editClientButton.addActionListener(listen);
		makePaymentButton.addActionListener(listen);
		searchClientButton.addActionListener(listen);
		if (flag)
			deleteClientButton.addActionListener(listen);

		addTransactionButton.addActionListener(listen);
	}

}