package beans;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class QuizSolution extends Entity
{
	private Quiz quiz;
	private int score;
	private Map<Question, List<String>> answers; // mapped array of student answers where Integer refers to questionID and List<String> refers to given answers array
	// prepare list for js 
	private ArrayList<String> answersAsString; 
	private ArrayList<String> questionsAsString;
	private ArrayList<String> correctAnswersAsString;

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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result
				+ ((answersAsString == null) ? 0 : answersAsString.hashCode());
		result = prime
				* result
				+ ((correctAnswersAsString == null) ? 0
						: correctAnswersAsString.hashCode());
		result = prime
				* result
				+ ((questionsAsString == null) ? 0 : questionsAsString
						.hashCode());
		result = prime * result + ((quiz == null) ? 0 : quiz.hashCode());
		result = prime * result + score;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (!super.equals(obj))
		{
			return false;
		}
		if (!(obj instanceof QuizSolution))
		{
			return false;
		}
		QuizSolution other = (QuizSolution) obj;
		if (!super.equals(obj)) return false;
		if (answers == null)
		{
			if (other.answers != null)
			{
				return false;
			}
		} else if (!answers.equals(other.answers))
		{
			return false;
		}
		if (answersAsString == null)
		{
			if (other.answersAsString != null)
			{
				return false;
			}
		} else if (!answersAsString.equals(other.answersAsString))
		{
			return false;
		}
		if (correctAnswersAsString == null)
		{
			if (other.correctAnswersAsString != null)
			{
				return false;
			}
		} else if (!correctAnswersAsString.equals(other.correctAnswersAsString))
		{
			return false;
		}
		if (questionsAsString == null)
		{
			if (other.questionsAsString != null)
			{
				return false;
			}
		} else if (!questionsAsString.equals(other.questionsAsString))
		{
			return false;
		}
		if (quiz == null)
		{
			if (other.quiz != null)
			{
				return false;
			}
		} else if (!quiz.equals(other.quiz))
		{
			return false;
		}
		if (score != other.score)
		{
			return false;
		}
		return true;
	}

	public ArrayList<String> getAnswersAsString()
	{
		ArrayList<String> s = new ArrayList<>();
		for (Map.Entry<Question, List<String>> entry : answers.entrySet())
		{
		    s.add(entry.getValue().get(0) + ";");
		}
		return s;
	}
	
	public ArrayList<String> getQuestionsAsString()
	{
		ArrayList<String> s = new ArrayList<>();
		for (Question q : this.getQuiz().getQuestions())
		{
		    s.add(q.getQuestion());
		}
		return s;
	}


	public ArrayList<String> getCorrectAnswersAsString()
	{
		ArrayList<String> s = new ArrayList<>();
		for (Question q : this.getQuiz().getQuestions())
		{
			String answer = "";
			if(q.getCorrectAnswer() == null)
			{
				try
				{
					ScriptEngineManager mgr = new ScriptEngineManager();
			    	ScriptEngine engine = mgr.getEngineByName("JavaScript");
					String an = engine.eval(q.getQuestion()).toString();
					answer += an + ";";
				} catch (ScriptException e)
				{
					e.printStackTrace();
					System.exit(1);
				}
			}
			else
			{
				for (Answer a : q.getCorrectAnswer())
				{
					answer += (a.getTik() == null ? "" : (a.getTik() + "-")) + a.getTok() + ";";  
				}
			}
				
		    s.add(answer);
		}
		return s;
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
		return "QuizSolution: id=" + super.getId() + " student=" + super.getAuthor();
//				+ "; answers=" + answers + super.toString();
	}	
}
