package CustMgtSys_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProductController {

	private ArrayList<ProductModel> products;

	
	static class ProductComparator implements Comparator<ProductModel>
	{            
			@Override
			public int compare(ProductModel o1, ProductModel o2) {
				return o1.getSKU().compareTo(o2.getSKU());
			}
	     }
	
	
	
	public ProductController(){
		products = new ArrayList<ProductModel>();
		Collections.sort(products, new ProductComparator());
	}
	
	public int addProduct(String SKU, String item, String price){
		for(ProductModel product : products){
			if(product.getSKU().equals(SKU)){
				return -1;
			}
		}
		products.add(new ProductModel(SKU,item,price));
		return 0;	
	}
	
	
	
}
