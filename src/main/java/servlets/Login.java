package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.PersonDao;
import beans.Person;


@WebServlet("/login")
public class Login extends HttpServlet
{
	PersonDao pd = new PersonDao();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		PrintWriter out = response.getWriter();
		
		//this part of code suppose to deal with existing person in db
		String loginName = request.getParameter("login_name");
		String loginPassword = request.getParameter("password");
		if (loginName != null)
		{
			Person p = pd.findByLoginName(loginName);
			if (p.getPassword().equals(loginPassword))	out.println(new JSONObject(p));
			else out.println("{\"id\": \"wrong password\"}");
		}
		
		//this part of code suppose to register new student and login as new registered student
		String registerFirstName = request.getParameter("first_name");
		String registerLastName = request.getParameter("last_name");
		String registerName = request.getParameter("register_name");
		
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
