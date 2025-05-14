package com.believe.lld.carbooking.model;

import java.util.List;

import lombok.Data;

@Data
public class CarStore {

	List<Car> cars;
	private String address;
}
