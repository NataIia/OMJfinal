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

import beans.Quiz;
import beans.QuizSolution;
import beans.Student;
import persistence.Catalogs;

@WebServlet("/check_precessor")
public class CheckPrecessor extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		String message = "";
		PrintWriter out = response.getWriter();
		int quizID = Integer.parseInt(request.getParameter("quizID"));
		int studentID = Integer.parseInt(request.getParameter("studentID"));
		
		Quiz quiz = Catalogs.get().getQuizzes().stream()
				.filter(q -> q.getId() == quizID).findFirst().get();
		Student student = (Student) Catalogs.get().getPeople().stream()
				.filter(s -> s.getId() == studentID).findFirst().get();
		
		if (quiz.getMandatoryPrecessor() != null)													//if quiz to be solved contains mandatory precessor
		{
			List<QuizSolution> solutions =  Catalogs.get().getSolutions().stream()					//search for the solutions of the precessor by inlogged student
					.filter(solution -> solution.getAuthor().equals(student))
					.filter(solution -> solution.getQuiz().equals(quiz.getMandatoryPrecessor()))
					.collect(Collectors.toList());
			System.out.println("printsomething" + solutions + "message");
			
			
			if(!solutions.isEmpty())																	//if the precessor has been solved at least once, the score need to be checked
			{
				List<QuizSolution> successfulSolutions = solutions.stream()
						.filter(s -> (s.getScore() >= (s.getQuiz().getQuestions().size() / 2)))		//score of successful solution need t0 be at least 50%
						.collect(Collectors.toList());
				if (!successfulSolutions.isEmpty())
					message += "You successfully solved all necessary quizzes to permit solve the chosen quiz. Success!";
				else
					message += "The chosen quiz required to quiz number " + quiz.getMandatoryPrecessor().getId() + 
					" to be solved with score min " + (int) Math.ceil(quiz.getMandatoryPrecessor().getQuestions().size()/2);
			}
			else
				message += "The chosen quiz required to quiz number " + quiz.getMandatoryPrecessor().getId() + 
						" to be solved";
		}
		else
			message += "Success!";

		out.println(message);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
