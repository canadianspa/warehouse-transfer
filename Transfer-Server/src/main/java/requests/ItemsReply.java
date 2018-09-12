package requests;

import entities.Item;

public class ItemsReply {
	
	public Item i;
	public int quantity;
	
	public ItemsReply(Item i, int quantity) {
		this.i = i;
		this.quantity = quantity;
	}

}
