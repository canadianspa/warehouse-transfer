package entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Item {
	
	public String productTitle;
	public String sku;
	@Id
	public Long id;


	public Item(String productTitle, String sku, Long id) {
		super();
		this.productTitle = productTitle;
		this.sku = sku;
		this.id = id;
	}
	

	public Item() {
	}

	@Override
	public String toString() {
		return productTitle + " " + sku;
	}
	
	
	

}
