package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import persistence.Catalogs;
import beans.QuizSolution;
import beans.Task;

@WebServlet("/tasks")
public class Tasks extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("text/javascript");
		ArrayList<Task> tasks = new ArrayList<>();
		PrintWriter out = response.getWriter();
		if (request.getParameterMap().containsKey("id"))
		{
			int idStudent = Integer.parseInt(request.getParameter("id"));
			Catalogs.get().getTasks().stream()
										.filter(s -> (s.getStudent().getId() == idStudent))
										.forEach(s -> tasks.add(s));
		}
		else
		{
			Catalogs.get().getTasks().stream().forEach(s -> tasks.add(s));
		}
		out.println(new JSONArray(tasks));
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
