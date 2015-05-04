package service;

import beans.Question;
import beans.Quiz;

public class QuizQuestionUtils
{
	public static void bindQuizQuiestion(Quiz quiz, Question question)
	{
		quiz.addQuestion(question);
	}
}
