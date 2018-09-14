package requests;

import com.googlecode.objectify.ObjectifyService;

import entities.TransferJob;
import entities.User;

public abstract class Request {
	
	String userKey;
	
	public int getLevel()
	{
		return getUser().level;
	}
	
	public User getUser()
	{
		Iterable<User> users = ObjectifyService.ofy().load().type(User.class);
		for(User u: users)
		{
			if(u.userKey.equals(userKey))
			{
				return u;
			}
		}
		return new User("Wrong","Wrong", 0);
	}
	

}
