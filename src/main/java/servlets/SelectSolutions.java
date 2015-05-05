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
import beans.QuizSolution;
import service.SolutionUtils;

@WebServlet ("/selectSolutionsStudent")
public class SelectSolutions extends HttpServlet
{
	private SolutionUtils util = new SolutionUtils();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("text/javascript");	
		List<QuizSolution> solutions = getSolutions(request);
		PrintWriter out = response.getWriter();
		out.println(new JSONArray(solutions));		
	}

	private List<QuizSolution> getSolutions(HttpServletRequest request) 
	{
		List<QuizSolution> id = (util.findQuizSolutionById(request.getParameter("numberSolution")).isEmpty() ? Catalogs.get().getSolutions() : util.findQuizSolutionById(request.getParameter("numberSolution")));
		List<QuizSolution> thema = (util.findQuizSolutionByThema(request.getParameter("themaSolution")).isEmpty() ? Catalogs.get().getSolutions() : util.findQuizSolutionByThema(request.getParameter("themaSolution")));
		List<QuizSolution> score = (util.findQuizSolutionByScore(request.getParameter("scoreSolution")).isEmpty() ? Catalogs.get().getSolutions() : util.findQuizSolutionByScore(request.getParameter("scoreSolution")));
		
		return id.stream().filter(b -> thema.contains(b))
							.filter(b -> score.contains(b))
							.collect(Collectors.toList());
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
