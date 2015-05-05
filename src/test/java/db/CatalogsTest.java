package db;

import junit.framework.TestCase;


import persistence.Catalogs;
import persistence.DBmanager;

public class CatalogsTest extends TestCase 
{
	private Catalogs c;

	protected static void setUpBeforeClass() throws Exception
	{
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		new DBmanager();
		c = Catalogs.get();
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	
	public void testTeachersLoad()
	{
		if(	c.getTeachers() == null) fail("Teachers not yet implemented");
	}
	
		public void testStudentLoad()
	{
		if ( c.getStudents() == null) fail("Students not yet implemented");
	}
	
	
	public void testAnswersLoad()
	{
		c.getAnswers().stream().forEach(System.out::println);
	}
	
	public void testQuestionsLoad()
	{
		c.getQuestionTypes().stream().forEach(System.out::println);
		c.getQuestions().stream().forEach(System.out::println);
	}
	
	public void testQuizzesLoad()
	{
		c.getQuizzes().stream().forEach(System.out::println);
	}
}
