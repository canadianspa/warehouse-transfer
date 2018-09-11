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
		ObjectifyService.ofy().delete().entities(ObjectifyService.ofy().load().type(TransferJob.class)).now();
		ObjectifyService.ofy().delete().entities(ObjectifyService.ofy().load().type(Items.class)).now();
	}
}




