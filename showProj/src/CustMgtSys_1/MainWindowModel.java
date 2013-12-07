package CustMgtSys_1;

import java.util.ArrayList;
	
	/**
	 * @author Oscar
	 * 
	 * This is the model for the MainWindow. Houses all the information needed.
	 * For simplicity, most information is kept inside CustomerController 
	 * also has methods that corelate to the view. ie which customer has been selected
	 * 
	 *
	 */

public class MainWindowModel {

        
        private CustomerController database;
        private ArrayList<CustomerModel> searchResults;
        private int selectedCustomer;
        
        
        public MainWindowModel(CustomerController database){
                this.database = database;
        }
     
        public ArrayList<CustomerModel> getSearchResults(){
        	return searchResults;
        }
        
        public void setResults(ArrayList<CustomerModel> results){
                this.searchResults = results;
        }
        
        public void setSelectedCustomer(int index){
                this.selectedCustomer = index;
        }
        public int getSelectedCustomer(){
                return selectedCustomer;
        }
        
        public CustomerController getDatabase(){
               return database;
        }        
}