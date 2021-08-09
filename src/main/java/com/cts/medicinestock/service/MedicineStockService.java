package com.cts.medicinestock.service;

import java.util.List;

import com.cts.medicinestock.entity.MedicineStock;

public interface MedicineStockService {

	public List<MedicineStock> getMedicineStockInformation();

	List<String> getMedicineNamesByTargetAilment(String treatingAilment);

	int getNumberOfTabletsInStockByName(String medicine);

	MedicineStock getMedicineByName(String name);

	void update(MedicineStock medicine);

}
