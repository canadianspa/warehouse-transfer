package entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Warehouse{

	public String name;
	@Id
	public Long id;

	
	
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





}
