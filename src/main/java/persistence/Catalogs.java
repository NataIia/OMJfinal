package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import service.QuestionAnswerUtils;
import service.QuizQuestionUtils;
import service.QuizUtils;
import beans.Answer;
import beans.Person;
import beans.Question;
import beans.QuestionType;
import beans.Quiz;
import beans.QuizSolution;
import beans.Student;
import beans.Task;
import beans.Teacher;
import dao.Idao;

public class Catalogs implements Idao
{
	private static Catalogs instance;
	
	private ArrayList<Question> questions;
	private ArrayList<Answer> answers;
	private ArrayList<Quiz> quizzes;
	private ArrayList<Teacher> teachers;
	private ArrayList<Student> students;
	private ArrayList<QuestionType> questionTypes;
	private ArrayList<QuizSolution> solutions;
	private ArrayList<Task> tasks;
	
	
	
	protected Catalogs()
	{
		new DBmanager();
		setPeople();
		setAnswers();
		setQuestionTypes();
		setQuestions();
		setQuizzes();
		setSolutions();
		setTasks();
	}
	
	public static Catalogs get()
	{
		if(instance == null) instance = new Catalogs();
		return instance;
	}
	
	private void setPeople()
	{
		if (teachers == null) teachers = new ArrayList<>();
		if (students == null) students = new ArrayList<>();
		
    	GregorianCalendar birthDate = new GregorianCalendar();
		
		try
		{
			//read teachers
			String queryTeachers = "select * from tbl_teacher t, tbl_person p "
							+ "where t.id = p.id";
        	Statement statementTeachers = connection.createStatement();
        	ResultSet rsTeachers = statementTeachers.executeQuery(queryTeachers);
        	
        	while (rsTeachers.next())
        	{
        		birthDate.setTimeInMillis(rsTeachers.getDate("birth_date").getTime());
        		teachers.add(new Teacher(rsTeachers.getInt("id"), 
        				rsTeachers.getString("first_name"),
        				rsTeachers.getString("last_name"),
        				rsTeachers.getString("login_name"),
        				rsTeachers.getString("password_field"),
        				birthDate,
        				rsTeachers.getString("specialisation")));
        	}
        	
    		//read students
			String queryStudents = "select * from tbl_student s, tbl_person p "
					+ "where s.id = p.id";
			Statement statementStudents = connection.createStatement();
			ResultSet rsStudents = statementStudents.executeQuery(queryStudents);
			
			while (rsStudents.next())
			{
				birthDate.setTimeInMillis(rsStudents.getDate("birth_date").getTime());
				students.add(new Student(rsStudents.getInt("id"), 
						rsStudents.getString("first_name"),
						rsStudents.getString("last_name"),
						rsStudents.getString("login_name"),
						rsStudents.getString("password_field"),
						birthDate,
						rsStudents.getInt("study_year")));
			}
        	
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void setAnswers()
	{
		if (answers == null) answers = new ArrayList<>();
		if (quizzes == null) quizzes = new ArrayList<>();
	
		// read mono answers
     	String query_monoanswers = "select * from tbl_monoanswer ma, tbl_answer a, tbl_teacher t, tbl_person p "
     							+ "where ma.id = a.id and a.autheur = t.id and t.id = p.id";       	
     	String query_multianswers = "select * from tbl_multianswer ma, tbl_answer a, tbl_teacher t, tbl_person p "
					+ "where ma.id = a.id and a.autheur = t.id and t.id = p.id";       	

     	try
		{
			Statement statement = connection.createStatement();
        	ResultSet rsMono = statement.executeQuery(query_monoanswers);
        	GregorianCalendar creationDate = new GregorianCalendar();
          	while (rsMono.next())
        	{
          		int idAuthor = rsMono.getInt("autheur");
          		creationDate.setTimeInMillis(rsMono.getDate("creation_date").getTime());
        		answers.add(new Answer(rsMono.getInt("id"),
    					null,
    					rsMono.getString("tok"),
    					rsMono.getString("question_type"),
    					rsMono.getString("field"),
    					teachers.stream().filter(t -> (t.getId() == idAuthor)).findFirst().get(),
    					creationDate)); 
        	}
          //read multi answers
        	ResultSet rsMulti = statement.executeQuery(query_multianswers);
          	while (rsMulti.next())
        	{
          		int idAuthor = rsMulti.getInt("autheur");
          		creationDate.setTimeInMillis(rsMulti.getDate("creation_date").getTime());
        		answers.add(new Answer(rsMulti.getInt("id"),
        				rsMulti.getString("tik"),
    					rsMulti.getString("tok"),
    					rsMulti.getString("question_type"),
    					rsMulti.getString("field"),
    					teachers.stream().filter(t -> (t.getId() == idAuthor)).findFirst().get(),
    					creationDate)); 
        	}
		} catch (SQLException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void setQuestionTypes()
	{
		if (questionTypes == null) questionTypes = new ArrayList<>();
		
		String query = "select * from omj_final.tbl_question_type"; 
		
		try
		{
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		while (rs.next())
			{
			questionTypes.add(new QuestionType(rs.getInt("id"),
						rs.getString("description"))); 
			}
		} catch (SQLException e)
		{
		e.printStackTrace();
		System.exit(1);
		}
		
	}
	
	private void setQuestions()
	{
		if (questions == null) questions = new ArrayList<>();
		
     	String queryQuestion = "select * from omj_final.tbl_question";
     	String queryQuestionCorrectAnswers = "select * from omj_final.tbl_question_correct_answers";
     	String queryQuestionAnswers = "select * from omj_final.tbl_question_answers";
     	
		try
		{
		Statement statement = connection.createStatement();
		ResultSet rsQuestion = statement.executeQuery(queryQuestion);
		GregorianCalendar creationDate = new GregorianCalendar();
		while (rsQuestion.next())
			{
				int idAuthor = rsQuestion.getInt("author");
				int idType = rsQuestion.getInt("question_type");
				creationDate.setTimeInMillis(rsQuestion.getDate("creation_date").getTime());
				questions.add(new Question(rsQuestion.getString("question"),
						rsQuestion.getString("thema"),
						rsQuestion.getInt("id"),
						teachers.stream().filter(t -> (t.getId() == idAuthor)).findFirst().get(),
						creationDate,
						questionTypes.stream().filter(t -> (t.getId() == idType)).findFirst().get().getDescription(),
						rsQuestion.getInt("study_year"))); 
			}
		
		ResultSet rsQuestionCorrectAnswers = statement.executeQuery(queryQuestionCorrectAnswers);
		while(rsQuestionCorrectAnswers.next())
			{
				int q = rsQuestionCorrectAnswers.getInt("id");
				int a = rsQuestionCorrectAnswers.getInt("correct_answer");
				QuestionAnswerUtils.bindQuestionCorrectAnswer(questions.stream().filter(t -> (t.getId() == q)).findFirst().get(), 
						answers.stream().filter(t -> (t.getId() == a)).findFirst().get());
			}
		
		ResultSet rsQuestionAnswers = statement.executeQuery(queryQuestionAnswers);
		while(rsQuestionAnswers.next())
			{
				int q = rsQuestionAnswers.getInt("question");
				int a = rsQuestionAnswers.getInt("answer");
				QuestionAnswerUtils.bindQuestionAnswer(questions.stream().filter(t -> (t.getId() == q)).findFirst().get(), 
															answers.stream().filter(t -> (t.getId() == a)).findFirst().get());
			}
		} catch (SQLException e)
		{
		e.printStackTrace();
		System.exit(1);
		}
	}
	
	private void setQuizzes()
	{
		if (quizzes == null) quizzes = new ArrayList<>();
		
     	String queryQuiz = "select * from omj_final.tbl_quiz";
     	String queryQuizQuestions = "select * from omj_final.tbl_quiz_question";
     	
     	try
		{
		Statement statement = connection.createStatement();
		ResultSet rsQuiz = statement.executeQuery(queryQuiz);
		GregorianCalendar creationDate = new GregorianCalendar();
		while (rsQuiz.next())
			{
				int idAuthor = rsQuiz.getInt("author");
				creationDate.setTimeInMillis(rsQuiz.getDate("creation_date").getTime());
				quizzes.add(new Quiz(rsQuiz.getString("thema"),
						rsQuiz.getInt("id"),
						teachers.stream().filter(t -> (t.getId() == idAuthor)).findFirst().get(),
						creationDate,
						rsQuiz.getInt("study_year"))); 
			}
		
		ResultSet rsQuizQuestions = statement.executeQuery(queryQuizQuestions);
		while(rsQuizQuestions.next())
			{
				int q = rsQuizQuestions.getInt("quiz");
				int a = rsQuizQuestions.getInt("question");
				QuizQuestionUtils.bindQuizQuiestion(quizzes.stream().filter(t -> (t.getId() == q)).findFirst().get(), 
						questions.stream().filter(t -> (t.getId() == a)).findFirst().get());
			}
		} catch (SQLException e)
		{
		e.printStackTrace();
		System.exit(1);
		}

	}
	
	private void setSolutions()
	{
		if (solutions == null) solutions = new ArrayList<>();
		
		String query1 = "select * from omj_final.tbl_quiz_solution";
		String query2 = "select * from omj_final.tbl_question_solution_answer";
		
		try
		{
		Statement statement = connection.createStatement();
		ResultSet rs1 = statement.executeQuery(query1);
		GregorianCalendar creationDate = new GregorianCalendar();
		while (rs1.next())
			{
				int idStudent = rs1.getInt("student");
				int idQuiz = rs1.getInt("quiz");
				creationDate.setTimeInMillis(rs1.getDate("date_solution").getTime());
				solutions.add(new QuizSolution(quizzes.stream().filter(q -> (q.getId() == idQuiz)).findFirst().get().getThema(),
						rs1.getInt("id"),
						students.stream().filter(s -> (s.getId() == idStudent)).findFirst().get(),
						quizzes.stream().filter(q -> (q.getId() == idQuiz)).findFirst().get(),
						creationDate,
						rs1.getInt("score"))); 
			}
		ResultSet rs2 = statement.executeQuery(query2);
		while(rs2.next())
		{
			int idSolution = rs2.getInt("quiz_solution");
			int idQuestion = rs2.getInt("question");
			Question question = questions.stream().filter(q -> (q.getId() == idQuestion)).findFirst().get();
			solutions.stream()
				.filter(s -> (s.getId() == idSolution))
				.findFirst().get().addAnswer(question, rs2.getString("student_answer"));
		}
		} catch (SQLException e)
		{
		e.printStackTrace();
		System.exit(1);
		}
	}
	
	public void setTasks()
	{
		if (tasks == null) tasks = new ArrayList<>();
		
		String query = "select * from omj_final.tbl_task";
		
		try
		{
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		GregorianCalendar creationDate = new GregorianCalendar();
		while (rs.next())
			{
				int idStudent = rs.getInt("student");
				int idQuiz = rs.getInt("quiz");
				int idTeacher = rs.getInt("author");
				creationDate.setTimeInMillis(rs.getDate("date_creation").getTime());
				tasks.add(new Task(rs.getInt("id"),
						quizzes.stream().filter(q -> (q.getId() == idQuiz)).findFirst().get(),
						students.stream().filter(s -> (s.getId() == idStudent)).findFirst().get(),
						rs.getString("task_status"),
						teachers.stream().filter(t -> (t.getId() == idTeacher)).findFirst().get(),
						quizzes.stream().filter(q -> (q.getId() == idQuiz)).findFirst().get().getThema(),
						creationDate)); 
			}
		} catch (SQLException e)
		{
		e.printStackTrace();
		System.exit(1);
		}
	}

	/**
	 * @return the questions
	 */
	public ArrayList<Question> getQuestions()
	{
		return questions;
	}

	/**
	 * Saves the data from questions array to db
	 * 
	 * @param questions
	 */
	public void saveQuestions(ArrayList<Question> questions)
	{
		this.questions = questions;
	}

	/**
	 * @return the answers
	 */
	public ArrayList<Answer> getAnswers()
	{
		return answers;
	}

	/**
	 * Saves the data from answers array to db
	 * 
	 * @param answers 
	 */
	public void saveAnswers(ArrayList<Answer> answers)
	{
		this.answers = answers;
	}

	/**
	 * 
	 * @return the quizzes
	 */
	public ArrayList<Quiz> getQuizzes()
	{
		return quizzes;
	}

	/**
	 * Saves the data from quizzes array to db
	 * 
	 * @param quizzes
	 */
	public void setQuizzes(ArrayList<Quiz> quizzes)
	{
		this.quizzes = quizzes;
	}

	/**
	 * @return the teachers
	 */
	public ArrayList<Teacher> getTeachers()
	{
		return teachers;
	}

	/**
	 * Saves the data from teachers array to db
	 * 
	 * @param teachers the teachers to set
	 */
	public void saveTeachers(ArrayList<Teacher> teachers)
	{
		this.teachers = teachers;
	}

	/**
	 * @return the students
	 */
	public ArrayList<Student> getStudents()
	{
		return students;
	}

	/**
	 * Saves the data from students array to db
	 * 
	 * @param students
	 */
	public void saveStudents(ArrayList<Student> students)
	{
		this.students = students;
	}
	
	public ArrayList<Person> getPeople()
	{
		ArrayList<Person> p = new ArrayList<>();
		teachers.stream().forEach(t -> p.add(t));
		students.stream().forEach(s -> p.add(s));
		return p;
	}
	
	/**
	 * @return the questionTypes
	 */
	public ArrayList<QuestionType> getQuestionTypes()
	{
		return questionTypes;
	}

	/**
	 * @param questionTypes the questionTypes to set
	 */
	public void saveQuestionTypes(ArrayList<QuestionType> questionTypes)
	{
		this.questionTypes = questionTypes;
	}

	
	/**
	 * @return the solutions
	 */
	public ArrayList<QuizSolution> getSolutions()
	{
		return solutions;
	}

	/**
	 * @param solutions the solutions to set
	 */
	public void saveSolutions(ArrayList<QuizSolution> solutions)
	{
		this.solutions = solutions;
	}

	public ArrayList<Task> getTasks()
	{
		return tasks;
	}

	public void saveTasks(ArrayList<Task> tasks)
	{
		this.tasks = tasks;
	}
	
	public void addTaskToDB(String student, String quiz, String status, String teacher)
	{
		Integer studentId = Integer.parseInt(student.split("=")[1]);
		Integer quizId = Integer.parseInt(quiz);
		Integer teacherId = Integer.parseInt(teacher);
		String thema = quizzes.stream().filter(q -> q.getId() == quizId).findFirst().get().getThema();
		String queryIn = "INSERT INTO omj_final.tbl_task (student, quiz, task_status, author, thema) VALUES (?, ?, ?, ?, ?);";

		try
		{
			PreparedStatement psIn = connection.prepareStatement(queryIn);
			psIn.setInt(1, studentId);
			psIn.setInt(2, quizId);
			psIn.setString(3, status);
			psIn.setInt(4, teacherId);
			psIn.setString(5, thema);
			
			psIn.execute();
		} catch (SQLException e)
		{
			e.printStackTrace();
			System.exit(1);
		}

		
		tasks = null;
		setTasks();
		
	}
	
	public void addQuizSolutionToDB(String student, String quiz, int score, String[] answers)
	{       
		Integer studentId = Integer.parseInt(student);
		Integer quizId = Integer.parseInt(quiz);
		int solutionId = -1;
		String queryIn = "INSERT INTO omj_final.tbl_quiz_solution (student, quiz, score) VALUES (?, ?, ?);";
		String queryIn2 = "INSERT INTO omj_final.tbl_question_solution_answer (quiz_solution, question, student_answer) VALUES (?, ?, ?)";
		try
		{
			PreparedStatement psIn = connection.prepareStatement(queryIn, Statement.RETURN_GENERATED_KEYS);
			psIn.setInt(1, studentId);
			psIn.setInt(2, quizId);
			psIn.setInt(3, score);
			
			psIn.execute();
			
			ResultSet generatedKeys = psIn.getGeneratedKeys();
			
	        if (generatedKeys.next()) 
	        {
	            solutionId = generatedKeys.getInt(1);
	        }
	        else 
	        {
	            throw new SQLException("Registration solution failed, no ID obtained.");
	        }
	        
	        for(int i = 0; i < answers.length; i++)
	        {
	        	int question = Integer.parseInt(answers[i].split("answer=")[0]);
	        	String answer = answers[i].split("answer=")[1];
		        PreparedStatement psIn2 = connection.prepareStatement(queryIn2);
				psIn2.setInt(1, solutionId);
				psIn2.setInt(2, question);
				psIn2.setString(3, answer);
				
				psIn2.execute();
	        }

	        
	        
		}catch (SQLException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	    
	        
	        solutions = null;
	        setSolutions();
    }
	
	public void addStudentToDB(String firstName, String lastName, String loginName, String password, String birthDate, int studyYear)
	{
		String queryIn = "INSERT INTO omj_final.tbl_person (first_name, last_name, login_name, password_field, birth_date) VALUES (?, ?, ?, ?, ?);";
		String queryIn2 = "INSERT INTO omj_final.tbl_student (id, study_year) VALUES (?, ?)";
		int id = -1;

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yy");

		
		try
		{
			Date bd = dateFormat.parse(birthDate);
			java.sql.Date bd_sql = new java.sql.Date(bd.getTime());
			
			PreparedStatement psIn = connection.prepareStatement(queryIn, Statement.RETURN_GENERATED_KEYS);
			psIn.setString(1, firstName);
			psIn.setString(2, lastName);
			psIn.setString(3, loginName);
			psIn.setString(4, password);
			psIn.setDate(5, bd_sql);
			
			psIn.execute();
			
			ResultSet generatedKeys = psIn.getGeneratedKeys();
			
	        if (generatedKeys.next()) 
	        {
	            id = generatedKeys.getInt(1);
		        PreparedStatement psIn2 = connection.prepareStatement(queryIn2);
				psIn2.setInt(1, id);
				psIn2.setInt(2, studyYear);
				
				psIn2.execute();
	        }
	        else 
	        {
	            throw new SQLException("Creating student failed, no ID obtained.");
	        }      
	        
		}catch (SQLException | ParseException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		students = null;
		teachers = null;
		setPeople();
	}

	public void saveCatalogs()
	{
		//TODO all separate save functions
		
		//TODO save all changed while closing the programm
	}
}
