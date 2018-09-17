package requests;

import java.util.ArrayList;

import com.googlecode.objectify.Key;

import entities.Item;
import entities.Items;
import entities.TransferJob;
import entities.User;
import entities.Warehouse;

public class CreateUserRequest extends Request {
	
	public String email;
	public String password;
	public int level;

}
