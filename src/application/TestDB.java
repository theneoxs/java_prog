package application;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestDB {

	private Database db;

	@BeforeEach
	void init() {
		db = new Database();
		db.openConnection("root", "admin");
	}

	@AfterEach
	void close() {
		db.closeConnection();
		db = null;
	}

	@Test
	@DisplayName("TEST 1")
	void insertTech() throws IOException{
		assertEquals(db.newTech("Aaaa", "Vvvv", Date.valueOf("2018-01-01"), Float.parseFloat("0.432"), 25, 1, 1), true);
	}
}
