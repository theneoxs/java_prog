package application;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Test1 {

	private Tech c1, c2;

	@BeforeEach
	void init() {
		c1 = new Tech();
		c2 = new Tech(1, "MacBook", "Apple", new Date(Calendar.getInstance().getTimeInMillis()), Float.parseFloat("0.4"), 35, 1, 1);
	}

	@AfterEach
	void close() {
		c1 = null;
		c2 = null;
	}
	
	@Test
	@DisplayName("TEST 1")
	void emptyClient() {
		assertEquals(c1.getIdtechnic(), Integer.valueOf(0), "idtechnic");
		assertEquals(c1.getName(), String.valueOf(""), "name");
		assertEquals(c1.getModel(), String.valueOf(""), "model");
		assertEquals(c1.getDate(), Date.valueOf("2018-01-01"), "date");
		assertEquals(c1.getCost(), Float.valueOf(0), "cost");
		assertEquals(c1.getRoom_num(), Integer.valueOf(0), "room_num");
		assertEquals(c1.getMat_resp_id(), Integer.valueOf(0), "mat_resp_id");
		assertEquals(c1.getSubdividion_id(), Integer.valueOf(0), "subdividion_id");
	}

	@Test
	@DisplayName("TEST 2")
	void idTech() {
		assertEquals(c2.getIdtechnic(), Integer.valueOf(1), "idtechnic");
	}

	@Test
	@DisplayName("TEST 3")
	void nAme() {
		assertEquals(c2.getName(), String.valueOf("MacBook"), "name");
	}

	@Test
	@DisplayName("TEST 4")
	void mOdel() {
		assertEquals(c2.getModel(), String.valueOf("Apple"), "model");
	}

	@Test
	@DisplayName("TEST 5")
	void dAte() {
		assertEquals(c2.getDate(), new Date(Calendar.getInstance().getTimeInMillis()), "date");
	}


}
