package CustMgtSys_1;

import java.util.Comparator;


/**
 * This class declares two custom comparators for CustomerModel and TransactionsModel in order
 *  to have binary searching 
 * @author ofalcon
 *
 */
public class CustomComparator {

	 public static Comparator<CustomerModel> AccountComparator = new Comparator<CustomerModel>(){
	        public int compare(CustomerModel a1, CustomerModel a2){
	           return a1.getAccount().compareTo(a2.getAccount());
	        }
	     };
	
	     
	     public static Comparator<TransactionsModel> TransactionComparator = new Comparator<TransactionsModel>(){
		        public int compare(TransactionsModel t1, TransactionsModel t2){
		           return t1.getTransactionNumber().compareTo(t2.getTransactionNumber());
		        }
		     };
	     
}
