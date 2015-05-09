package beans;

import java.util.GregorianCalendar;

public class Task extends Entity
{
	private Quiz quiz;
	private Person student;
	private String status;

	public Task(int id, Quiz quiz, Person student, String status, Person author, String thema, 
			GregorianCalendar creationDate)
	{
		super(thema, id, author, creationDate);
		this.quiz = quiz;
		this.student = student;
		this.setStatus(status);
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
	 * @return the student
	 */
	public Person getStudent()
	{
		return student;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent(Person student)
	{
		this.student = student;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((quiz == null) ? 0 : quiz.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
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
		if (!(obj instanceof Task))
		{
			return false;
		}
		Task other = (Task) obj;
		if(!super.equals(obj)) return false;
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
		if (status == null)
		{
			if (other.status != null)
			{
				return false;
			}
		} else if (!status.equals(other.status))
		{
			return false;
		}
		if (student == null)
		{
			if (other.student != null)
			{
				return false;
			}
		} else if (!student.equals(other.student))
		{
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Task: quiz=" + quiz.getId() + " -> student=" + student.getFirstName() + " " + student.getSecondName() + "; status="	+ status;
	}
	
	
}
