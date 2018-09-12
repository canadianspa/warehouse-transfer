package entities;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity 
public class Items{
	
	@Id
	public Long id; 
	public Key<Item> i;
	public int quantity;
	
	public Items(Key<Item> i, int quantity) {
		super();
		this.i = i;
		this.quantity = quantity;
	}
	
	

	public Items() {
	}

	public Item getItem()
	{
		return ObjectifyService.ofy().load().key(i).now();
	}

	@Override
	public String toString() {
		
		Item item = ObjectifyService.ofy().load().key(i).now();
		return item.toString() + " " + quantity;
	}
	
	
	
	
	
	

}
