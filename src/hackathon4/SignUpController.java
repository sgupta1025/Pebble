package hackathon4;

import java.io.IOException;
import java.net.URL;
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
public class SignUpController implements Initializable {
	@FXML
	TextField setEmail = new TextField();
	@FXML
	PasswordField password = new PasswordField();
	@FXML
	TextField setUser = new TextField();
	@FXML
	Button newAccount = new Button();
	@FXML
	ImageView image = new ImageView();
	@FXML
	PasswordField passConfirm = new PasswordField();
	@FXML
	public void makeNewAcc() {
		if(password.getText().equals(passConfirm.getText())) {
			ProfilePageController.email = setEmail.getText();
			ProfilePageController.password = passConfirm.getText();
			ProfilePageController.username = setUser.getText();
			Stage mainStage = Main.stg;
			try {
				Scene newScene = new Scene(FXMLLoader.load(getClass().getResource("MakeProfilePage.fxml")));
				mainStage.setScene(newScene);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			Alert diAlert = new Alert(AlertType.ERROR);
			diAlert.setTitle("An error occured.");
			diAlert.setContentText("Your passwords did not miss");
			diAlert.showAndWait();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image logo = new Image(getClass().getResourceAsStream("Pebble.png"));
		image.setImage(logo);
		
	}
	
}
