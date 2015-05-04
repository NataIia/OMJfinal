package service;

import java.util.ArrayList;

import persistence.Catalogs;
import beans.Quiz;

public class QuizUtils
{
	public ArrayList<Quiz> findQuizById(String id)
	{
		ArrayList<Quiz> quizzes = new ArrayList<>();
		if (id != null && !id.equals("") && !id.equals("Quiz ID"))
			Catalogs.get().getQuizzes()
						.stream()
						.filter(quiz -> quiz.getId() == Integer.parseInt(id))
						.forEach(quiz -> quizzes.add(quiz));
		return quizzes;
	}
	
	public ArrayList<Quiz> findQuizByThema(String thema)
	{
		ArrayList<Quiz> quizzes = new ArrayList<>();
		if (thema != null && !thema.equals("") && !thema.equals("Thema"))
			Catalogs.get().getQuizzes()
						.stream()
						.filter(quiz -> quiz.getThema().equals(thema))
						.forEach(quiz -> quizzes.add(quiz));
		return quizzes;
	}
	
	public ArrayList<Quiz> findQuizByStudyYear(String studyYear)
	{
		ArrayList<Quiz> quizzes = new ArrayList<>();
		if (studyYear != null && !studyYear.equals("") && !studyYear.equals("Study year"))
			Catalogs.get().getQuizzes()
						.stream()
						.filter(quiz -> quiz.getStudyYear() == Integer.parseInt(studyYear))
						.forEach(quiz -> quizzes.add(quiz));
		return quizzes;
	}
	
	public ArrayList<Quiz> findQuizByNumberOfQuestions(String number)
	{
		ArrayList<Quiz> quizzes = new ArrayList<>();
		if (number != null && !number.equals("") && !number.equals("Number of questions"))
			Catalogs.get().getQuizzes()
						.stream()
						.filter(quiz -> quiz.getQuestions().size() == Integer.parseInt(number))
						.forEach(quiz -> quizzes.add(quiz));
		return quizzes;
	}
	
	public Quiz generateQuiz(String thema, int studyYear)
	{
		return null;
	}
}
