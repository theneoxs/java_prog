package application;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transportation {
	private IntegerProperty idtransportation;
	private ObjectProperty<Date> date_transportation;
	private StringProperty status;
	private IntegerProperty new_mat_resp;
	private IntegerProperty technic_id;
	private IntegerProperty new_subdividion_id;
	
	public Transportation() {
		this.idtransportation = new SimpleIntegerProperty(0);
		this.date_transportation = new SimpleObjectProperty<Date>(Date.valueOf("2018-01-01"));;
		this.status = new SimpleStringProperty("");
		this.new_mat_resp = new SimpleIntegerProperty(0);
		this.technic_id = new SimpleIntegerProperty(0);
		this.new_subdividion_id = new SimpleIntegerProperty(0);
	}
	public Transportation(Integer idtransportation, Date date_transportation, String status, Integer new_mat_resp, Integer technic_id, Integer new_subdividion_id) {
		this.idtransportation = new SimpleIntegerProperty(idtransportation);
		this.date_transportation = new SimpleObjectProperty<Date>(date_transportation);
		this.status = new SimpleStringProperty(status);
		this.new_mat_resp = new SimpleIntegerProperty(new_mat_resp);
		this.technic_id = new SimpleIntegerProperty(technic_id);
		this.new_subdividion_id = new SimpleIntegerProperty(new_subdividion_id);
	}
	public Integer getIdtransportation() {
		return this.idtransportation.get();
	}
	public Date getDate_transportation() {
		return this.date_transportation.get();
	}
	public String getStatus() {
		return this.status.get();
	}
	public Integer getNew_mat_resp() {
		return this.new_mat_resp.get();
	}
	public Integer getTechnic_id() {
		return this.technic_id.get();
	}
	public Integer getNew_subdividion_id() {
		return this.new_subdividion_id.get();
	}

}
