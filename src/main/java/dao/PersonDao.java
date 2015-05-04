package dao;

import persistence.Catalogs;
import beans.Person;
import beans.Student;
import beans.Teacher;

public class PersonDao implements Idao
{
	public void save(Person person)
	{
		
	}
	
	/**
	 * Controls if quiz exists in db and return id or -1 if nor exists
	 * @param quiz
	 * @return
	 */
	int exist(Person person)
	{
		return -1;
	}
	
	public Person findById(int id)
	{
		Person p = null;

		return null;
	}
	
	public Person findByLoginName(String loginName)
	{
		if (isExist(loginName))
		{
			return Catalogs.get().getPeople().stream()
					.filter(p -> p.getLoginName()
					.equals(loginName))
					.findFirst().get();
		}
		else 
			return null;
	}
	
	private boolean isExist(String loginName)
	{
		for(Person p : Catalogs.get().getPeople())
		{
			if (p.getLoginName().equals(loginName)) return true;
		}
		return false;
	}
}
