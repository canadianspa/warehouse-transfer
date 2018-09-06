package transferserver;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Warehouse{

	String name;
	@Id
	Long id;

	
	
	public Warehouse() {
	}

	public Warehouse(String name, Long id) {
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
