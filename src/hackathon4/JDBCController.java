package hackathon4;
import java.sql.*;

public class JDBCController {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String jdbcUrl = "jdbc:mysql://google/userinfo?cloudSqlInstance=ugahack-4:us-east4:pebble&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=root&password=P3bble$password";
		Connection connection;
		connection = DriverManager.getConnection(jdbcUrl);
		return connection;
	}
}