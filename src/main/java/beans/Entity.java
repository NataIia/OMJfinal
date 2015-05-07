package beans;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * abstract class with common properties for quiz, question and answer
 * 
 * 
 * @author Natalia Dyubankova
 * @since 26/04/2015
 *
 */
public abstract class Entity
{
	private String thema;
	private int id;
	private Person author;
	private GregorianCalendar creationDate;
	private String date; // creation date in string format to pass js

	
	
	public Entity(String thema, int id, Person author,
			GregorianCalendar creationDate)
	{
		super();
		this.thema = thema;
		this.id = id;
		this.author = author;
		this.creationDate = creationDate;
	}

	/**
	 * @return the thema
	 */
	public String getThema()
	{
		return thema;
	}

	/**
	 * @param thema the thema to set
	 */
	public void setThema(String thema)
	{
		this.thema = thema;
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
	 * @return the author
	 */
	public Person getAuthor()
	{
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(Person author)
	{
		this.author = author;
	}

	/**
	 * @return the creationDate
	 */
	public GregorianCalendar getCreationDate()
	{
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(GregorianCalendar creationDate)
	{
		this.creationDate = creationDate;
	}

	public String getDate()
	{
		SimpleDateFormat string = new SimpleDateFormat("d MMMM yyyy");
		return string.format(this.creationDate.getTime()).toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((thema == null) ? 0 : thema.hashCode());
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
		if (!(obj instanceof Entity))
		{
			return false;
		}
		Entity other = (Entity) obj;
		if (thema == null)
		{
			if (other.thema != null)
			{
				return false;
			}
		} else if (!thema.equals(other.thema))
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
		return "id=" + id + "thema=" + thema +  "; author=" + author.getFirstName() + " " + author.getSecondName()
				+ "; creationDate=" + creationDate.getTime();
	}	
	
}

