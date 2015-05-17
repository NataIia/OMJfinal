package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.Catalogs;

@WebServlet ("/submitSolution")
public class SubmitSolution extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		String[] answers = request.getParameter("quizAnswers").split(":");
		Catalogs.get().addQuizSolutionToDB(request.getParameter("student"), 
								request.getParameter("quiz"), 
								Integer.parseInt(request.getParameter("score")),
								answers);
		
		PrintWriter out = response.getWriter();
		out.print("Your solution is registered in data bank");

	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
