package application;

import java.sql.Date;

import javafx.beans.property.*;

public class Tech {
	private IntegerProperty idtechnic;
	private StringProperty name;
	private StringProperty model;
	private ObjectProperty<Date> date;
	private FloatProperty cost;
	private IntegerProperty room_num;
	private IntegerProperty mat_resp_id;
	private IntegerProperty subdividion_id;
	
	public Tech(){
		this.idtechnic = new SimpleIntegerProperty(0);
		this.name = new SimpleStringProperty("");
		this.model = new SimpleStringProperty("");
		this.date = new SimpleObjectProperty<Date>(Date.valueOf("2018-01-01"));;
		this.cost = new SimpleFloatProperty(0);
		this.room_num = new SimpleIntegerProperty(0);
		this.mat_resp_id = new SimpleIntegerProperty(0);
		this.subdividion_id = new SimpleIntegerProperty(0);
		
	}
	public Tech(Integer idtechnic, String name, String model, Date date, Float cost, Integer room_num, Integer mat_resp_id, Integer subdividion_id) {
		this.idtechnic = new SimpleIntegerProperty(idtechnic);
		this.name = new SimpleStringProperty(name);
		this.model = new SimpleStringProperty(model);
		this.date = new SimpleObjectProperty<Date>(date);
		this.cost = new SimpleFloatProperty(cost);
		this.room_num = new SimpleIntegerProperty(room_num);
		this.mat_resp_id = new SimpleIntegerProperty(mat_resp_id);
		this.subdividion_id = new SimpleIntegerProperty(subdividion_id);
		
	}
	public Integer getIdtechnic() {
		return this.idtechnic.get();
	}
	public String getName() {
		return this.name.get();
	}
	public String getModel() {
		return this.model.get();
	}
	public Date getDate() {
		return this.date.get();
	}
	public Float getCost() {
		return this.cost.get();
	}
	public Integer getRoom_num() {
		return this.room_num.get();
	}
	public Integer getMat_resp_id() {
		return this.mat_resp_id.get();
	}
	public Integer getSubdividion_id() {
		return this.subdividion_id.get();
	}
}
