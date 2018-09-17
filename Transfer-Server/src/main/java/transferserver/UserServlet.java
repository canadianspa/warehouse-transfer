package transferserver;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TimeZone;

import javax.servlet.ServletException;
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

import entities.TransferJob;
import entities.User;
import entities.Warehouse;
import requests.CreateUserRequest;
import transferserver.TransferJobsServlet.TransferJobReply;

@WebServlet(
		name = "UserServlet",
		urlPatterns = {"/User"}
		)
public class UserServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		try {
			Gson g = new Gson();
			
			CreateUserRequest cur = g.fromJson(request.getReader().readLine(), CreateUserRequest.class);
			if(cur.getLevel() < 3)
			{
				throw new Exception("Need higher Level");
			}
			User u = new User(cur.email,cur.password,cur.level);
			response.getWriter().print("Success");
		} catch (Exception e) {
			response.getWriter().print(e.getMessage());

		}
		
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		
		ArrayList<User> result = new ArrayList<User>();
		Iterable<User> iu = ObjectifyService.ofy().load().type(User.class);
		for(User u: iu)
		{
			 result.add(u);
			
		}
		Gson g = new Gson();
		res.getWriter().println(g.toJson(result));

	}
}
