package CustMgtSys_1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 * This is thte controller for the MainWindowView, controlls all interacable fields and buttons
 * 
 * @author Oscar & Adrian
 *
 */


public class MainWindowController implements ActionListener,
		ListSelectionListener {

	private MainWindowView view;
	private MainWindowModel model;
	private MasterModel master;

	public MainWindowController(MainWindowView view, MainWindowModel model,
			MasterModel MM) {
		this.view = view;
		this.model = model;
		this.master = MM;

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == view.exitButton) {
			ench.saving(master);
			view.frame.dispose();
			System.exit(0);
			
			
		} else if (event.getSource() == view.logout) {
			ench.saving(master);
			view.frame.dispose();
			master.intro.rebirthIntro();
			
			
		} else if (event.getSource() == view.changePassword) {
			new ChangePassword(view.userName, master);
			
			
		} else if (event.getSource() == view.save) { 
			ench.saving(master);
			JOptionPane.showMessageDialog(view.save, "Successfully saved to "
					+ master.getFileModel().getFolder());
			
			
		} else if (event.getSource() == view.about){
			JOptionPane.showMessageDialog(view.about,
					"For an indepth how-to please refer to the MyCMS manual");
		}else if (event.getSource() == view.developers)
			JOptionPane
					.showMessageDialog(
							view.developers,
							"Developers include Oscar F, Robert L, Adrian M, Alex M, and John W");

		else if (event.getSource() == view.addUser) {
			new CreateLoginWindowMAIN(master);
			
			
		} else if (event.getSource() == view.deleteUser) {
			new DeleteUserWindow(master);
		}

		else if (event.getSource() == view.makePaymentButton) {
			if (model.getSelectedCustomer() == -1)
				NoCustomerSelected();
			else
				new MakePaymentWindow(model.getSelectedCustomer(), model, view);
			
		} else if (event.getSource() == view.addClientButton)
			new AddClientWindow(model, view, this);

		else if (event.getSource() == view.editClientButton) {
			int ndx = model.getSelectedCustomer();
			if (model.getSelectedCustomer() == -1)
				NoCustomerSelected();
			else {
				new EditClientWindow(model, view, this, ndx);
				view.updateStatus("Client ID: "
						+ model.getDatabase().getCustomers().get(ndx) + " "
						+ "has been edited");
			}

		} else if (event.getSource() == view.deleteClientButton) {
			int ndx = model.getSelectedCustomer();
			if (model.getSelectedCustomer() == -1)
				NoCustomerSelected();
			else {
				CustomerModel deleted = model.getDatabase().getCustomers()
						.get(ndx);
				if (JOptionPane.showConfirmDialog(view.frame,
						"Are you sure you want to delete client " + deleted
								+ "?") == 0) {
					model.getDatabase().deleteCustomer(ndx);
					view.updateStatus("You have deleted client " + deleted);
					view.updateTable(model.getDatabase().getCustomers());
					view.restoreListListener(this);
				}else
					view.updateStatus("You have canceled the deletion of "
							+ deleted);
				}
		} else if (event.getSource() == view.addTransactionButton) {
			
			int index = model.getSelectedCustomer();
			if (index == -1)
				NoCustomerSelected();
			else {
				new addTransactionWindow(index, model, view);
				view.updateTransactions(index);
			}
		}else if (event.getSource() == view.transactionfield
				|| event.getSource() == view.searchTranButton) {
			if (view.transactionfield.getText().equals("")) {
				view.updateTable(model.getDatabase().getCustomers());
				view.restoreListListener(this);
				view.updateTransactions(-1);
			} else {
				ArrayList<CustomerModel> results = new ArrayList<CustomerModel>();

				int TransactionIndex = model.getDatabase().searchTransaction(
						view.transactionfield.getText());
				int customerIndex = -1;

				if (TransactionIndex >= 0) {
					String customerAccount = model.getDatabase()
							.getTransactions().get(TransactionIndex)
							.getAccountNumber();
					customerIndex = model.getDatabase().searchCustomer(
							customerAccount);

					CustomerModel customer = model.getDatabase().getCustomers()
							.get(customerIndex);
					results.add(customer);

				}
				view.updateTable(results);
				view.restoreListListener(this);
				view.updateTransactions(customerIndex);
				if(results.size() > 0)	
					view.updateStatus("Transaction found");
				else
					view.updateStatus("No such Transaction Found");
			}
			view.transactionfield.setText("");
			
		} else if (event.getSource() == view.AccountField) {
			if (view.AccountField.getText().equals("")) {
				view.updateTable(model.getDatabase().getCustomers());
				view.restoreListListener(this);
				view.updateTransactions(-1);
			}else{
				String text = view.AccountField.getText();
				ArrayList<CustomerModel> results = new ArrayList<CustomerModel>();
				int index = model.getDatabase().searchCustomer(text);

				if (index >= 0)
					results.add(model.getDatabase().getCustomers().get(index));

				model.setResults(results);
				view.AccountField.setText("");
				view.updateTable(model.getSearchResults());
				view.restoreListListener(this);
				if (index >= 0)
					view.updateTransactions(index);
			}
		}

		else {
			String text;

			text = view.FirstField.getText();
			model.setResults(Search.searchByFirst(text, model.getDatabase()
					.getCustomers()));
			view.FirstField.setText("");

			text = view.LastField.getText();
			model.setResults(Search.searchByLast(text, model.getSearchResults()));
			view.LastField.setText("");

			text = (String) view.PhoneField.getValue();
			model.setResults(Search.searchByPhone(text,
					model.getSearchResults()));
			view.PhoneField.setValue("");

			text = view.AddressField.getText();
			model.setResults(Search.searchByAddress(text,
					model.getSearchResults()));
			view.AddressField.setText("");

			text = view.ZipField.getText();
			model.setResults(Search.searchByZip(text, model.getSearchResults()));
			view.ZipField.setText("");

			text = view.EmailField.getText();
			model.setResults(Search.searchByEmail(text,
					model.getSearchResults()));
			view.EmailField.setText("");

			view.updateStatus("Found " + model.getSearchResults().size()
					+ " customers");
			
			view.updateTable(model.getSearchResults());
			view.restoreListListener(this); 
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		int index = view.table.getSelectedRow();
		if( index >= 0 && index < model.getDatabase().getCustomers().size()) {
			String account = (String) view.table.getValueAt(index,0);
			int ndx = model.getDatabase().searchCustomer(account);
		
			String updateMessage = "You have selected customer: "
					+ model.getDatabase().getCustomers().get(ndx);
			
			model.setSelectedCustomer(ndx);
			view.updateStatus(updateMessage);
			view.updateTransactions(ndx);
		}
	}

	public void NoCustomerSelected() {
		JOptionPane.showMessageDialog(view.frame, "No customer selected");

	}

}