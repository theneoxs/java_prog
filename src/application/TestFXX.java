package application;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;

import java.io.IOException;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TestFXX extends ApplicationTest{
	@Override
	public void start(Stage stage) throws IOException{
		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
		Scene scene = new Scene(root,1000,400);
		stage.setScene(scene);
		stage.show();
	}
	
	@Test
	public void inputName() {
		clickOn("#tfName");
		write("MacBook");
		verifyThat("#tfName", hasText("MacBook"));
	}

	@Test
	public void inputModel() {
		clickOn("#tfModel");
		write("Apple");
		verifyThat("#tfModel", hasText("Apple"));
	}

	@Test
	public void inputDate() {
		clickOn("#tfDate");
		write("2017-12-12");
		verifyThat("#tfDate", hasText("2017-12-12"));
	}
	@Test
	public void TestClick() {
		moveTo("Sadsa").clickOn();
		verifyThat("#tfIdTech", hasText("1"));
	}
	
}
