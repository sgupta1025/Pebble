package hackathon4;

import java.sql.*;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	static String img;
	static Stage stg;
	@Override
	public void start(Stage stage) throws Exception {
		stg = stage;
		Pane root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(root, 400, 400);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {

		/*try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//System.out.println("SUCCESS");
		}
		catch(ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			System.exit(1);
		}
		String jdbcUrl = "jdbc:mysql://google/userinfo?cloudSqlInstance=ugahack-4:us-east4:pebble&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=root&password=P3bble$password";

		//System.out.println(jdbcUrl);
		Connection connection;
		try {
			connection = DriverManager.getConnection(jdbcUrl);
			String test = "SELECT * FROM data";
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery(test);
			while (set.next()) {
				String userName = set.getString("userName");
				String bio = set.getString("bio");
				int age = set.getInt("age");
				String location = set.getString("loc");
				img = set.getString("img");
				String contactInfo = set.getString("contact");
				String gender = set.getString("gender");
				String likes = set.getString("peopleLiked");
				String dislikes = set.getString("peopleDisliked");
				System.out.print("USER: " + userName + ", ");
				System.out.print("BIO: " + bio + ", ");
				System.out.print("AGE: " + age + ", ");
				System.out.print("LOCATION: " + location + ", ");
				System.out.print("IMAGE: " + img + ", ");
				System.out.print("CONTACT INFO: " + contactInfo + ", ");
				System.out.print("GENDER: " + gender + ", ");
				System.out.print("LIKES: " + likes + ", ");
				System.out.println("DISLIKES: " + dislikes + ", ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		launch(args);


	}
}
