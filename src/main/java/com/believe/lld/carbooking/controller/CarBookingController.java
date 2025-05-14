package com.believe.lld.carbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.believe.lld.carbooking.model.Car;
import com.believe.lld.carbooking.model.CarStore;
import com.believe.lld.carbooking.service.CarBookingService;

@Controller
public class CarBookingController {

	@Autowired
	CarBookingService bookingService;
	
	@PostMapping
	List<CarStore> getCarStore(String address){
		return bookingService.getStoresByAddress(address);
	}
	
	@PostMapping
	List<Car> getCarsByStore(String storeId, String startDate, String endDate){
		return bookingService.getCarsByStoreId( storeId,  startDate,  endDate);
	}
	
	@PostMapping
	void bookCar(Car car, String storeId){
		bookingService.bookCarByCarAttributes(car, storeId);
	}
	
}
