package transferserver;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.googlecode.objectify.ObjectifyService;

import entities.Item;
@WebServlet(
		name = "ItemServlet",
		urlPatterns = {"/Item"}
		)
public class ItemServlet extends HttpServlet {


	String APIKEY = "***REMOVED***";

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws IOException {

		
		String query = "brown spa";
		ArrayList<Item> result = new ArrayList<Item>();
		Client client = ClientBuilder.newClient();
		Response response;
		try {

			response = client.target("https://api.veeqo.com/products?query=" + URLEncoder.encode(query, "UTF-8"))
					.request(MediaType.APPLICATION_JSON_TYPE)
					.header("x-api-key", APIKEY)
					.get();
			String body = response.readEntity(String.class);
			JsonArray jsonArray = new JsonArray();
			JsonParser jparse = new JsonParser();
			jsonArray = jparse.parse(body).getAsJsonArray();
			for(JsonElement j : jsonArray)
			{
				JsonArray sellableArray = j.getAsJsonObject().get("sellables").getAsJsonArray();
				for(JsonElement e : sellableArray)
				{
					Item i = new Item(e.getAsJsonObject().get("full_title").getAsString(),e.getAsJsonObject().get("sku_code").getAsString(),e.getAsJsonObject().get("id").getAsLong());
					result.add(i);
					ObjectifyService.ofy().save().entity(i).now();
					res.getWriter().print(i.productTitle + "\r\n");
				}
			}


		} catch (UnsupportedEncodingException e) {
		}



	}

}