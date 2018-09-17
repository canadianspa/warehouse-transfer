package transferserver;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.googlecode.objectify.ObjectifyService;

import entities.Item;
import entities.Items;
import entities.TransferJob;
import entities.User;
import entities.Warehouse;
@WebServlet(
		name = "tdasu",
		urlPatterns = {"/tdasu"}
		)
public class TearDownAndSetUp extends HttpServlet {



	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		
		ObjectifyService.register(TransferJob.class); 
		ObjectifyService.register(Warehouse.class); 
		ObjectifyService.register(Item.class); 
		ObjectifyService.register(Items.class); 
		ObjectifyService.register(User.class); 
		
		User jake = new User("***REMOVED***","***REMOVED***",3);
		ObjectifyService.ofy().save().entity(jake).now();
		
		User troy = new User("troy@canadianspacompany.com","canspa",3);
		ObjectifyService.ofy().save().entity(troy).now();
		
		User phil = new User("phil@canadianspacompany.com","canspa",3);
		ObjectifyService.ofy().save().entity(phil).now();
		
		User kerry = new User("kerry@canadianspacompany.com","canspa",2);
		ObjectifyService.ofy().save().entity(kerry).now();
		
		User paul = new User("paul@canadianspacompany.com","canspa",2);
		ObjectifyService.ofy().save().entity(paul).now();
		
		User tony = new User("tony@canadianspacompany.com","canspa",2);
		ObjectifyService.ofy().save().entity(tony).now();
		
		User len = new User("lenahearn@yahoo.co.uk","canspa",2);
		ObjectifyService.ofy().save().entity(len).now();
		
		User mike = new User("mike@canadianspacompany.com","canspa",2);
		ObjectifyService.ofy().save().entity(mike).now();
		
		User adam = new User("adam@canadianspacompany.com","canspa",2);
		ObjectifyService.ofy().save().entity(adam).now();
		

		
	}
}




