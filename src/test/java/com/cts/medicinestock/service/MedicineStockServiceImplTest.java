package com.cts.medicinestock.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.medicinestock.dao.MedicineStockRepository;
import com.cts.medicinestock.entity.MedicineStock;

//@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
class MedicineStockServiceImplTest {

	@Autowired
	private MedicineStockServiceImpl medicineStockService;

	//@Mock
	private MedicineStock medicineStock;

	
	@MockBean
	private MedicineStockRepository repository;
	
	
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
	
	
	@Test
	void testGetMedicineStockInformation() {
		System.out.println("inside the test");
		when(repository.findAll()).thenReturn(getMedicineStockMockData());
		List<MedicineStock> medicineList = medicineStockService.getMedicineStockInformation();
		System.out.println("after the test");

		assertNotNull(medicineList);
	}

	//@Test
	void testGetMedicineNamesByTargetAilment() {
		List<String> medicineList = medicineStockService.getMedicineNamesByTargetAilment("Gynaecology");
		assertNotNull(medicineList);
	}

	//@Test
	void testGetNumberOfTabletsInStockByName() {
		int numberOfTabletsInStockByName = medicineStockService.getNumberOfTabletsInStockByName("Dolo-650");
		assertNotNull(numberOfTabletsInStockByName);
	}

	//@Test
	void testGetMedicineByName() {
		MedicineStock medicineByName = medicineStockService.getMedicineByName("Dolo-650");
		assertNotNull(medicineByName);
	}

}
