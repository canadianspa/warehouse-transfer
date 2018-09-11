package entities;

public class Warehouse{

	public String name;
	public Long id;

	public Warehouse(String name, long id) {
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
