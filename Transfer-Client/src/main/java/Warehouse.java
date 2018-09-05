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

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Warehouse)
		{
			Warehouse w = (Warehouse) obj;
			if(w.id == id)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return super.equals(obj);
		}
	}







}
