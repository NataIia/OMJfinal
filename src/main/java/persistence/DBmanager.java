package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBmanager
{
	private static Connection connection = null;
	
	public DBmanager()
	{	
		if (connection == null)
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/omj_final",	"root", "root");
			} catch (SQLException | ClassNotFoundException e)
			{
				System.out.println(e);
				e.printStackTrace();
			}
	}
	
	public static Connection getConnection()
	{
		return connection;
	}
}
