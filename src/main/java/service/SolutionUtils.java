package service;

import java.util.ArrayList;

import persistence.Catalogs;
import beans.QuizSolution;

public class SolutionUtils
{
	public ArrayList<QuizSolution> findQuizSolutionById(String id)
	{
		ArrayList<QuizSolution> solutions = new ArrayList<>();
		if (id != null && !id.equals("") && !id.equals("Quiz ID"))
			Catalogs.get().getSolutions()
						.stream()
						.filter(solution -> solution.getQuiz().getId() == Integer.parseInt(id))
						.forEach(solution -> solutions.add(solution));
		return solutions;
	}
	
	public ArrayList<QuizSolution> findQuizSolutionByThema (String thema)
	{
		ArrayList<QuizSolution> solutions = new ArrayList<>();
		if (thema != null && !thema.equals("") && !thema.equals("Thema"))
			Catalogs.get().getSolutions()
						.stream()
						.filter(solution -> solution.getThema().equals(thema))
						.forEach(solution -> solutions.add(solution));
		return solutions;
	}
	
	public ArrayList<QuizSolution> findQuizSolutionByScore(String score)
	{
		ArrayList<QuizSolution> solutions = new ArrayList<>();
		if (score != null && !score.equals("") && !score.equals("Score"))
			Catalogs.get().getSolutions()
						.stream()
						.filter(solution -> solution.getScore() == Integer.parseInt(score))
						.forEach(solution -> solutions.add(solution));
		return solutions;
	}
	
	public ArrayList<QuizSolution> findQuizSolutionByStudent(String student)
	{
		ArrayList<QuizSolution> solutions = new ArrayList<>();
		if (student != null && !student.equals("") && !student.equals("Student"))
		{
			int studentId = Integer.parseInt(student.split("=")[1]);
			Catalogs.get().getSolutions()
						.stream()
						.filter(solution -> (solution.getAuthor().getId() == studentId))
						.forEach(solution -> solutions.add(solution));
		}
		return solutions;
	}
}
