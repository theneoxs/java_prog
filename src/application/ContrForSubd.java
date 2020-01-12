package application;

import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class ContrForSubd {
	//Создаем атрибуты элементов сцены
	@FXML private TableView<Subd> tvSubd;
	@FXML private TableColumn<Subd, Integer> tcIdSubd;
	@FXML private TableColumn<Subd, String> tcFullName;
	@FXML private TableColumn<Subd, String> tcShortName;
	
	@FXML private Label lIdSubd;
	@FXML private Label lFullName;
	@FXML private Label lShortName;
	@FXML private TextField tfIdSubd;
	@FXML private TextField tfFullName;
	@FXML private TextField tfShortName;
	@FXML private Button bNew;
	@FXML private Button bEdit;
	@FXML private Button bDelete;
	private Database db = new Database();
	
	//инициализация
	@FXML
	private void initialize() {
		tcIdSubd.setCellValueFactory(new PropertyValueFactory<Subd, Integer>("idsubdividion")); //1 столбик
		tcFullName.setCellValueFactory(new PropertyValueFactory<Subd, String>("full_name")); //2 столбик
		tcShortName.setCellValueFactory(new PropertyValueFactory<Subd, String>("short_name")); //3 столбик
		
		tvSubd.setItems(FXCollections.observableArrayList(db.getAllSubd())); //инициализация динамического массива из элементов Subd и передача соответствующей информации в столбики
		
		tvSubd.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showSubdDetails(newValue)); //Прослушиватель нажатия на табличку
	}
	//метод интерпритирования выбранного элемента таблицы по определенным ячейкам
	private void showSubdDetails(Subd cl) {
		if (cl != null) {
			tfIdSubd.setText(Integer.toString(cl.getIdsubdividion()));
			tfFullName.setText(cl.getFull_name());
			tfShortName.setText(cl.getShort_name());
		} else {
			tfIdSubd.setText("");
			tfFullName.setText("");
			tfShortName.setText("");
		}
	}
	
	//добавить новую запись в таблицу
	@FXML
	private void handleNew() {
		if (isInputValid(1)) {
			db.newSubd(tfFullName.getText(), tfShortName.getText());
			tvSubd.setItems(FXCollections.observableArrayList(db.getAllSubd()));
		}
	}
	//удалить строчку с id n 
	@FXML
	private void handleDel() {
		if (isInputValid(2)) {
			db.delSubd(Integer.parseInt(tfIdSubd.getText()));
			tvSubd.setItems(FXCollections.observableArrayList(db.getAllSubd()));
		}
	}
	  //обновить строку с id n на параметры
	  
	@FXML
	private void handleUpd() {
		if (isInputValid(3)) {
			db.updSubd(Integer.parseInt(tfIdSubd.getText()), tfFullName.getText(), tfShortName.getText());
			tvSubd.setItems(FXCollections.observableArrayList(db.getAllSubd()));
		}
	}
	 
	// проверка корректности ввода данных 
	private boolean isInputValid(int i) {
		String errorMessage = "";
		if (i == 1) {
			if (tfIdSubd.getText() == null || tfIdSubd.getText().length() == 0) {
				errorMessage += "No valid ID!\n";
			} else {
				try {
					Integer.parseInt(tfIdSubd.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
		} else if (i == 2) {
			if (tfFullName.getText() == null || tfFullName.getText().length() == 0) {
				errorMessage += "No valid full name!\n";
			}
			if (tfShortName.getText() == null || tfShortName.getText().length() == 0) {
				errorMessage += "No valid short name!\n";
			}
		}
		else if (i == 3) {
			if (tfIdSubd.getText() == null || tfIdSubd.getText().length() == 0) {
				errorMessage += "No valid ID!\n";
			} else {
				try {
					Integer.parseInt(tfIdSubd.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
			if (tfFullName.getText() == null || tfFullName.getText().length() == 0) {
				errorMessage += "No valid full name!\n";
			}
			if (tfShortName.getText() == null || tfShortName.getText().length() == 0) {
				errorMessage += "No valid short name!\n";
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