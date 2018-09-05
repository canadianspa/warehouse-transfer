import java.io.Serializable;

public class Items implements Serializable{
	
	Item i;
	int quantity;
	
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
