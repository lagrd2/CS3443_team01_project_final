package CustMgtSys_1;

/**
 * 
 * @author Oscar
 * 
 * This houses all information regaurding transactions. A transaction in this program is defined by this 
 * class. Has getters and setters.
 *
 */

public class TransactionsModel {
        
        private String date;
        private String SKU;
        private String item;
        private String price;
        private String transactionNumber;
        private String paymentMethod;
        private String accountNumber;
        
        public TransactionsModel(String transactionNumber, String date, String SKU, String item, String price, String paymentMethod, String accountNumber){
                this.date = date;
                this.SKU = SKU;
                this. item = item;
                this.price = price;
                this.transactionNumber = transactionNumber;
                this.paymentMethod = paymentMethod;
                this.accountNumber = accountNumber;
        }
        
        
        public String getAccountNumber(){
        	return accountNumber;
        }
        public String getDate(){
                return date;
        }
        public String getSKU(){
                return SKU;
        }
        public String getItem(){
                return item;
        }
        public String getPrice(){
                return price;
        }
        public String getTransactionNumber(){
        	return transactionNumber;
        }
        public String getPaymentMethod(){
                return paymentMethod;
        }
       
        
        
        
        public void setDate(String date){
                this.date = date;
        }
        public void setSKU(String SKU){
                this.SKU = SKU;
        }
        public void setItem(String item){
                this.item = item;
        }
        public void setPrice(String price){
                this.price = price;
        }
        public void setTransactionNUmber(String transactionNumber){
        	this.transactionNumber = transactionNumber;
        }
        public void setPaymentMethod(String paymentMethod){
                this.paymentMethod = paymentMethod;
        }
        public void setAccountNumber(String accountNumber){
        	this.accountNumber = accountNumber;
        }
        
        public String toString(){
        	return date + " " + SKU + " " + price + " " + accountNumber;
        }
        
}