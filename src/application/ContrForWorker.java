package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class ContrForWorker {
	//������� �������� ��������� �����
	@FXML private TableView<Worker> tvWorker;
	@FXML private TableColumn<Worker, Integer> tcIdWorker;
	@FXML private TableColumn<Worker, String> tcName;
	@FXML private TableColumn<Worker, String> tcSurname;
	@FXML private TableColumn<Worker, String> tcPosition;
	@FXML private TableColumn<Worker, Integer> tcExperience;
	@FXML private TableColumn<Worker, String> tcLogin;
	@FXML private TableColumn<Worker, String> tcPassword;
	@FXML private TableColumn<Worker, Integer> tcSubDivID;
	
	@FXML private Label lIdWorker;
	@FXML private Label lName;
	@FXML private Label lSurname;
	@FXML private Label lPosition;
	@FXML private Label lExp;
	@FXML private Label lLogin;
	@FXML private Label lPass;
	@FXML private Label lSubDivID;
	@FXML private TextField tfIdWorker;
	@FXML private TextField tfName;
	@FXML private TextField tfSurname;
	@FXML private TextField tfPosition;
	@FXML private TextField tfExperience;
	@FXML private TextField tfLogin;
	@FXML private TextField tfPassword;
	@FXML private ObservableList<String> cblSID;
	@FXML private ComboBox<String> cbSID = new ComboBox<String>();
	@FXML private Button bNew;
	@FXML private Button bEdit;
	@FXML private Button bDelete;
	@FXML private Button bTr;
	@FXML private Button bSubd;
	private Database db = new Database();
	
	//�������������
	@FXML
	private void initialize() throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        lvl.close();
        tfIdWorker.setEditable(false);
        tfLogin.setEditable(false);
		bTr.setVisible(false);
		bSubd.setVisible(false);
		tcLogin.setVisible(false);
		tcPassword.setVisible(false);
		lLogin.setVisible(false);
		lPass.setVisible(false);
		tfLogin.setVisible(false);
		tfPassword.setVisible(false);
		
		if (level_accept.equals("Subvisor")) {
			bTr.setVisible(true);
			bSubd.setVisible(true);
		}
		else if (level_accept.equals("Administrator")) {
			tcLogin.setVisible(true);
			tcPassword.setVisible(true);
			lLogin.setVisible(true);
			lPass.setVisible(true);
			tfName.setEditable(false);
			tfSurname.setEditable(false);
			tfPosition.setEditable(false);
			tfExperience.setEditable(false);
			tfLogin.setVisible(true);
			tfPassword.setVisible(true);
		}
		else if (level_accept.equals("root")) {
			tcLogin.setVisible(true);
			tcPassword.setVisible(true);
			lLogin.setVisible(true);
			lPass.setVisible(true);
			tfLogin.setVisible(true);
			tfPassword.setVisible(true);
		}
		cblSID = FXCollections.observableArrayList(db.listAllSubd());
		cbSID.setItems(cblSID);
		cbSID.setValue(cblSID.get(0));
		tcIdWorker.setCellValueFactory(new PropertyValueFactory<Worker, Integer>("idworker")); //1 �������
		tcName.setCellValueFactory(new PropertyValueFactory<Worker, String>("name")); //2 �������
		tcSurname.setCellValueFactory(new PropertyValueFactory<Worker, String>("surname")); //3 �������
		tcPosition.setCellValueFactory(new PropertyValueFactory<Worker, String>("position")); //4 �������
		tcExperience.setCellValueFactory(new PropertyValueFactory<Worker, Integer>("experience")); //5 �������
		tcLogin.setCellValueFactory(new PropertyValueFactory<Worker, String>("login")); //6 �������
		tcPassword.setCellValueFactory(new PropertyValueFactory<Worker, String>("password")); //7 �������
		tcSubDivID.setCellValueFactory(new PropertyValueFactory<Worker, Integer>("subdividion_id")); //8 �������
		tvWorker.setItems(FXCollections.observableArrayList(db.getAllWorker())); //������������� ������������� ������� �� ��������� Tech � �������� ��������������� ���������� � ��������
		
		tvWorker.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			try {
				showWorkerDetails(newValue);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}); //�������������� ������� �� ��������
	}
	//����� ����������������� ���������� �������� ������� �� ������������ �������
	private void showWorkerDetails(Worker cl) throws IOException{
		if (cl != null) {
			tfIdWorker.setText(Integer.toString(cl.getIdworker()));
			tfName.setText(cl.getName());
			tfSurname.setText(cl.getSurname());
			tfPosition.setText(cl.getPosition());
			tfExperience.setText(Integer.toString(cl.getExperience()));
			tfLogin.setText(cl.getLogin());
			tfPassword.setText(cl.getPassword());
			cbSID.setValue(db.getSubd(cl.getSubdividion_id()));
			
		} else {
			tfIdWorker.setText("");
			tfName.setText("");
			tfSurname.setText("");
			tfPosition.setText("");
			tfExperience.setText("");
			tfLogin.setText("");
			tfPassword.setText("");
			cbSID.setValue(cblSID.get(0));
		}
	}
	
	//�������� ����� ������ � �������
	@FXML
	private void handleNew() throws Exception {
		if (isInputValid(1)) {
			db.newWorker(tfName.getText(), tfSurname.getText(), tfPosition.getText(), Integer.parseInt(tfExperience.getText()), 
					tfLogin.getText(), tfPassword.getText(),
					Integer.parseInt(cbSID.getValue().substring(0, cbSID.getValue().indexOf(" "))));
			tvWorker.setItems(FXCollections.observableArrayList(db.getAllWorker()));
			
		}
	}
	//������� ������� � id n 
	@FXML
	private void handleDel() throws Exception {
		if (isInputValid(2)) {
			db.delWorker(Integer.parseInt(tfIdWorker.getText()), tfLogin.getText());
			tvWorker.setItems(FXCollections.observableArrayList(db.getAllWorker()));
		}
	}
	  //�������� ������ � id n �� ���������
	  
	@FXML
	private void handleUpd() throws Exception {
		if (isInputValid(3)) {
			db.updWorker(Integer.parseInt(tfIdWorker.getText()), tfName.getText(), tfSurname.getText(), tfPosition.getText(), Integer.parseInt(tfExperience.getText()), 
					tfLogin.getText(), tfPassword.getText(),
					Integer.parseInt(cbSID.getValue().substring(0, cbSID.getValue().indexOf(" "))));
			tvWorker.setItems(FXCollections.observableArrayList(db.getAllWorker()));
		}
	}
	 
	// �������� ������������ ����� ������ 
	private boolean isInputValid(int i) {
		String errorMessage = "";
		if (i == 1) {
			if (tfName.getText() == null || tfName.getText().length() == 0) {
				errorMessage += "No valid name!\n";
			}
			if (tfSurname.getText() == null || tfSurname.getText().length() == 0) {
				errorMessage += "No valid surname!\n";
			}
			if (tfPosition.getText() == null || tfPosition.getText().length() == 0) {
				errorMessage += "No valid position!\n";
			}
			if (tfExperience.getText() == null || tfExperience.getText().length() == 0) {
				errorMessage += "No valid Experience!\n";
			}
			else {
				try {
					Integer.parseInt(tfExperience.getText());
				}
				catch (NumberFormatException e) {
					errorMessage += "Experience is not number!\n";
				}
			}
			if (tfLogin.getText() == null || tfLogin.getText().length() == 0) {
				tfLogin.setText(tfName.getText()+"_"+tfSurname.getText());
			}
			if (tfPassword.getText() == null || tfPassword.getText().length() == 0) {
				tfPassword.setText("***");
			}
			
		}
		if (i == 2) {
			if (tfIdWorker.getText() == null || tfIdWorker.getText().length() == 0) {
				errorMessage += "No valid ID!\n";
			} else {
				try {
					Integer.parseInt(tfIdWorker.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
		}
		if (i == 3) {
			if (tfIdWorker.getText() == null || tfIdWorker.getText().length() == 0) {
				errorMessage += "No valid ID!\n";
			} else {
				try {
					Integer.parseInt(tfIdWorker.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
			if (tfName.getText() == null || tfName.getText().length() == 0) {
				errorMessage += "No valid name!\n";
			}
			if (tfSurname.getText() == null || tfSurname.getText().length() == 0) {
				errorMessage += "No valid surname!\n";
			}
			if (tfPosition.getText() == null || tfPosition.getText().length() == 0) {
				errorMessage += "No valid position!\n";
			}
			if (tfExperience.getText() == null || tfExperience.getText().length() == 0) {
				errorMessage += "No valid Experience!\n";
			}
			else {
				try {
					Integer.parseInt(tfExperience.getText());
				}
				catch (NumberFormatException e) {
					errorMessage += "Experience is not number!\n";
				}
			}
			if (tfLogin.getText() == null || tfLogin.getText().length() == 0) {
				tfLogin.setText(tfName.getText()+"_"+tfSurname.getText());
			}
			if (tfPassword.getText() == null || tfPassword.getText().length() == 0) {
				tfPassword.setText("***");
			}
		}
	
		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	}
	@FXML
	private void winSubd() throws IOException {
		Stage primaryStage = new Stage();
		AnchorPane root = new AnchorPane();
		
		root = FXMLLoader.load(getClass().getResource("Subdividion.fxml"));
		
		Scene scene = new Scene(root,1000,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Subdividion");
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		    	try {
					cblSID = FXCollections.observableArrayList(db.listAllSubd());
				} catch (IOException e) {
					e.printStackTrace();
				}
		    	cbSID.setItems(cblSID);
		    	cbSID.setValue(cblSID.get(0));

		    }
		});
		
	}
	@FXML
	private void winTr() throws IOException {
		Stage primaryStage = new Stage();
		AnchorPane root = new AnchorPane();
		
		root = FXMLLoader.load(getClass().getResource("Transportation.fxml"));
		
		Scene scene = new Scene(root,1000,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Transportation");
		primaryStage.show();
	}
	
}