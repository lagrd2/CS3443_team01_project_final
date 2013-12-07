package CustMgtSys_1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class provides static methods that are called, so that a search can be performed.
 * 
 * @author Oscar
 *
 */

public class Search {
  
	 
	public static int searchByAccount(String account, ArrayList<CustomerModel> customers){
		
		if(customers.size() == 0)
			return -1;
		
		CustomerModel key = new CustomerModel(null,null,null,null,null,null,null,account);
		return(Collections.binarySearch(customers,key,CustomComparator.AccountComparator));
	   	} 
	
	
  
    public static ArrayList<CustomerModel> searchByFirst(String first, ArrayList<CustomerModel> customers){
    	ArrayList<CustomerModel> matches = new ArrayList<CustomerModel>();
    	
    	if( customers.size() == 0)
    		return matches;
    	
            if(first.equals(""))
                    return customers;
            
            
         for(CustomerModel current : customers){
            if(current.getFirst().equals(first)){
            matches.add(current);
            }
         }
         return matches;
    }
      
    
    public static ArrayList<CustomerModel> searchByLast(String last, ArrayList<CustomerModel> customers){
    	ArrayList<CustomerModel> matches = new ArrayList<CustomerModel>();
    	
    	if(customers.size() == 0 )
    		return matches;
    	
    	if(last.equals(""))
                return customers;
                
            
            
        for(CustomerModel current : customers){
           if(current.getLast().equals(last)){
              matches.add(current);
           }
        }
            return matches;
    }
    
    
    public static ArrayList<CustomerModel> searchByPhone(String phone, ArrayList<CustomerModel> customers){
    	ArrayList<CustomerModel> matches = new ArrayList<CustomerModel>();
    	
    	if( customers.size() == 0 )   
    		return matches; 
    	   
    	   if(phone.equals(""))
                    return customers;
            
            
        for(CustomerModel current : customers){
           if(current.getPhone().equals(phone)){
              matches.add(current);
           }
        }
            return matches;
    }
    
    
    
    
    public static ArrayList<CustomerModel> searchByAddress(String address, ArrayList<CustomerModel> customers){
    	ArrayList<CustomerModel> matches = new ArrayList<CustomerModel>();
    	
    	if(customers.size() == 0 )
    		return matches;
    	
    	
    	
    	if(address.equals(""))
                return customers;
            
            
        for(CustomerModel current : customers){
           if(current.getAddress().equals(address)){
              matches.add(current);
           }
        }
            return matches;
    }
    
    
    public static ArrayList<CustomerModel> searchByZip(String zip, ArrayList<CustomerModel> customers){
       
        ArrayList<CustomerModel> matches = new ArrayList<CustomerModel>();
    	
        if(customers.size() == 0 )
    		return matches;
    	
    	
    	if(zip.equals(""))
                return customers;
        
        for(CustomerModel current : customers){
           if(current.getZip().equals(zip)){
           matches.add(current);
           }
        }
        return matches;
   }
    
    
    
    
    
    public static ArrayList<CustomerModel> searchByEmail(String email, ArrayList<CustomerModel> customers){
    	
    	
    	ArrayList<CustomerModel> matches = new ArrayList<CustomerModel>();
    	
    	if(customers.size() == 0 )
    		return matches;
    	
    	if(email.equals(""))
                return customers;
    	
    	
        for(CustomerModel current : customers){
           if(current.getEmail().equals(email)){
           matches.add(current);
           }
        }
        return matches;
   }
    
    
    
    public static ArrayList<CustomerModel> searchByCity(String city, ArrayList<CustomerModel> customers){
        
    	ArrayList<CustomerModel> matches = new ArrayList<CustomerModel>();
    	
    	if(customers.size() == 0 )
    		return matches;
    	
    	if(city.equals(""))
                return customers;
  
        for(CustomerModel current : customers){
           if(current.getCity().equals(city)){
           matches.add(current);
           }
        }
        return matches;
   }
    
    
    
      
}