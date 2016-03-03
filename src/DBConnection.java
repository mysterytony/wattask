public interface DBConnection
{
	
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	// constants for red hat server (rhcloud)
//	public static final String HOST = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
//	public static final String PORT = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
//	public static final String USER = "adminHPZrVtR";
//	public static final String PASS = "HBGU3QGv8jRL";
	
	// test on local:
	public static final String HOST = "localhost";
	public static final String PORT = "3306";
	public static final String USER = "root";
	public static final String PASS = "lihenantom001";
}
