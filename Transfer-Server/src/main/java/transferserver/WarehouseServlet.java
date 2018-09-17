package transferserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.googlecode.objectify.ObjectifyService;

import entities.Warehouse;
@WebServlet(
		name = "WarehouseServlet",
		urlPatterns = {"/Warehouse"}
		)
public class WarehouseServlet extends HttpServlet {


	String APIKEY = "***REMOVED***";

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		
		Client client = ClientBuilder.newClient();
		Response response = client.target("https://api.veeqo.com/warehouses?page_size=25")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.header("x-api-key", APIKEY)
				.get();

		String body = response.readEntity(String.class);

		Gson g = new Gson();
		java.lang.reflect.Type collectionType = new TypeToken<Collection<Warehouse>>(){}.getType();
		Collection<Warehouse> enums = g.fromJson(body, collectionType);
		ArrayList<Warehouse> listOfWarehouses = new ArrayList<Warehouse>();
		for(Warehouse w: enums)
		{
			listOfWarehouses.add(w);
			ObjectifyService.ofy().save().entity(w).now();
			
		}
		
		res.getWriter().println(g.toJson(listOfWarehouses));

	}

}
