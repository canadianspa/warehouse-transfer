package entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Warehouse{

	public String name;
	@Id
	public Long id;
	String address_line_1;
	String address_line_2;
	String city;
	String post_code;
	
	
	public Warehouse() {
	}

	

	public Warehouse(String name, Long id, String address_line_1, String address_line_2, String city,
			String post_code) {
		this.name = name;
		this.id = id;
		this.address_line_1 = address_line_1;
		this.address_line_2 = address_line_2;
		this.city = city;
		this.post_code = post_code;
	}



	@Override
	public String toString() {
		return name;
	}





}
