package transferserver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.googlecode.objectify.ObjectifyService;

import entities.Item;
import entities.Items;
import entities.TransferJob;
import entities.User;
import entities.Warehouse;
import requests.CreateJobRequest;
import requests.LoginRequest;
import requests.changeTransferJobRequest;
@WebServlet(
		name = "LoginServlet",
		urlPatterns = {"/Login"}
		)
public class LoginServlet  extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		
		ObjectifyService.register(TransferJob.class); 
		ObjectifyService.register(Warehouse.class); 
		ObjectifyService.register(Item.class); 
		ObjectifyService.register(Items.class); 
		ObjectifyService.register(User.class); 
		
		try {
			Gson g = new Gson();
			
			LoginRequest lr = g.fromJson(request.getReader().readLine(), LoginRequest.class);
			 
			
			User u = ObjectifyService.ofy().load().type(User.class).id(lr.email).now();

			if(u.password.equals(lr.password))
			{
				response.getWriter().print(u.userKey);
			}
			else
			{
				response.getWriter().print("Failed");

			}
			
			
		} catch (Exception e) {
			response.getWriter().print("Failed");

		}
	}

}