package transferserver;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import entities.Item;
import entities.Items;
import entities.TransferJob;
import entities.Warehouse;

@WebServlet(
		name = "TransferJobServlet",
		urlPatterns = {"/TransferJob"}
		)
public class TransferJobsServlet extends HttpServlet {


	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException {


		ObjectifyService.register(TransferJob.class); 
		ObjectifyService.register(Warehouse.class); 
		ObjectifyService.register(Item.class); 
		ObjectifyService.register(Items.class); 
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");




		Warehouse recvWarehouse = new Warehouse("Canada House", 4L);
		Warehouse dispWarehouse = new Warehouse("Verran", 5L);
		ArrayList<Items> listOfItems = new ArrayList();
		Item spa = new Item("Spa", "SK1", 1L);
		Item chemical = new Item("Chemical", "SH2", 2L);
		
		ObjectifyService.ofy().save().entity(spa).now();
		ObjectifyService.ofy().save().entity(chemical).now();
		
		Key<Item> spaKey = Key.create(Item.class, 1L);
		Key<Item> chemicalKey = Key.create(Item.class, 2L);
		
		listOfItems.add(new Items(spaKey,1));
		listOfItems.add(new Items(chemicalKey,2));
		
		ObjectifyService.ofy().save().entity(recvWarehouse).now();
		ObjectifyService.ofy().save().entity(dispWarehouse).now();

		Key<Warehouse> recvKey = Key.create(Warehouse.class, 4L);
		Key<Warehouse> dispKey = Key.create(Warehouse.class, 5L);
	
		
		TransferJob tj = new TransferJob(recvKey,  dispKey, listOfItems);

		ObjectifyService.ofy().save().entity(tj).now();
	
		Iterable<TransferJob> it = ObjectifyService.ofy().load().type(TransferJob.class);
		for(TransferJob t: it)
		{
			response.getWriter().print(t.id + "\r\n");
			response.getWriter().print(t + "\r\n");
			for(Items i: t.listOfItems)
			{
				response.getWriter().print(i + "\r\n");

			}
			response.getWriter().print("\r\n");
		}
		




	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectifyService.register(TransferJob.class); 
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");

		ObjectifyService.ofy().save().entity(new TransferJob()).now();

		response.getWriter().print(request.toString());
	}



}