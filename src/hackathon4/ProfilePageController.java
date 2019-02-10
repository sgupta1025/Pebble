package hackathon4;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
public class ProfilePageController implements Initializable {
	static String email;
	static String password;
	static String username;
	File selectedFile;
	@FXML
	TextArea fieldinterests = new TextArea();
	@FXML
	TextField fieldAge = new TextField();
	@FXML
	TextField fieldLocation = new TextField();
	@FXML
	Text locationInfo = new Text();
	@FXML
	Text interestsInfo = new Text();
	@FXML
	Button submitButton = new Button();
	@FXML
	ComboBox<String> genderBox = new ComboBox<>();
	@FXML
	TextArea disablities = new TextArea();
	@FXML 
	TextField contactInfo = new TextField();
	@FXML
	ImageView addProfileimage;
	@FXML
	public void makeProfile() throws FileNotFoundException {
		String gender = genderBox.getValue();
		String sqlStatement = "INSERT INTO data (userName, bio, age, loc, img, contact, gender, peopleLiked, peopleDisliked, disablities, password)  "
				+"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = JDBCController.getConnection().prepareStatement(sqlStatement);
			statement.setString(1, username);
			statement.setString(2, fieldinterests.getText());
			statement.setInt(3, Integer.parseInt(fieldAge.getText()));
			statement.setString(4, locationInfo.getText());
			statement.setString(5, selectedFile.toURI().toString());
			statement.setString(6, contactInfo.getText());
			statement.setString(7, gender);
			statement.setString(8, "none");
			statement.setString(9, "none");
			statement.setString(10, disablities.getText());
			statement.setString(11, password);
			statement.executeUpdate();
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Stage mainStage = Main.stg;
		try {
			homeController.currentUser =username;
			Scene newScene = new Scene(FXMLLoader.load(getClass().getResource("Home.fxml")));
			mainStage.setScene(newScene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Image image = new Image(getClass().getResourceAsStream("profile pic.png"));
		addProfileimage.setImage(image);
		genderBox.getItems().addAll("MALE","FEMALE","OTHER");
		fieldAge.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
				if(!newVal.matches("\\*d"))
				{
					fieldAge.setText(newVal.replaceAll("[^\\d]", ""));
				}

			}
		});
		contactInfo.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
				if(!newVal.matches("\\*d"))
				{
					contactInfo.setText(newVal.replaceAll("[^\\d]", ""));
				}

			}
		});
	}
	@FXML
	public void addImage() {
		//open file explorer; get jpg/png image
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Image Files", "*.png", "*.jpg"));
		Stage mainStage = (Stage) submitButton.getScene().getWindow();
		 selectedFile = fileChooser.showOpenDialog(mainStage);
		if (selectedFile != null) {
			addProfileimage.setImage(new Image(selectedFile.toURI().toString()));
		}


	}

}
