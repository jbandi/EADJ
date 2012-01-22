package org.books.service;

import javax.jms.JMSException;

public interface OrderSenderLocal {

	public void sendMessage(String text) throws JMSException;

}