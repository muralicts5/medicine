package com.cts.medicinestock.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.medicinestock.dao.MedicineStockRepository;
import com.cts.medicinestock.entity.MedicineStock;

@Service
@Transactional
public class MedicineStockServiceImpl implements MedicineStockService {

	@Autowired
	private MedicineStockRepository repository;

	/**
	 * Get the medicine stock information from the database.
	 */

	@Override
	public List<MedicineStock> getMedicineStockInformation() {
		return repository.findAll();
	}

	/**
	 * Get the medicines by target ailment from database based on the provided
	 * treating ailment
	 */

	@Override
	public List<String> getMedicineNamesByTargetAilment(String treatingAilment) {
		List<String> medicines = new ArrayList<>();
		List<MedicineStock> medicineByTargetAilment = repository.getMedicineByTargetAilment(treatingAilment);
		for (Iterator<?> iterator = medicineByTargetAilment.iterator(); iterator.hasNext();) {
			MedicineStock medicineStock = (MedicineStock) iterator.next();
			medicines.add(medicineStock.getName());
		}
		return medicines;
	}

	/**
	 * Get the count of tablets present in the stock for a given medicine
	 */

	@Override
	public int getNumberOfTabletsInStockByName(String medicine) {
		return repository.getNumberOfTabletsInStockByName(medicine).getNumberOfTabletsInStock();
	}

	/**
	 * Get the MedicineStock by using medicine name
	 */

	@Override
	public MedicineStock getMedicineByName(String name) {
		return repository.getMedicineByName(name);
	}

	/* Update the Number of Tablets In Stock */

	@Override
	public void update(MedicineStock medicine) {
		String name = medicine.getName();
		int numberOfTabletsInStock = medicine.getNumberOfTabletsInStock();
		repository.updateMedicine(numberOfTabletsInStock, name);
	}

}
