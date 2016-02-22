public interface DBConnection
{
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String HOST = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
	public static final String PORT = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
	public static final String USER = "adminHPZrVtR";
	public static final String PASS = "HBGU3QGv8jRL";
}
