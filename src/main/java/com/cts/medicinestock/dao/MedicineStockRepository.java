package com.cts.medicinestock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.medicinestock.entity.MedicineStock;

@Repository
public interface MedicineStockRepository extends JpaRepository<MedicineStock, String> {

	/* Getting medicine based on target ailment */
	@Query(name = "select * from medicine where target_ailment = :targetAilment", nativeQuery = true)
	List<MedicineStock> getMedicineByTargetAilment(@Param("targetAilment") String targetAilment);

	/* Getting Stock Count form DB */
	@Query(name = "SELECT number_of_tablets_in_stock FROM medicine_stock where name = :medicine", nativeQuery = true)
	MedicineStock getNumberOfTabletsInStockByName(@Param("medicine") String medicine);

	/* Getting Medicine Stock using name */
	@Query(name = "SELECT * from medicine_stock where name= :name", nativeQuery = true)
	MedicineStock getMedicineByName(@Param("name") String name);

	/* Updating Medicine Stock */
	@Modifying
	@Query(value = "UPDATE medicine_stock SET number_of_tablets_in_stock= :numberOfStock WHERE name =:name", nativeQuery = true)
	void updateMedicine(int numberOfStock, String name);

}
