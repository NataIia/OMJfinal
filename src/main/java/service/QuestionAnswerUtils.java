package service;

import beans.Answer;
import beans.Question;

public class QuestionAnswerUtils
{
	public static void bindQuestionAnswer(Question question, Answer answer)
	{
		question.addAnswer(answer);
	}
	
	public static void bindQuestionCorrectAnswer(Question question, Answer answer)
	{
		question.addCorrectAnswer(answer);
	}

}
