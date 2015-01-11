package com.myCompany.soapWebService;

import java.util.List;

import javax.jws.WebService;

import com.myCompany.model.Car;

@WebService
public interface CarWebService {
	
	long save(Car car);
	List<Car> findAllCars();

}
