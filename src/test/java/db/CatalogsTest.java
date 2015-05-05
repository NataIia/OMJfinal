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
		if ( c.getAnswers() == null) fail("Answers not yet implemented");
	}
	
	public void testQuestionsLoad()
	{
		if ( c.getQuestionTypes() == null) fail("Quiestion Types not yet implemented");
		if ( c.getQuestions() == null) fail("Questions not yet implemented");
	}
	
	public void testQuizzesLoad()
	{
		if ( c.getQuizzes() == null) fail("Quizzes not yet implemented");
	}
	
	public void testSolutionsLoad()
	{
		if ( c.getSolutions() == null) fail("Quizzes not yet implemented");
//		c.getSolutions().stream().forEach(System.out :: println);
	}
}
