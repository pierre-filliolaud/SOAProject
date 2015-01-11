package com.myCompany.soapWebService;

import java.util.List;

import javax.jms.JMSException;
import javax.jws.WebService;

import com.myCompany.application.CarService;
import com.myCompany.model.Car;

@WebService(endpointInterface = "com.myCompany.soapWebService.CarWebService")
public class CarWebServiceImpl implements CarWebService{

	CarService carService;
	
	public CarService getCarService() {
		return carService;
	}

	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	@Override
	public long save(Car car) {
		try {
			return carService.save(car);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	@Override
	public List<Car> findAllCars() {
		return carService.findAllCars();
	}
}
