package beans;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizSolution extends Entity
{
	private Quiz quiz;
	private int score;
	private Map<Question, List<String>> answers; // mapped array of student answers where Integer refers to questionID and List<String> refers to given answers array

	public QuizSolution(String thema, int id, Person author, Quiz quiz, Map<Question, List<String>> answers,
			GregorianCalendar creationDate)
	{
		super(thema, id, author, creationDate);
		this.quiz = quiz;
		this.answers = answers;
	}
	public QuizSolution(String thema, int id, Person author, Quiz quiz,
			GregorianCalendar creationDate)
	{
		super(thema, id, author, creationDate);
		this.quiz = quiz;
	}
	/**
	 * @return the quiz
	 */
	public Quiz getQuiz()
	{
		return quiz;
	}
	/**
	 * @param quiz the quiz to set
	 */
	public void setQuiz(Quiz quiz)
	{
		this.quiz = quiz;
	}
	/**
	 * @return the answers
	 */
	public Map<Question, List<String>> getAnswers()
	{
		return answers;
	}
	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(Map<Question, List<String>> answers)
	{
		this.answers = answers;
	}
	
	public void addAnswer(Question question, String answer)
	{
		List<String> a;
		if (this.answers == null) this.answers = new HashMap<>();
		
		if (answers.containsKey(question)) a = answers.get(question);
		else a = new ArrayList<>();
		a.add(answer);
		
		this.answers.remove(question);
		this.answers.put(question, a);
	}
	
	public int getScore()
	{
		int score = 0;
		return score;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "QuizSolution: quiz=" + quiz + "; answers=" + answers + super.toString();
	}
	
	
}
