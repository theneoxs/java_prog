package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Subd {
	private IntegerProperty idsubdividion;
	private StringProperty full_name;
	private StringProperty short_name;
	public Subd() {
		this.idsubdividion = new SimpleIntegerProperty(0);
		this.full_name = new SimpleStringProperty("");
		this.short_name = new SimpleStringProperty("");

	}
	public Subd(Integer idsubd, String fname, String shname) {
		this.idsubdividion = new SimpleIntegerProperty(idsubd);
		this.full_name = new SimpleStringProperty(fname);
		this.short_name = new SimpleStringProperty(shname);

	}
	public Integer getIdsubdividion() {
		return this.idsubdividion.get();
	}
	public String getFull_name() {
		return this.full_name.get();
	}
	public String getShort_name() {
		return this.short_name.get();
	}
	
}
