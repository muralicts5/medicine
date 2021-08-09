package com.cts.medicinestock.controller;

/**
 This class is handling the end points for medicine stock information. 
 This controller has only one mappings which will be
 used to extract information of medicine stock. 
 It will use an interface MedicineStockService for extracting information about medicine stock. 
*/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.medicinestock.entity.MedicineStock;
import com.cts.medicinestock.exception.DatabaseRetrievalException;
import com.cts.medicinestock.service.MedicineStockService;
import com.cts.medicinestock.util.MedicineStockConstant;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MedicineStockController {

	/**
	 * An interface which has an implementation class MedicineStockServiceImpl that
	 * contains the business logic for extracting information about medicine stock
	 */

	@Autowired
	private MedicineStockService medicineStockService;

	/**
	 * This Controller will return a list of all the medicines stored in the
	 * database
	 */

	@RequestMapping(value = "/getMedicineStockInformation", method = RequestMethod.GET)
	public ResponseEntity<?> getMedicineStockInformation() {
		log.info("Getting all the medicine stock information");
		List<MedicineStock> medicineStockInformation = medicineStockService.getMedicineStockInformation();
		if (medicineStockInformation.isEmpty()) {
			throw new DatabaseRetrievalException(MedicineStockConstant.MEDICINE_STOCK_RETRIEVAL_EXCEPTION);
		}
		log.info("Fetched all medicine stocks");
		return new ResponseEntity<>(medicineStockInformation, HttpStatus.OK);
	}

	/**
	 * Based on the treating ailment this method will return the information about
	 * the medicine. After fetching details in a list, from the list one by one
	 * extract each medicineStock object and fetch medicine from it and store these
	 * in an array and return it.
	 */

	@GetMapping(value = "/byTreatingAilment")
	public ResponseEntity<?> getMedicineByTreatingAilment(
			@RequestHeader(name = "Authorization", required = true) String token,
			@RequestParam String treatingAilment) {
		log.info("Getting the medicine names for " + treatingAilment);
		List<String> medicines = medicineStockService.getMedicineNamesByTargetAilment(treatingAilment);
		if (medicines.isEmpty()) {
			throw new DatabaseRetrievalException(MedicineStockConstant.MEDICINE_STOCK_RETRIEVAL_EXCEPTION);
		}
		log.info("Fetched medicine names");
		return new ResponseEntity<>(medicines.toArray(new String[0]), HttpStatus.OK);
	}

	/**
	 * Fetch the stock count for the given medicine. Call the
	 * MedicineStockServiceImpl method to extract the result
	 */

	@GetMapping(value = "/get-stock-count")
	public ResponseEntity<?> getStockCountForMedicine(
			@RequestHeader(name = "Authorization", required = true) String token, @RequestParam String medicine) {
		log.info("Getting the count for " + medicine);
		log.info("The count will be displayed");
		int numberOfTabletsInStockByName = medicineStockService.getNumberOfTabletsInStockByName(medicine);
		if (numberOfTabletsInStockByName == 0) {
			throw new DatabaseRetrievalException(MedicineStockConstant.STOCK_NOT_AVAILABLE_EXCEPTION);
		}
		return new ResponseEntity<>(numberOfTabletsInStockByName, HttpStatus.OK);
	}

	/*
	 * Getting the medicine by name for updation of Medicine Stock from the
	 * PharmacySupply Microservice
	 */

	@GetMapping(value = "/getmedicinebyname")
	public ResponseEntity<?> getMedicineByName(@RequestHeader(name = "Authorization", required = true) String token,
			@RequestParam String name) {
		log.info("Getting Medicine by name for updation");
		log.info("Required Medicine will be obtained");
		return new ResponseEntity<>(medicineStockService.getMedicineByName(name), HttpStatus.OK);
	}

	/* Updating the medicine stock after supplying the medicine to pharmacies */

	@PutMapping(value = "/update-stock")
	public void updateStockByName(@RequestHeader(name = "Authorization", required = true) String token,
			@RequestBody MedicineStock medicineStock) {
		log.info("updating the stock after supply");
		medicineStockService.update(medicineStock);
		log.info("Updated successfully");
	}

}
