package application;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class SampleController {
	//Создаем атрибуты элементов сцены
	@FXML private TableView<Tech> tvTech;
	@FXML private TableColumn<Tech, Integer> tcIdTech;
	@FXML private TableColumn<Tech, String> tcName;
	@FXML private TableColumn<Tech, String> tcModel;
	@FXML private TableColumn<Tech, Date> tcDate;
	@FXML private TableColumn<Tech, Float> tcCost;
	@FXML private TableColumn<Tech, Integer> tcRoomNum;
	@FXML private TableColumn<Tech, Integer> tcMatRespID;
	@FXML private TableColumn<Tech, Integer> tcSubDivID;
	
	@FXML private Label lIdTech;
	@FXML private Label lName;
	@FXML private Label lModel;
	@FXML private Label lDate;
	@FXML private Label lCost;
	@FXML private Label lRoomNum;
	@FXML private Label lMatRespID;
	@FXML private Label lSubDivID;
	@FXML private TextField tfIdTech;
	@FXML private TextField tfName;
	@FXML private TextField tfModel;
	@FXML private TextField tfDate;
	@FXML private TextField tfCost;
	@FXML private TextField tfRoomNum;
	@FXML private ObservableList<String> cblMRID;
	@FXML private ObservableList<String> cblSID;
	@FXML private ComboBox<String> cbMRID = new ComboBox<String>();
	@FXML private ComboBox<String> cbSID = new ComboBox<String>();
	@FXML private Button bNew;
	@FXML private Button bEdit;
	@FXML private Button bDelete;
	@FXML private Button newW;
	private Database db = new Database();
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
	//инициализация
	@FXML
	private void initialize() {
		cblMRID = FXCollections.observableArrayList(db.listAllWork());
		cbMRID.setItems(cblMRID);
		cblSID = FXCollections.observableArrayList(db.listAllSubd());
		cbSID.setItems(cblSID);
		cbMRID.setValue(cblMRID.get(0));
		cbSID.setValue(cblSID.get(0));
		tcIdTech.setCellValueFactory(new PropertyValueFactory<Tech, Integer>("idtechnic")); //1 столбик
		tcName.setCellValueFactory(new PropertyValueFactory<Tech, String>("name")); //2 столбик
		tcModel.setCellValueFactory(new PropertyValueFactory<Tech, String>("model")); //3 столбик
		tcDate.setCellValueFactory(new PropertyValueFactory<Tech, Date>("date")); //4 столбик
		tcCost.setCellValueFactory(new PropertyValueFactory<Tech, Float>("cost")); //5 столбик
		tcRoomNum.setCellValueFactory(new PropertyValueFactory<Tech, Integer>("room_num")); //6 столбик
		tcMatRespID.setCellValueFactory(new PropertyValueFactory<Tech, Integer>("mat_resp_id")); //7 столбик
		tcSubDivID.setCellValueFactory(new PropertyValueFactory<Tech, Integer>("subdividion_id")); //8 столбик
		
		tvTech.setItems(FXCollections.observableArrayList(db.getAllTech())); //инициализация динамического массива из элементов Tech и передача соответствующей информации в столбики
		
		tvTech.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showTechDetails(newValue)); //Прослушиватель нажатия на табличку
	}
	//метод интерпритирования выбранного элемента таблицы по определенным ячейкам
	private void showTechDetails(Tech cl) {
		if (cl != null) {
			tfIdTech.setText(Integer.toString(cl.getIdtechnic()));
			tfName.setText(cl.getName());
			tfModel.setText(cl.getModel());
			tfDate.setText(cl.getDate().toString());
			tfCost.setText(Float.toString(cl.getCost()));
			tfRoomNum.setText(Integer.toString(cl.getRoom_num()));
			cbMRID.setValue(cblMRID.get(cl.getMat_resp_id()-1));
			cbSID.setValue(cblSID.get(cl.getSubdividion_id()-1));
			
		} else {
			tfIdTech.setText("");
			tfName.setText("");
			tfModel.setText("");
			tfDate.setText("");
			tfCost.setText("");
			tfRoomNum.setText("");
			cbMRID.setValue(cblMRID.get(0));
			cbSID.setValue(cblSID.get(0));
		}
	}
	
	//добавить новую запись в таблицу
	@FXML
	private void handleNew() {
		if (isInputValid(1)) {
			db.newTech(tfName.getText(), tfModel.getText(), Date.valueOf(tfDate.getText()), Float.parseFloat(tfCost.getText()), 
					Integer.parseInt(tfRoomNum.getText()), Integer.parseInt(cbMRID.getValue().substring(0, cbMRID.getValue().indexOf(" "))), 
					Integer.parseInt(cbSID.getValue().substring(0, cbSID.getValue().indexOf(" "))));
			tvTech.setItems(FXCollections.observableArrayList(db.getAllTech()));
		}
	}
	//удалить строчку с id n 
	@FXML
	private void handleDel() {
		if (isInputValid(2)) {
			db.delTech(Integer.parseInt(tfIdTech.getText()));
			tvTech.setItems(FXCollections.observableArrayList(db.getAllTech()));
		}
	}
	  //обновить строку с id n на параметры
	  
	@FXML
	private void handleUpd() {
		if (isInputValid(3)) {
			db.updTech(Integer.parseInt(tfIdTech.getText()), tfName.getText(), tfModel.getText(), Date.valueOf(tfDate.getText()), Float.parseFloat(tfCost.getText()), 
					Integer.parseInt(tfRoomNum.getText()), Integer.parseInt(cbMRID.getValue().substring(0, cbMRID.getValue().indexOf(" "))), 
					Integer.parseInt(cbSID.getValue().substring(0, cbSID.getValue().indexOf(" "))));
			tvTech.setItems(FXCollections.observableArrayList(db.getAllTech()));
		}
	}
	 
	// проверка корректности ввода данных 
	private boolean isInputValid(int i) {
		String errorMessage = "";
		if (i == 1) {
			if (tfName.getText() == null || tfName.getText().length() == 0) {
				errorMessage += "No valid name!\n";
			}
			if (tfModel.getText() == null || tfModel.getText().length() == 0) {
				errorMessage += "No valid model!\n";
			}
			if (tfDate.getText() == null || tfDate.getText().length() == 0) {
				errorMessage += "No valid birthday!\n";
			} else {
				try {
					df.parse(tfDate.getText()).getTime();
				} catch (ParseException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
				} catch (IllegalArgumentException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
				}
			}
			if (tfCost.getText() == null || tfCost.getText().length() == 0) {
				errorMessage += "No valid cost!\n";
			}
			else {
				try {
					Float.parseFloat(tfCost.getText());
				}
				catch (NumberFormatException e) {
					errorMessage += "This is not number!\n";
				}
			}
			if (tfRoomNum.getText() == null || tfRoomNum.getText().length() == 0) {
				errorMessage += "No valid room number!\n";
			}
			else {
				try {
					Integer.parseInt(tfRoomNum.getText());
				}
				catch (NumberFormatException e) {
					errorMessage += "This is not number!\n";
				}
			}
			
		}
		if (i == 2) {
			if (tfIdTech.getText() == null || tfIdTech.getText().length() == 0) {
				errorMessage += "No valid ID!\n";
			} else {
				try {
					Integer.parseInt(tfIdTech.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
		}
		if (i == 3) {
			if (tfIdTech.getText() == null || tfIdTech.getText().length() == 0) {
				errorMessage += "No valid ID!\n";
			} else {
				try {
					Integer.parseInt(tfIdTech.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
			if (tfName.getText() == null || tfName.getText().length() == 0) {
				errorMessage += "No valid first name!\n";
			}
			if (tfModel.getText() == null || tfModel.getText().length() == 0) {
				errorMessage += "No valid last name!\n";
			}
			if (tfDate.getText() == null || tfDate.getText().length() == 0) {
				errorMessage += "No valid birthday!\n";
			} else {
				try {
					df.parse(tfDate.getText()).getTime();
				} catch (ParseException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
				}
			}
			if (tfCost.getText() == null || tfCost.getText().length() == 0) {
				errorMessage += "No valid cost!\n";
			}
			else {
				try {
					Float.parseFloat(tfCost.getText());
				}
				catch (NumberFormatException e) {
					errorMessage += "This is not number!\n";
				}
			}
			if (tfRoomNum.getText() == null || tfRoomNum.getText().length() == 0) {
				errorMessage += "No valid room number!\n";
			}
			else {
				try {
					Integer.parseInt(tfRoomNum.getText());
				}
				catch (NumberFormatException e) {
					errorMessage += "This is not number!\n";
				}
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
	 
}