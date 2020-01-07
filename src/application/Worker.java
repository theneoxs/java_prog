package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Worker {
	private IntegerProperty idworker;
	private StringProperty name;
	private StringProperty surname;
	private StringProperty position;
	private IntegerProperty experience;
	private StringProperty login;
	private StringProperty password;
	private IntegerProperty subdividion_id;
	
	public Worker() {
		this.idworker = new SimpleIntegerProperty(0);
		this.name = new SimpleStringProperty("");
		this.surname = new SimpleStringProperty("");
		this.position = new SimpleStringProperty("");
		this.experience = new SimpleIntegerProperty(0);
		this.login = new SimpleStringProperty("");
		this.password = new SimpleStringProperty("");
		this.subdividion_id = new SimpleIntegerProperty(0);
	}
	public Worker(Integer idworker, String name, String surname, String position,
			Integer experience, String login, String password, Integer subdividion_id) {
		this.idworker = new SimpleIntegerProperty(idworker);
		this.name = new SimpleStringProperty(name);
		this.surname = new SimpleStringProperty(surname);
		this.position = new SimpleStringProperty(position);
		this.experience = new SimpleIntegerProperty(experience);
		this.login = new SimpleStringProperty(login);
		this.password = new SimpleStringProperty(password);
		this.subdividion_id = new SimpleIntegerProperty(subdividion_id);
	}
	public Integer getIdworker() {
		return this.idworker.get();
	}
	public String getName() {
		return this.name.get();
	}
	public String getSurname() {
		return this.surname.get();
	}
	public String getPosition() {
		return this.position.get();
	}
	public Integer getExperience() {
		return this.experience.get();
	}
	public String getLogin() {
		return this.login.get();
	}
	public String getPassword() {
		return this.password.get();
	}
	public Integer getSubdividion_id() {
		return this.subdividion_id.get();
	}
}
