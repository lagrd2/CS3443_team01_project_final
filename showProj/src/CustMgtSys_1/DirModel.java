package CustMgtSys_1;

import java.io.File;

/**
 * 
 * @author Robert
 *
 */
public class DirModel {
	
	private File transactions;
	private File products;
	private File customers;
	private File auth;
	private File folder;
	private File path;
	private File accounts;
	
	
	public DirModel(){
	}
	
	public File getFolder(){
		return folder;
	}
	public File getCustomersFile(){
		return customers;
	}
	public File getAuthFile(){
		return auth;
	}
	public File getProductsFile(){
		return products;
	}
	public File getTransactionsFile(){
		return transactions;
	}
	public File getPath(){
		return path;
	}
	public File getAccountsFile(){
		return accounts;
	}
	
	public void setfolder(File folder1){
		this.folder = folder1;
	}
	public void setcustomers(File customers1){
		this.customers = customers1;
	}
	public void setauth(File auth1){
		this.auth = auth1;
	}
	public void setproducts(File products){
		this.products = products;
	}
	public void settransactions(File transactions1){
		this.transactions = transactions1;
	}
	public void setpath(File path){
		this.path = path;
	}
	public void setAccountsFile(File accounts){
		this.accounts = accounts;
	}
	
}
