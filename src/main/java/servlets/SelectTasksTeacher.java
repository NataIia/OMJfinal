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

import beans.Task;
import persistence.Catalogs;
import service.TaskUtils;

@WebServlet ("/selectTasksTeacher")
public class SelectTasksTeacher extends HttpServlet
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
		List<Task> quiz = (util.findTaskByQuizID(request.getParameter("taskQuizTeacher")).isEmpty() ? Catalogs.get().getTasks() : util.findTaskByQuizID(request.getParameter("taskQuizTeacher")));
		List<Task> student = (util.findTaskByStudentID(request.getParameter("taskPersonTeacher")).isEmpty() ? Catalogs.get().getTasks() : util.findTaskByStudentID(request.getParameter("taskPersonTeacher")));
		
		return quiz.stream().filter(b -> student.contains(b)).collect(Collectors.toList());
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
