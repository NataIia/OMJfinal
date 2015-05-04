package beans;

import java.util.GregorianCalendar;

public class Teacher extends Person
{
	private String specialisation;
	
	public Teacher(int id, String firstName, String secondName, String loginName, String password,
			GregorianCalendar birthDate, String specialisation)
	{
		super(id, firstName, secondName, loginName, password, birthDate);
		this.specialisation = specialisation;
	}

	/**
	 * @return the specialisation
	 */
	public String getSpecialisation()
	{
		return specialisation;
	}

	/**
	 * @param specialisation the specialisation to set
	 */
	public void setSpecialisation(String specialisation)
	{
		this.specialisation = specialisation;
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
				+ ((specialisation == null) ? 0 : specialisation.hashCode());
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
		if (!(obj instanceof Teacher))
		{
			return false;
		}
		Teacher other = (Teacher) obj;
		if(!super.equals(obj)) return false;			
		else if (specialisation == null)
		{
			if (other.specialisation != null)
			{
				return false;
			}
		} else if (!specialisation.equals(other.specialisation))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return super.toString() + "; specialisation: " + this.specialisation;
	}
}
