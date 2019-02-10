package hackathon4;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
public class homeController implements Initializable {
	@FXML
	ImageView homePics = new ImageView();
	@FXML
	Button noBtn = new Button();
	@FXML
	Button yesbtn = new Button();
	@FXML
	MenuBar menuPanel = new MenuBar();
	@FXML
	Label profileViewing = new Label();
	@FXML
	Label bioText = new Label();
	@FXML
	Label locText = new Label();
	@FXML
	Label ageText = new Label();
	@FXML
	Label disTxt = new Label();


	static String currentUser;
	int currentId;
	ArrayList<Integer> blockedIds;
	@FXML
	public void nomatch() throws ClassNotFoundException, SQLException {
		Connection connection = JDBCController.getConnection();
		Statement statement = connection.createStatement();
		ResultSet set = statement.executeQuery("SELECT peopleDisliked FROM data WHERE userName = \'" + currentUser + "\';");
		String listOfDislikes = "";
		while (set.next()) {
			if(!set.getString(1).equals("none"))
			{
				listOfDislikes = set.getString(1);
				System.out.println(listOfDislikes);
			}

		}
		statement.executeUpdate("UPDATE data SET peopleDisliked = \' " + listOfDislikes + "," + profileViewing.getText()+"\';");
		nextUser();
	}
	public void matched() throws ClassNotFoundException, SQLException {
		Connection connection = JDBCController.getConnection();
		Statement statement = connection.createStatement();
		ResultSet set = statement.executeQuery("SELECT peopleLiked FROM data WHERE userName = \'" + currentUser + "\';");
		String listOfLikes = "";
		while (set.next()) {
			if(!set.getString(1).equals("none"))
			{
				listOfLikes = set.getString(1);
				System.out.println(listOfLikes);
			}

		}
		statement.executeUpdate("UPDATE data SET peopleLiked = \'" + listOfLikes +  "," + profileViewing.getText()+"\';");
		nextUser();
	}
	/**
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void nextUser() throws ClassNotFoundException, SQLException {
		Connection connection = JDBCController.getConnection();
		Statement statement = connection.createStatement();
		System.out.println(currentId);
		ResultSet set = statement.executeQuery("SELECT * FROM data WHERE id = " + currentId + ";");
		System.out.println(set);
		if(set.first()) {
			System.out.println("in if statement");
			profileViewing.setText(set.getString("userName"));
			System.out.println(set.getString("userName"));
			bioText.setText(set.getString("bio"));
			ageText.setText(String.valueOf(set.getInt("age")));
			locText.setText(set.getString("loc"));
			Image pic = new Image(set.getString("img"));
			homePics.setImage(pic);
			disTxt.setText(set.getString("disablities"));	
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		blockedIds = new ArrayList<>();
		try {
			currentId = 1;
			Connection connection = JDBCController.getConnection();
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery("SELECT id FROM data WHERE userName = \'" + currentUser + "\'");
			while(set.next()) { 
				blockedIds.add(set.getInt(1));
				set = statement.executeQuery("SELECT peopleDisliked FROM data WHERE userName = \'" + currentUser + "\'");
				set.next();
				String nonSplit = set.getString(1);
				String[] disliked = nonSplit.split(",");
				for(String s: disliked)
				{
					set = statement.executeQuery("SELECT id FROM data where userName = \'" + s + "\';");
					if(set.next()) {
						blockedIds.add(set.getInt(1));
						System.out.println(set.getInt(1));
					}
				}
				set = statement.executeQuery("SELECT peopleLiked FROM data WHERE userName = \'" + currentUser + "\'");
				set.next();
				String nonSplit2 = set.getString(1);
				String[] liked = nonSplit2.split(",");
				for(String s: liked)
				{
					set = statement.executeQuery("SELECT id FROM data where userName = \'" + s + "\';");
					if (set.next()) {
						blockedIds.add(set.getInt(1));
						System.out.println(set.getInt(1));
					}

				}
				currentId = 1;
				set = statement.executeQuery("SELECT id FROM data ORDER BY id DESC LIMIT 1;");
				set.next();
				int maxId = set.getInt(1);
				while (blockedIds.indexOf(currentId) != -1) {
					if (currentId > maxId) {
						currentId = 1;
					}
					currentId++;
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


}
