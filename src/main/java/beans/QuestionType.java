package beans;

public class QuestionType
{
	private int id;
	private String description;
	
	public QuestionType(int id, String description)
	{
		super();
		this.id = id;
		this.description = description;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	
	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	@Override
	public String toString()
	{
		return "QuestionType: id=" + id + "; description=" + description;
	}
}
