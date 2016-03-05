
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WatTask
 */
//@WebServlet("/WatTask")
public class WatTask extends HttpServlet implements DBConnection
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WatTask()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException
	{
		// TODO Auto-generated method stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		
		PrintWriter printer = response.getWriter();
		printer.print(WebContent.getHeader());
		
		String DBNAME = "wattask";
		String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DBNAME;
		
		try
		{
			
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			
			printer.println("connection successful</br>");
			
			Statement stmt = conn.createStatement();
			String sql;
			sql = "SELECT id, pass FROM tableUser";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				int id = rs.getInt("id");
				String pass = rs.getString("pass");
				
				printer.print("<p>ID: " + id + ", Pass: " + pass + "</p>");
				
			}
			
			rs.close();
			stmt.close();
			conn.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
