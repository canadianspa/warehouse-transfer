
public class Item {
	
	String productTitle;
	String sku;
	String id;


	public Item(String productTitle, String sku, String id) {
		super();
		this.productTitle = productTitle;
		this.sku = sku;
		this.id = id;
	}


	@Override
	public String toString() {
		return productTitle + " " + sku;
	}
	
	
	

}
