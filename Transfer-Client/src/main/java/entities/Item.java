package entities;
public class Item {
	
	public String productTitle;
	public String sku;
	public Long id;


	public Item(String productTitle, String sku, Long id) {
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
