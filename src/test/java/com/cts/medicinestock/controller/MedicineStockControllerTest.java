package com.cts.medicinestock.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cts.medicinestock.dao.MedicineStockRepository;
import com.cts.medicinestock.entity.MedicineStock;
import com.cts.medicinestock.exception.DatabaseRetrievalException;
import com.cts.medicinestock.service.MedicineStockService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicineStockControllerTest {

	//@InjectMocks
	private MedicineStockController stockController;

	//@Mock
	private MedicineStock medicineStock;

	@MockBean
	private MedicineStockService medicineStockService;

	//@Mock
	private MedicineStockRepository medicineStockRepository;

 	private MockMvc mockMvc;
 	
 	 @Autowired
 	  private WebApplicationContext webApplicationContext;
	
	  private MockMvc setUpMockMvc() {
	    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		return mockMvc;

	  }
	 
	
	private List<MedicineStock> getMedicineStockMockData() {
		MedicineStock medicineStock1=new MedicineStock();
		medicineStock1.setChemicalComposition("cmp");
		medicineStock1.setName("stock 1");
		MedicineStock medicineStock2=new MedicineStock();
		medicineStock2.setChemicalComposition("cmp");
		medicineStock2.setName("stock 2");
		List<MedicineStock> medicineStocks=new ArrayList<MedicineStock>();
		medicineStocks.add(medicineStock1);
		medicineStocks.add(medicineStock2);
		return medicineStocks;
		
	}
	
	
	/**
	 * @throws Exception Tests the testGetMedicineStockInformation method and checks
	 *                   if the returned value is equal or not
	 */
	@Test
	@WithMockUser(username = "YourUsername", password = "YourPassword", roles = "USER")
	void testGetMedicineStockInformation() throws Exception {
	    mockMvc = setUpMockMvc();
	
		when(medicineStockService.getMedicineStockInformation()).thenReturn(getMedicineStockMockData());
		mockMvc.perform(get("/getMedicineStockInformation")
//				.with(httpBasic("user", "password"))
				.contentType(MediaType.APPLICATION_JSON))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$[0].name", is("stock 1")))
         .andExpect(jsonPath("$[1].name", is("stock 2")))
         .andDo(print());
		
		/*Assertions.assertThrows(DatabaseRetrievalException.class, () -> {
			ResponseEntity<?> responseEntity = stockController.getMedicineStockInformation();
			HttpStatus status = responseEntity.getStatusCode();
			assertNotNull(status);
			assertEquals(HttpStatus.OK, status);
		});*/
	}

	/**
	 * @throws Exception Tests the testGetMedicineByTreatingAilment method and
	 *                   checks if the returned value is equal or not
	 */
	//@Test
	void testGetMedicineByTreatingAilment() {
		Assertions.assertThrows(DatabaseRetrievalException.class, () -> {
			ResponseEntity<?> responseEntity = stockController.getMedicineByTreatingAilment(null, "Gynaecology");
			HttpStatus status = responseEntity.getStatusCode();
			assertNotNull(status);
			assertEquals(HttpStatus.OK, status);
		});
	}

	/**
	 * @throws Exception Tests the testGetStockCountForMedicine method and checks if
	 *                   the returned value is equal or not
	 */
	//@Test
	void testGetStockCountForMedicine() {
		Assertions.assertThrows(DatabaseRetrievalException.class, () -> {
			ResponseEntity<?> responseEntity = stockController.getStockCountForMedicine(null, "Orthoherb");
			HttpStatus status = responseEntity.getStatusCode();
			assertNotNull(status);
			assertEquals(HttpStatus.OK, status);
		});

	}

	/**
	 * Tests the testGetMedicineByName method and checks if the returned value is
	 * equal or not
	 */
	//@Test
	void testGetMedicineByName() {
		ResponseEntity<?> responseEntity = stockController.getMedicineByName(null, "Dolo-650");
		HttpStatus status = responseEntity.getStatusCode();
		assertNotNull(status);
		assertEquals(HttpStatus.OK, status);
	}

	/**
	 * Tests the testUpdateStockByName method and checks if the returned value is
	 * equal or not
	 */
	//@Test
	void testUpdateStockByName() {
		MedicineStock medicine = new MedicineStock();
		stockController.updateStockByName(null, medicine);
		Mockito.verify(medicineStockService, Mockito.times(1)).update(medicine);
	}

}
