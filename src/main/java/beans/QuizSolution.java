package beans;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizSolution extends Entity
{
	private Quiz quiz;
	private Map<Integer, List<String>> answers; // mapped array of student answers where Integer refers to questionID and List<String> refers to given answers array

	public QuizSolution(String thema, int id, Person author, Quiz quiz, Map<Integer, List<String>> answers,
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
	public Map<Integer, List<String>> getAnswers()
	{
		return answers;
	}
	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(Map<Integer, List<String>> answers)
	{
		this.answers = answers;
	}
	
	public void addAnswer(Question question, List<String> answers)
	{
		if (this.answers == null) this.answers = new HashMap<>();
		this.answers.put(question.getId(), answers);
	}
	
	public int getScore()
	{
		int score = 0;
		return score;
	}
}
