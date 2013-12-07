package CustMgtSys_1;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This houses customer and transaction data. Has built in methods for 
 * adding, deleting, editing, and searching for criteria 
 * @author Oscar
 *
 */


public class CustomerController {
 
    private ArrayList<CustomerModel> customers; //Customer Data
    private ArrayList<TransactionsModel> transactions; //Transaction Data
    
    private Integer maxAccountNum; //used to generate next valid Account Number
    private Integer maxTransactionNum; //used to generate next valid transaction number 
  
    
    /**
     * Opens and loads the programs, storing all information from 
     * selected file
     * @param file
     * @throws IOException
     */
    
    public CustomerController(MasterModel master) throws IOException{
    	ArrayList<String>  accounts = new ArrayList<String>();
    	
        
    	customers = ench.opener(master); //initialize customers array
        
        accounts = ench.AccountOpener(master); //extract account number from seprate file
        if( accounts.size() > 0) 
        	maxAccountNum = Integer.parseInt( accounts.get(accounts.size()-1) ); //set maximun account number out of account file
        else
        	maxAccountNum = 0; //if the file is empty, set max account to 0
        
        
        int i = 0;
        for(String account : accounts){
        	customers.get(i++).setAccount(account); //set accounts for every customer 
        	if( i == customers.size() )
        		break;
        }

        
        transactions = ench.TransOpener(master); //initialize transactions array 
        if( transactions.size() > 0)
        	maxTransactionNum = Integer.parseInt(transactions.get(transactions.size()-1).getAccountNumber());
        else 
        	maxTransactionNum = 0;
      
        //for each transaction in array, assign it to the appropriate customer 
        i = 0;
        for(TransactionsModel transaction : transactions ){ 
        	CustomerModel key = new CustomerModel(null,null,null,null,null,null,null,transaction.getAccountNumber());
        	int CustomerIndex = Collections.binarySearch(customers,key,CustomComparator.AccountComparator);
        	if(CustomerIndex >= 0){
        		customers.get(CustomerIndex).getTransactions().add(i);
        		if(transaction.getPaymentMethod().equals("Credit")){
        			double balance = customers.get(CustomerIndex).getBalance();
        			customers.get(CustomerIndex).setBalance(  balance - Double.parseDouble(transaction.getPrice()));
        		}
        	}
        	i++;
        }
        Collections.sort(customers,CustomComparator.AccountComparator);
        master.setCustomerController(this); //set this database to the master method
    }
   
    /** Adds a new Customer with specified information to the database
     *  RETURNS: The assigned Account Number for the new Customer**/
    public int addCustomer(String first, String last, String phone, String email,String address, String city, String zip){
        maxAccountNum += 537; 
        CustomerModel newcustomer = new CustomerModel(first,last,phone,email,address,city,zip,maxAccountNum.toString());
        customers.add(newcustomer);
        return maxAccountNum;  
    }
    
    /**
     * Edits customer of the Database.
     * @param ndx
     * @param first
     * @param last
     * @param phone
     * @param email
     * @param address
     * @param city
     * @param zip
     */
    public void editCustomer(int ndx,String first, String last, String phone, String email,String address, String city, String zip){
        if( ndx < 0 || ndx >= customers.size()){
        	System.out.println("Error: Customer Index out of range");
        	return;
        }
    	CustomerModel customer = customers.get(ndx);
        customer.setFirst(first);
        customer.setlast(last);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setCity(city);
        customer.setZip(zip);
    }
    
    /**
     * Removes customer ndx from the Database and removes all his associated transactions 
     * @param ndx - The index of the customer to be deleted 
     */
    public void deleteCustomer(int ndx){
        
    	if(ndx < 0 || ndx >= customers.size() ){
    		System.out.println("Error: Customer Index out of range " + ndx);
    		return;
    	}
    	
    	//delete all of the customers transactions, adjust every other customers transaction index
    	for(int TransactionIndex : customers.get(ndx).getTransactions()){
        	for(CustomerModel customer : customers){
        		int i = 0;
        		for(Integer Index : customer.getTransactions()){
        			if( Index > TransactionIndex){
        				customer.getTransactions().set(i, Index -1);
        			}
        			i++;
        		}
        			
        	}
    		transactions.remove(TransactionIndex);
    	}
    	//delete customer 
    	customers.remove(ndx);
    }
    
    /**
     * Adds a new transaction with specified information to customer ndx of the Database
     * If null is specified for transactionNumber, a new and unique transactionNumber will be assigned to the transaction 
     * NOTE: If you pass in a non null transactionNumber, it MUST be guaranteed to be unique in the database. Otherwise, undetermined 
     * behavior occurs. 
     * 
     * @param ndx
     * @param transactionNumber
     * @param Date
     * @param SKU
     * @param item
     * @param price
     * @param paymentMethod
     * @param accountNumber
     */
    public void addTransaction(int ndx,String transactionNumber, String Date, String SKU, String item, String price, String paymentMethod,String accountNumber){
		
    	if(ndx < 0 || ndx >= customers.size() ){
    		System.out.println("Error: Customer Index out of range");
    		return;
    	}
    	
		TransactionsModel trans;
		CustomerModel customer = customers.get(ndx);
 
		if(transactionNumber == null){
        	maxTransactionNum++;
        	trans = new TransactionsModel(maxTransactionNum.toString(),Date,SKU,item,price,paymentMethod,accountNumber);
        }else
        	trans = new TransactionsModel(transactionNumber,Date,SKU,item,price,paymentMethod,accountNumber);
        
        if(paymentMethod.equals("Credit")){
        	double  balance = customer.getBalance();
        	balance = balance - Double.parseDouble(price);
        	customer.setBalance(balance);
        }
        transactions.add(trans);
        customer.getTransactions().add(new Integer(transactions.size()-1) );
       }
    
    /**
     * searches the Database for the transaction containing the specified transactionNumber
 
     * @param transaction
     * @return	Returns the index of the transaction, or < 0 if not found
     */
    public int searchTransaction(String transaction){
	   	TransactionsModel key = new TransactionsModel(transaction,null,null,null,null,null,null);
	   return(Collections.binarySearch(transactions,key,CustomComparator.TransactionComparator));
   	} 
   
    /**
     * Searches the database for the customer with account number accountNumber
    
     * @param accountNumber
     * @return Returns: the index of the customer, < 0 if not found 
     */
   	public int searchCustomer(String accountNumber){
   		return Search.searchByAccount(accountNumber, customers);
   	}
    
    /**
     * 
     * @return The customer ArrayList for this database
     */
    public ArrayList<CustomerModel> getCustomers(){
            return customers;
    }
    
    /**
     * @return The transaction ArrayList for this database
     */
    public ArrayList<TransactionsModel> getTransactions(){
    	return transactions;
    }
    
    /**
     * Prints to console all the customers in the database
     */
    public void printCustomers(){
    	for (CustomerModel customer : customers) 
    		System.out.println(customer);
	
    }
    	
    
   
}