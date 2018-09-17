package entities;

import java.util.UUID;

public class User {
	
	
	public String email;
	public String password;
	public String userKey;
	public int level;

	
	public User(String email, String password, int level) {
	
		this.email = email;
		this.password = password;
		this.userKey = UUID.randomUUID().toString();
		this.level = level;
	}

	public User() {}
	
	

}
