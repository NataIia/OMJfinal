package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import persistence.Catalogs;
import beans.QuizSolution;

@WebServlet("/solutions")
public class Solutions extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("text/javascript");
		ArrayList<QuizSolution> solutions = new ArrayList<>();
		PrintWriter out = response.getWriter();
		int idStudent = Integer.parseInt(request.getParameter("id"));
		Catalogs.get().getSolutions().stream()
					.filter(s -> (s.getAuthor().getId() == idStudent))
					.forEach(s -> solutions.add(s));
		out.println(new JSONArray(solutions));
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
