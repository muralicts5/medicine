package com.cts.medicinestock.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
class MedicineStockTest {

	@InjectMocks
	MedicineStock medicineStock;

	@Test
	void testMedicine() {

	}

	@Test
	void testMedicineStringStringStringLocalDateInt() {
		LocalDate date = LocalDate.of(2017, 1, 13);
		MedicineStock medicineStock = new MedicineStock("Orthoherb", "Eranda", "Orthopaedics", date, 8000);
		assertEquals("Orthoherb", medicineStock.getName());
		assertEquals("Eranda", medicineStock.getChemicalComposition());
		assertEquals("Orthopaedics", medicineStock.getTargetAilment());
		assertEquals(date, medicineStock.getDateOfExpiry());
		assertEquals(8000, medicineStock.getNumberOfTabletsInStock());

	}

	@Test
	void testToString() {
		String sampleString = medicineStock.toString();
		assertTrue(sampleString.contains("name=" + medicineStock.getName()));
	}

	@Test
	void testGetName() {
		medicineStock.setName("Orthoherb");
		assertEquals("Orthoherb", medicineStock.getName());
	}

	@Test
	void testSetName() {
		medicineStock.setName("Orthoherb");
		assertEquals("Orthoherb", medicineStock.getName());
	}

	@Test
	void testGetChemicalComposition() {
		medicineStock.setChemicalComposition("Eranda");
		assertEquals("Eranda", medicineStock.getChemicalComposition());
	}

	@Test
	void testSetChemicalComposition() {
		medicineStock.setChemicalComposition("Eranda");
		assertEquals("Eranda", medicineStock.getChemicalComposition());
	}

	@Test
	void testGetTargetAilment() {
		medicineStock.setTargetAilment("Orthopaedics");
		assertEquals("Orthopaedics", medicineStock.getTargetAilment());
	}

	@Test
	void testSetTargetAilment() {
		medicineStock.setTargetAilment("Orthopaedics");
		assertEquals("Orthopaedics", medicineStock.getTargetAilment());
	}

	@Test
	void testGetDateOfExpiry() {
		LocalDate date = LocalDate.of(2017, 1, 13);
		medicineStock.setDateOfExpiry(date);
		assertEquals(date, medicineStock.getDateOfExpiry());

	}

	@Test
	void testSetDateOfExpiry() {
		LocalDate date = LocalDate.of(2017, 1, 13);
		medicineStock.setDateOfExpiry(date);
		assertEquals(date, medicineStock.getDateOfExpiry());
	}

	@Test
	void testGetNumberOfStock() {
		medicineStock.setNumberOfTabletsInStock(8000);
		assertEquals(8000, medicineStock.getNumberOfTabletsInStock());
	}

	@Test
	void testSetNumberOfStock() {
		medicineStock.setNumberOfTabletsInStock(8000);
		assertEquals(8000, medicineStock.getNumberOfTabletsInStock());
	}
}
