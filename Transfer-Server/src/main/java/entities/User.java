package entities;

import java.util.UUID;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
@Entity
public class User {
	
	@Id
	public String email;
	public String password;
	public String userKey;
	public int level;

	
	public User(String email, String password, int level) {
		super();
		this.email = email;
		this.password = password;
		this.userKey = UUID.randomUUID().toString();
		this.level = level;
	}

	public User() {}
	
	

}
