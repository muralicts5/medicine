package com.cts.medicinestock.entity;

/**
 *Entity class to contain all the fields related to medicine stock
 *information. It has an annotation Entity because we want to store
 *the values in the database. The table name will be medicine_stock.
 */

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "medicine_stock")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MedicineStock {

	@Id
	private String name;

	private String chemicalComposition;

	private String targetAilment;

	private LocalDate dateOfExpiry;

	private int numberOfTabletsInStock;

}
