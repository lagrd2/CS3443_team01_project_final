package CustMgtSys_1;

import java.util.ArrayList;

/**
 * This is a model object that represents a single customer.
 * It stores all information relating to a customer. Has getters 
 * and setters.
 * @author Oscar
 *
 */


public class CustomerModel {


    private String first;
    private String last;
    private String address;
    private String city;
    private String zip;
    private String email;
    private String phone;
    private String account;
    private double balance;
    private ArrayList<Integer> transactions; //each customer has an array list of Integers that correspond to indices of the actual TransactionModel
    										// ArrayList housed in Customer Controller
    

    public CustomerModel(String first, String last, String phone, String email,String address, String city, String zip,String Account){
        this.first = first;
        this.last = last;
        this.address = address;
        this.zip = zip;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.account = Account;
        transactions = new ArrayList<Integer>();
        balance = 0;
    }
  
    
    
    public String getFirst(){
        return first;
    }
    public String getLast(){
        return last;
    }
    public String getAddress(){
        return address;
    }
    public String getCity(){
        return city;
    }
    public String getZip(){
        return zip;
    }
    public String getPhone(){
        return phone;
    }
    public String getEmail(){
        return email;
    }
    public String getAccount(){
    	return account;
    }
    public double getBalance(){
    	return balance;
    }
    
    
    
    
    public void setFirst(String first){
            this.first = first;
    }
    public void setlast(String last){
            this.last = last;
    }
    public void setAddress(String address){
            this.address = address;
    }
    public void setCity(String city){
            this.city = city;
    }
    public void setZip(String zip){
            this.zip = zip;
    }
    public void setPhone(String phone){
            this.phone = phone;
    }
    public void setEmail(String email){
            this.email = email;
    }
    public void setBalance(double balance){
    	this.balance = balance;
    }
    public void setAccount(String account){
    	this.account = account;
    }
  
    public ArrayList<Integer> getTransactions(){
            return transactions;
    }
  
    public String toString(){
        return "AccountNumber: " + getAccount() + " "+ getFirst() + " " + getLast();
    }
}