package org.books.service;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@MessageDriven
public class OrderProcessor implements MessageListener {

	@Resource
	private TimerService timerService;
	
    @PersistenceContext(unitName = "bookstore")
    private EntityManager entityManager;

	public void onMessage(Message arg0) {
		System.out.println("Message received");
		timerService.createTimer(50, "Message processed");
	}

	@Timeout
	public void closeOrder(Timer timer) {
		String msg = (String) timer.getInfo();
		
		// TODO: Load and process message
		
		System.out.println(msg);
	}
}
