package com.cts.medicinestock;

/**
 * This class is responsible for creating all objects, dependency injection and
 * managing the life cycle of all beans
 * 
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedicineStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicineStockApplication.class, args);
	}

}
