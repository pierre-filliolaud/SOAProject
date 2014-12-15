package com.myCompany.application;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;

import com.myCompany.model.Model;
import com.myCompany.util.EntityManagerUtil;

public class MyServiceImpl implements MyService{

	EntityManager entityManager;
	QueueConnectionFactory connectionFactory;
	QueueSender sender;
	QueueSession session;
	
	public MyServiceImpl(EntityManagerUtil entityManagerUtil, QueueConnectionFactory connectionFactory, Queue queue) throws JMSException{
		super();
		entityManager = entityManagerUtil.getEntityManager();
		this.connectionFactory = connectionFactory;
		QueueConnection connection =  connectionFactory.createQueueConnection();
		session = connection.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);
		sender = session.createSender(queue);
	}
	
	@Override
	public String methode() throws Exception {
		Model model = new Model();
		model.setI(1);
		entityManager.persist(model);
		TextMessage message = session.createTextMessage("hello");
		sender.send(message);
		return "Hello";
	}

}
