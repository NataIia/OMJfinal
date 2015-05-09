package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import persistence.Catalogs;
import service.TaskUtils;
import beans.Task;

@WebServlet ("/selectTasksStudent")
public class SelectTasksStudent extends HttpServlet
{
	private TaskUtils util = new TaskUtils();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("text/javascript");	
		List<Task> tasks = getSolutions(request);
		PrintWriter out = response.getWriter();
		out.println(new JSONArray(tasks));		
	}

	private List<Task> getSolutions(HttpServletRequest request) 
	{
		List<Task> student = Catalogs.get().getTasks().stream()
				.filter(task -> task.getStudent().getId() == Integer.parseInt(request.getParameter("student")))
				.collect(Collectors.toList());
		List<Task> quiz = (util.findTaskByQuizID(request.getParameter("taskQuizStudent")).isEmpty() ? student : util.findTaskByQuizID(request.getParameter("taskQuizStudent")));
		List<Task> thema = (util.findTaskByThema(request.getParameter("taskThemaStudent")).isEmpty() ? student : util.findTaskByThema(request.getParameter("taskThemaStudent")));
		
		return student.stream()
				.filter(b -> quiz.contains(b))
				.filter(b -> thema.contains(b))
				.collect(Collectors.toList());
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
