package beans;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Quiz extends Entity
{
	private int studyYear;
	private ArrayList<Question> questions;
	
	public Quiz(String thema, int id, Person author,
			GregorianCalendar creationDate, int studyYear)
	{
		super(thema, id, author, creationDate);
		this.studyYear = studyYear;
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
	 * @return the questions
	 */
	public ArrayList<Question> getQuestions()
	{
		return questions;
	}

	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(ArrayList<Question> questions)
	{
		this.questions = questions;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((questions == null) ? 0 : questions.hashCode());
		result = prime * result + studyYear;
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
		if (!(obj instanceof Quiz))
		{
			return false;
		}
		Quiz other = (Quiz) obj;
		if (!super.equals(obj)) return false;
		if (questions == null)
		{
			if (other.questions != null)
			{
				return false;
			}
		} else if (!questions.equals(other.questions))
		{
			return false;
		}
		if (studyYear != other.studyYear)
		{
			return false;
		}
		return true;
	}
	
	public void addQuestion(Question question)
	{
		if (questions == null) questions = new ArrayList<>();
		questions.add(question);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Quiz: " + super.toString() + "; studyYear=" + studyYear + "; questions=" + questions;
	}
	
	
}
