package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ContrForLogin {
	@FXML
	private TextField tfLogin;
	@FXML
	private PasswordField tfPassword;
	@FXML
	private Button bLogin;
	@FXML
	private Label lDeny;
	public static int level_accept = 0;
	private Database db = new Database();
	@FXML
	private void initialize() {
		lDeny.setVisible(false);
	}
	
	@FXML
	private void Log_In() throws IOException {
		if (db.login(tfLogin.getText(), tfPassword.getText())) {
			Stage stage = (Stage) bLogin.getScene().getWindow();
		    stage.close();
			Stage primaryStage = new Stage();
			AnchorPane root = new AnchorPane();
			
			root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
			
			Scene scene = new Scene(root,1000,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Technic");
			primaryStage.show();
			
		}
		else {
			lDeny.setVisible(true);
		}
	}

}
