import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User implements DBConnection
{
	String	username;
	String	password;
			
	public String getEncryptedPass()
	{
		return "" + password.hashCode();
	}
	
	public User(String u, String p)
	{
		username = u;
		password = p;
	}
	
	public static boolean addUserToDB(User user) throws Exception
	{
		try
		{
			String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + "wattask";
			
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			
			String checkingSQL = "SELECT username FROM tableUser WHERE username=?";
			PreparedStatement checkStmt = conn.prepareStatement(checkingSQL);
			checkStmt.setString(1, user.username);
			ResultSet rs = checkStmt.executeQuery();
			checkStmt.close();
			if (rs.next())
			{
				return false; // user name already exist
			}
			
			String sql = "INSERT INTO tableUser (username, pass) values (?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.username);
			stmt.setString(2, user.getEncryptedPass());
			
			stmt.executeQuery(sql);
			
			stmt.close();
			conn.close();
			return true;
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	public static User getUserFromDB(String username, String pass) throws Exception
	{
		try
		{
			String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + "wattask";
			
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			
			String sql = "SELECT id, username, pass FROM tableUser WHERE username=?, pass=?";
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, pass);
			
			ResultSet rs = preparedStmt.executeQuery(sql);
			
			 while (rs.next())
			 {
			 String name = rs.getString("username");
			 String pwd = rs.getString("pass");
			return new User(name, pwd);
			 }
			preparedStmt.close();
			conn.close();
			
			throw new Exception("No user found");
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
