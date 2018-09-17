package requests;

public class CreateUserRequest extends Request {

	public String email;
	public String password;
	public int level;
	public CreateUserRequest(String email, String password, int level) {
		super();
		this.email = email;
		this.password = password;
		this.level = level;
	}

	
	
}
