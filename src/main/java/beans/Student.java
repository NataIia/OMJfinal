package beans;

import java.util.GregorianCalendar;

public class Student extends Person
{
	private int studyYear;
	
	public Student(int id, String firstName, String secondName, String loginName, String password,
			GregorianCalendar birthDate, int studyYear)
	{
		super(id, firstName, secondName, loginName, password, birthDate);
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
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
		if (!(obj instanceof Student))
		{
			return false;
		}
		Student other = (Student) obj;
		if(!super.equals(obj)) return false;
		if (studyYear != other.studyYear)
		{
			return false;
		}
		return true;
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "; study year: " + this.studyYear;
	}
}
