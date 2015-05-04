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

import beans.Quiz;
import persistence.Catalogs;

@WebServlet("/quizzes")
public class Quizzes extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("text/javascript");
		List<Quiz> quizzes = new ArrayList<>();
		PrintWriter out = response.getWriter();
		Catalogs.get().getQuizzes().stream().forEach(q -> quizzes.add(q));
		out.println(new JSONArray(quizzes));
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
