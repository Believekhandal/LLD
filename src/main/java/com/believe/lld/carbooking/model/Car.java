package com.believe.lld.carbooking.model;

import java.util.List;

import lombok.Data;

@Data
public class Car {
	
	private String price;
	private String model;
	private String availabilityRange; //28 APR-1 MAY
	private List<BookParam> bookedDays;
	//5 -10  
	//15th -20th
	
}
