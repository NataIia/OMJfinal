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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Task: quiz=" + quiz.getId() + " -> student=" + student.getFirstName() + " " + student.getSecondName() + "; status="	+ status;
	}
	
	
}
