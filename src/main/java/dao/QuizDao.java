package dao;

import beans.Quiz;

public class QuizDao implements Idao
{
	public void SaveQuiz(Quiz quiz)
	{
		
	}
	
	/**
	 * Controls if quiz exists in db and return id or -1 if nor exists
	 * @param quiz
	 * @return
	 */
	int exist(Quiz quiz)
	{
		return -1;
	}
	
	public Quiz findById(int id)
	{
		return null;
	}
	
}
