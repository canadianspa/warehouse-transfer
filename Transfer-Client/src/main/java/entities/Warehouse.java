package entities;

public class Warehouse{

	public String name;
	public Long id;
	public String address_line_1;
	public String address_line_2;
	public String city;
	public String post_code;

	

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

	public String createAddress()
	{
		String output ="";
		output += name + System.lineSeparator() + System.lineSeparator();
		
		output += address_line_1 + System.lineSeparator();
		output += address_line_2 + System.lineSeparator();
		output += city + System.lineSeparator();
		output += post_code;
		return output;
	}
	
	







}
