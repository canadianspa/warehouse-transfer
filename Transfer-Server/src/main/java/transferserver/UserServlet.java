package transferserver;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entities.User;
import requests.CreateJobRequest;
import requests.CreateUserRequest;

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
			response.getWriter().print("Failed");

		}
		
	}
}
