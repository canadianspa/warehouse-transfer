import java.io.Serializable;

public class Warehouse implements Serializable{
	
	String name;
	int id;
	
	public Warehouse(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}

	@Override
	public String toString() {
		return name;
	}
	
   
	
	

}
