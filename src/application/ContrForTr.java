package application;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class ContrForTr {
	//Создаем атрибуты элементов сцены
	@FXML private TableView<Transportation> tvTr;
	@FXML private TableColumn<Transportation, Integer> tcIdTr;
	@FXML private TableColumn<Transportation, Date> tcDate_Tr;
	@FXML private TableColumn<Transportation, String> tcStatus;
	@FXML private TableColumn<Transportation, Integer> tcnew_mat_resp;
	@FXML private TableColumn<Transportation, Integer> tctechnic_id;
	@FXML private TableColumn<Transportation, Integer> tcnew_subdividion_id;
	
	@FXML private Label lIdTr;
	@FXML private Label lDate_Tr;
	@FXML private Label lStatus;
	@FXML private Label lnew_mat_resp;
	@FXML private Label ltechnic_id;
	@FXML private Label lnew_subdividion_id;
	@FXML private TextField tfIdTr;
	@FXML private TextField tfDate_Tr;
	@FXML private ObservableList<String> cblStatus;
	@FXML private ObservableList<String> cblNew_mat_resp;
	@FXML private ObservableList<String> cbltechnic_id;
	@FXML private ObservableList<String> cblnew_subdividion_id;
	@FXML private ComboBox<String> cbStatus = new ComboBox<String>();
	@FXML private ComboBox<String> cbNew_mat_resp = new ComboBox<String>();
	@FXML private ComboBox<String> cbtechnic_id = new ComboBox<String>();
	@FXML private ComboBox<String> cbnew_subdividion_id = new ComboBox<String>();
	@FXML private Button bNew;
	@FXML private Button bEdit;
	@FXML private Button bDelete;
	private Database db = new Database();
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
	
	//инициализация
	@FXML
	private void initialize() {
		cblNew_mat_resp = FXCollections.observableArrayList(db.listAllWork());
		cbltechnic_id = FXCollections.observableArrayList(db.listAllTech());
		cblnew_subdividion_id = FXCollections.observableArrayList(db.listAllSubd());
		cbNew_mat_resp.setItems(cblNew_mat_resp);
		cbtechnic_id.setItems(cbltechnic_id);
		cbnew_subdividion_id.setItems(cblnew_subdividion_id);
		cblStatus = FXCollections.observableArrayList("In Process", "Complete", "Failure");
		cbStatus.setItems(cblStatus);
		cbNew_mat_resp.setValue(cblNew_mat_resp.get(0));
		cbtechnic_id.setValue(cbltechnic_id.get(0));
		cbnew_subdividion_id.setValue(cblnew_subdividion_id.get(0));
		cbStatus.setValue("In Process");
		
		tcIdTr.setCellValueFactory(new PropertyValueFactory<Transportation, Integer>("idtransportation")); //1 столбик
		tcDate_Tr.setCellValueFactory(new PropertyValueFactory<Transportation, Date>("date_transportation")); //2 столбик
		tcStatus.setCellValueFactory(new PropertyValueFactory<Transportation, String>("status")); //1 столбик
		tcnew_mat_resp.setCellValueFactory(new PropertyValueFactory<Transportation, Integer>("new_mat_resp")); //2 столбик
		tctechnic_id.setCellValueFactory(new PropertyValueFactory<Transportation, Integer>("technic_id")); //1 столбик
		tcnew_subdividion_id.setCellValueFactory(new PropertyValueFactory<Transportation, Integer>("new_subdividion_id")); //2 столбик
		
		tvTr.setItems(FXCollections.observableArrayList(db.getAllTr())); //инициализация динамического массива из элементов Subd и передача соответствующей информации в столбики
		
		tvTr.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showTrDetails(newValue)); //Прослушиватель нажатия на табличку
	}
	//метод интерпритирования выбранного элемента таблицы по определенным ячейкам
	private void showTrDetails(Transportation cl) {
		if (cl != null) {
			tfIdTr.setText(Integer.toString(cl.getIdtransportation()));
			tfDate_Tr.setText(cl.getDate_transportation().toString());
			cbStatus.setValue(cl.getStatus());
			cbNew_mat_resp.setValue(db.getWorker(cl.getNew_mat_resp()));
			cbtechnic_id.setValue(db.getTech(cl.getTechnic_id()));
			cbnew_subdividion_id.setValue(db.getSubd(cl.getNew_subdividion_id()));
		} else {
			tfIdTr.setText("");
			tfDate_Tr.setText("");
			cbStatus.setValue(cblStatus.get(0));
			cbNew_mat_resp.setValue(cblNew_mat_resp.get(0));
			cbtechnic_id.setValue(cbltechnic_id.get(0));
			cbnew_subdividion_id.setValue(cblnew_subdividion_id.get(0));
		}
	}
	
	//добавить новую запись в таблицу
	@FXML
	private void handleNew() {
		if (isInputValid(1)) {
			db.newTr(Date.valueOf(tfDate_Tr.getText()), cbStatus.getValue(), Integer.parseInt(cbNew_mat_resp.getValue().substring(0, cbNew_mat_resp.getValue().indexOf(" "))),
					Integer.parseInt(cbtechnic_id.getValue().substring(0, cbtechnic_id.getValue().indexOf(" "))),
					Integer.parseInt(cbnew_subdividion_id.getValue().substring(0, cbnew_subdividion_id.getValue().indexOf(" "))));
			tvTr.setItems(FXCollections.observableArrayList(db.getAllTr()));
		}
	}
	//удалить строчку с id n 
	@FXML
	private void handleDel() {
		if (isInputValid(2)) {
			db.delTr(Integer.parseInt(tfIdTr.getText()));
			tvTr.setItems(FXCollections.observableArrayList(db.getAllTr()));
		}
	}
	  //обновить строку с id n на параметры
	  
	@FXML
	private void handleUpd() {
		if (isInputValid(3)) {
			db.updTr(Integer.parseInt(tfIdTr.getText()), Date.valueOf(tfDate_Tr.getText()), cbStatus.getValue(), Integer.parseInt(cbNew_mat_resp.getValue().substring(0, cbNew_mat_resp.getValue().indexOf(" "))),
					Integer.parseInt(cbtechnic_id.getValue().substring(0, cbtechnic_id.getValue().indexOf(" "))),
					Integer.parseInt(cbnew_subdividion_id.getValue().substring(0, cbnew_subdividion_id.getValue().indexOf(" "))));
			tvTr.setItems(FXCollections.observableArrayList(db.getAllTr()));
		}
	}
	 
	// проверка корректности ввода данных 
	private boolean isInputValid(int i) {
		String errorMessage = "";
		if (i == 1) {
			if (tfDate_Tr.getText() == null || tfDate_Tr.getText().length() == 0) {
				errorMessage += "No valid date!\n";
			} else {
				try {
					df.parse(tfDate_Tr.getText()).getTime();
				} catch (ParseException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
				} catch (IllegalArgumentException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
				}
			}
		
			
		} else if (i == 2) {
			if (tfIdTr.getText() == null || tfIdTr.getText().length() == 0) {
				errorMessage += "No valid ID!\n";
			} else {
				try {
					Integer.parseInt(tfIdTr.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
		}
		else if (i == 3) {
			if (tfIdTr.getText() == null || tfIdTr.getText().length() == 0) {
				errorMessage += "No valid ID!\n";
			} else {
				try {
					Integer.parseInt(tfIdTr.getText());
				} catch (NumberFormatException e) {
					errorMessage += "Format ID is not a number!\n";
				}
			}
			if (tfDate_Tr.getText() == null || tfDate_Tr.getText().length() == 0) {
				errorMessage += "No valid date!\n";
			} else {
				try {
					df.parse(tfDate_Tr.getText()).getTime();
				} catch (ParseException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
				} catch (IllegalArgumentException e) {
					errorMessage += "Not this format! (Need YYYY-MM-DD)\n";
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