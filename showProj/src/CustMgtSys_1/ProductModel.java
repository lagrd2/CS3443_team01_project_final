package CustMgtSys_1;

public class ProductModel {

	private String SKU;
	private String item;
	private String price;
	
	
	public ProductModel(String SKU, String item, String price){
		this. SKU = SKU;
		this.item = item;
		this.price = price;
	}
	
	public String getSKU(){
		return SKU;
	}
	public String getPrice(){
		return price;
	}
	public String getItem(){
		return item;
	}
	
}
