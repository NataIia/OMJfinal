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
import beans.Quiz;
import service.QuizUtils;

@WebServlet ("/selectQuizzes")
public class SelectQuizzes extends HttpServlet
{
	private QuizUtils util = new QuizUtils();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("text/javascript");	
		List<Quiz> quizzes = getQuizzes(request);
		PrintWriter out = response.getWriter();
		out.println(new JSONArray(quizzes));		
	}

	private List<Quiz> getQuizzes(HttpServletRequest request) 
	{
		List<Quiz> id = (util.findQuizById(request.getParameter("number")).isEmpty() ? Catalogs.get().getQuizzes() : util.findQuizById(request.getParameter("number")));
		List<Quiz> thema = (util.findQuizByThema(request.getParameter("thema")).isEmpty() ? Catalogs.get().getQuizzes() : util.findQuizByThema(request.getParameter("thema")));
		List<Quiz> studyYear = (util.findQuizByStudyYear(request.getParameter("study_year")).isEmpty() ? Catalogs.get().getQuizzes() : util.findQuizByStudyYear(request.getParameter("study_year")));
		List<Quiz> numberQuestions = (util.findQuizByNumberOfQuestions(request.getParameter("number_questions")).isEmpty() ? Catalogs.get().getQuizzes() : util.findQuizByNumberOfQuestions(request.getParameter("number_questions")));
		
		return id.stream().filter(b -> thema.contains(b))
							.filter(b -> studyYear.contains(b))
							.filter(b -> numberQuestions.contains(b))
							.collect(Collectors.toList());
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
