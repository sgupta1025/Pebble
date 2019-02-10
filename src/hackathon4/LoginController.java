package hackathon4;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
public class LoginController implements Initializable {

	@FXML
	TextField userField = new TextField();
	@FXML
	PasswordField passField = new PasswordField();
	@FXML
	Hyperlink createAccountBtn = new Hyperlink();
	@FXML
	Button loginButton;
	@FXML
	ImageView banner = new ImageView();
	Stage mainStage = Main.stg;
	public void logIn() throws ClassNotFoundException, SQLException {
		Connection connection = JDBCController.getConnection();
		Statement statement = connection.createStatement();
		ResultSet set = statement.executeQuery("SELECT userName, password FROM data");
		boolean matchingIdFound = false;
		while(set.next())
		{
			String userId = set.getString(1);
			String pass = set.getString(2);
			if(userField.getText().equals(userId) && passField.getText().equals(pass))
			{
				matchingIdFound = true;
				break;
			}
		}
		if(matchingIdFound){
				try {
					Scene newScene = new Scene(FXMLLoader.load(getClass().getResource("Home.fxml")));
					mainStage.setScene(newScene);
					homeController.currentUser = userField.getText();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		}
			else {
				Alert diAlert = new Alert(AlertType.ERROR);
				diAlert.setTitle("An error occured.");
				diAlert.setContentText("Your username or password is incorrect.");
				diAlert.showAndWait();
			}
	
	}
	
	@FXML
	public void createUser() {
		
			try {
				Scene newScene = new Scene(FXMLLoader.load(getClass().getResource("SignUp.fxml")));
				mainStage.setScene(newScene);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image image = new Image(getClass().getResourceAsStream("Banner.png"));
		banner.setImage(image);
		banner.setFitHeight(300);
		banner.setFitWidth(500);
		banner.setX(0);
		banner.setY(0);
		banner.setPreserveRatio(true);
	}
	
}


