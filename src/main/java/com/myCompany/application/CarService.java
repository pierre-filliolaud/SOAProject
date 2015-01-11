package com.myCompany.application;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.MessageListener;

import com.myCompany.model.Car;

public interface CarService extends MessageListener {
	
	long save(Car car) throws JMSException;
	List<Car> findAllCars();

}
