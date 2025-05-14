package com.believe.lld.carbooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.believe.lld.carbooking.dao.CarBookDao;
import com.believe.lld.carbooking.model.Car;
import com.believe.lld.carbooking.model.CarStore;

@Service
public class CarBookingService {

	@Autowired
	CarBookDao bookDao;

	public void bookCar(Car car) {

		return;

	}

	public List<CarStore> getStoresByAddress(String address) {
		List<CarStore> carStores = bookDao.getStoresByAddress(address);
		return carStores;
	}

	public void bookCarByCarAttributes(Car car, String storeId) {
		// TODO Auto-generated method stub
		bookDao.bookCarByStoreId(car, storeId);

	}

	public List<Car> getCarsByStoreId(String storeId, String startDate, String endDate) {
		
		List<Car> cars =  bookDao.getCarByStoreId(storeId);
		
		
		for(Car car:cars) {
			//Given startDate 28 endDate 30    
			//bookedDate  29 - 30
//			if((car.getBookedDays().getFirst()>startDate && car.getBookedDays().getLast()>endDate) && )
		}
		return null;
	}

}
