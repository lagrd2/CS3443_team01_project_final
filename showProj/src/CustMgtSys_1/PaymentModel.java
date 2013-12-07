package CustMgtSys_1;

public class PaymentModel {

	private double amount;
	private String date;
	private String accountNumber;
	
	
	public PaymentModel(double amount, String date, String accountNumber){
		this.amount = amount;
		this.date = date;
		this.accountNumber = accountNumber;
	}
	
	public double getAmount(){
		return amount;
	}
	public String getDate(){
		return date;
	}
	public String getAccountNumber(){
		return accountNumber;
	}
	
	
}
