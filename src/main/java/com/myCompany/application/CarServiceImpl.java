package com.myCompany.application;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.myCompany.model.Car;
import com.myCompany.util.EntityManagerUtil;

public class CarServiceImpl implements CarService {
	
	EntityManager entityManager;
	QueueConnectionFactory connectionFactory;
	QueueSender sender;
	QueueSession session;
	private QueueReceiver queueReceiver;
	private QueueConnection connection;
	
	private static List<Car> cachedCars = new ArrayList<Car>();
	
	public CarServiceImpl(EntityManagerUtil entityManagerUtil, QueueConnectionFactory connectionFactory, Queue queue) throws JMSException{
		super();
		entityManager = entityManagerUtil.getEntityManager();
		this.connectionFactory = connectionFactory;
		connection =  connectionFactory.createQueueConnection();
		session = connection.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);
		sender = session.createSender(queue);
		
		queueReceiver = session.createReceiver(queue);
		queueReceiver.setMessageListener(this);
		connection.start();
	}

	@Override
	public long save(Car car) throws JMSException {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(car);
		tx.commit();
		TextMessage message = session.createTextMessage("new car");
		sender.send(message);
		return car.getId();
	}

	@Override
	public List<Car> findAllCars() {
//		@SuppressWarnings("unchecked")
//		List<Car> cars = entityManager.createQuery("select c from Car c").getResultList();
//		cars.add(new Car("TT-878-75"));
//		return cars;
		return cachedCars;
	}
	
	public void updateCachedCar() {
		@SuppressWarnings("unchecked")
		List<Car> cars = entityManager.createQuery("select c from Car c").getResultList();
		cars.add(new Car("TT-878-75"));
		cachedCars.clear();
		cachedCars.addAll(cars);
	}
	
	@Override
	public void onMessage(Message arg0) {
		updateCachedCar();
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		if(session != null){
			session.close();
		}
		if(connection != null){
			connection.close();
		}
	}
}
