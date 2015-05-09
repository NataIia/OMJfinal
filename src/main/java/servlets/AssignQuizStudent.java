package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.Catalogs;

@WebServlet ("/assignQuizStudent")
public class AssignQuizStudent extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		String message = "";
		String headers[] = {"Student", "Quiz ID", "Status"};
		String parameters[] = {request.getParameter("taskPersonTeacher"), request.getParameter("taskQuizTeacher"), request.getParameter("taskStatusTeacher")};
		for (String header : headers)
		{
			if (Arrays.asList(parameters).contains(header))
			{
				message += (header + " can not be null. ");
			}
		}
		if(message.equals(""))
		{
			Catalogs.get().addTaskToDB(request.getParameter("taskPersonTeacher"), 
									request.getParameter("taskQuizTeacher"), 
									request.getParameter("taskStatusTeacher"), 
									request.getParameter("teacher"));
			message = "New task is added to quiz";
		}
		
		PrintWriter out = response.getWriter();
		out.print(message);

	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
