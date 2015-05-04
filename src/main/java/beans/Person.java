package beans;

import java.util.GregorianCalendar;

public abstract class Person
{
	private int id;
	private String firstName, secondName, loginName, password;
	private GregorianCalendar birthDate;
	
	public Person(int id, String firstName, String secondName, String loginName, String password,
			GregorianCalendar birthDate)
	{
		super();
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.birthDate = birthDate;
		this.loginName = loginName;
		this.password = password;
	}

	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @return the secondName
	 */
	public String getSecondName()
	{
		return secondName;
	}

	/**
	 * @param secondName the secondName to set
	 */
	public void setSecondName(String secondName)
	{
		this.secondName = secondName;
	}

	public String getLoginName()
	{
		return loginName;
	}

	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return the birthDate
	 */
	public GregorianCalendar getBirthDate()
	{
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(GregorianCalendar birthDate)
	{
		this.birthDate = birthDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((secondName == null) ? 0 : secondName.hashCode());
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
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof Person))
		{
			return false;
		}
		Person other = (Person) obj;
		if (birthDate == null)
		{
			if (other.birthDate != null)
			{
				return false;
			}
		} else if (!birthDate.equals(other.birthDate))
		{
			return false;
		}
		if (firstName == null)
		{
			if (other.firstName != null)
			{
				return false;
			}
		} else if (!firstName.equals(other.firstName))
		{
			return false;
		}
		if (secondName == null)
		{
			if (other.secondName != null)
			{
				return false;
			}
		} else if (!secondName.equals(other.secondName))
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
		return "Person: id=" + id + "; first name=" + firstName
				+ "; second name=" + secondName + 
				"; login name=" + loginName +
				"; password=" + password +
				"; birth date=" + birthDate.getTime();
	}
	
	
}
