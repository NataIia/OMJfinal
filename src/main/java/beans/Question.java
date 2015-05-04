package beans;

/**
 * 
 * property type defines one type of the answer from possible:
 * o Ja/nee vraag 
 * o Multiple choice door selectie van 1 antwoord 
 * o Muliple choice met meedere mogelijke antwoorden 
 * o Numeriek antwoord door gebruik van een slider. 
 * o Numeriek antwoord door ingave via toetsenbord. 
 * o Drag and drop waarbij het juiste antwoord in een bepaald veld moet geplaatst worden. 
 * 	 (hier met variatie meerdere mogelijkheden naar 1 antwoord gebied maar ook meerdere 
 * 	 mogelijkheden naar evenveel antwoordgebieden.
 * 
 * For question with one answer possible the array correctAnswer contains only one line,
 * for question with several answers possible the array correct Answer contains as many linea as necessary
 * 
 * @author Natalia Dyubankova
 * @since 26/04/2015
 *
 */
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Question extends Entity
{

	private String type, question;
	private int studyYear;
	private ArrayList<Answer> correctAnswer;
	private ArrayList<Answer> answer;
	
	public Question(String question, String thema, int id, Person author,
			GregorianCalendar creationDate, String type, int studyYear)
	{
		super(thema, id, author, creationDate);
		this.type = type;
		this.question = question;
		this.studyYear = studyYear;
	}
	
	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}
	/**
	 * @return the question
	 */
	public String getQuestion()
	{
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question)
	{
		this.question = question;
	}
	/**
	 * @return the studyYear
	 */
	public int getStudyYear()
	{
		return studyYear;
	}
	/**
	 * @param studyYear the studyYear to set
	 */
	public void setStudyYear(int studyYear)
	{
		this.studyYear = studyYear;
	}
	/**
	 * @return the correctAnswer
	 */
	public ArrayList<Answer> getCorrectAnswer()
	{
		return correctAnswer;
	}
	/**
	 * @param correctAnswer the correctAnswer to set
	 */
	public void setCorrectAnswer(ArrayList<Answer> correctAnswer)
	{
		this.correctAnswer = correctAnswer;
	}
	/**
	 * @return the answers
	 */
	public ArrayList<Answer> getAnswer()
	{
		return answer;
	}
	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(ArrayList<Answer> answers)
	{
		this.answer = answers;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result
				+ ((correctAnswer == null) ? 0 : correctAnswer.hashCode());
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		result = prime * result + studyYear;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (!(obj instanceof Question))
		{
			return false;
		}
		Question other = (Question) obj;
		if (super.getThema() == null)
		{
			if (other.getThema() != null)
			{
				return false;
			}
		} else if (!super.getThema().equals(other.getThema()))
		{
			return false;
		}
		if (answer == null)
		{
			if (other.answer != null)
			{
				return false;
			}
		} else if (!answer.equals(other.answer))
		{
			return false;
		}
		if (correctAnswer == null)
		{
			if (other.correctAnswer != null)
			{
				return false;
			}
		} else if (!correctAnswer.equals(other.correctAnswer))
		{
			return false;
		}
		if (question == null)
		{
			if (other.question != null)
			{
				return false;
			}
		} else if (!question.equals(other.question))
		{
			return false;
		}
		if (studyYear != other.studyYear)
		{
			return false;
		}
		if (type == null)
		{
			if (other.type != null)
			{
				return false;
			}
		} else if (!type.equals(other.type))
		{
			return false;
		}
		return true;
	}
	
	public void addAnswer(Answer answer)
	{
		if (this.answer == null) this.answer = new ArrayList<>();
		this.answer.add(answer);
	}
	
	public void addCorrectAnswer(Answer answer)
	{
		if (correctAnswer == null) correctAnswer = new ArrayList<>();
		correctAnswer.add(answer);
	}

	/**
	 * return negative if answer is not correct and 0 is correct
	 * 
	 * @param answer
	 * @return
	 */
	public int isCorrectAnswer(ArrayList<String> answer)
	{
		int result = -1*answer.size();
		switch(type)
		{
			case "yes/no":
			case "select_one":
			case "select_more":
				if (answer.size() == correctAnswer.size())
				{
					for(String a : answer)
					{
						if (correctAnswer.contains(a)) result++;
					}
				}
				break;
			case "type_number":
			case "scrol_number":
			try
			{	
				ScriptEngineManager mgr = new ScriptEngineManager();
		    	ScriptEngine engine = mgr.getEngineByName("JavaScript");
				String a = engine.eval(question).toString();
				if (answer.get(0).equals(a)) result ++;
			} catch (ScriptException e)
			{
				e.printStackTrace();
				System.exit(1);
			}
				break;
			default:
				break;
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Question: type=" + type + "; question=" + question
				+ "; studyYear=" + studyYear + "; correctAnswer="
				+ correctAnswer + "; answers=" + answer;
	}
	
	
}
