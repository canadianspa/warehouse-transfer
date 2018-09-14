package entities;
public class Items{
	
	public Item i;
	public int quantity;
	
	public Items(Item i, int quantity) {
		super();
		this.i = i;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return i.toString() + " " + quantity;
	}
	
	
	
	
	
	

}
