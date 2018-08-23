
public class Item {
	
	String productTitle;
	String sku;


	public Item(String productTitle, String sku) {
		super();
		this.productTitle = productTitle;
		this.sku = sku;
	}


	@Override
	public String toString() {
		return productTitle + " " + sku;
	}
	
	
	

}
