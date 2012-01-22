package org.books.service;

import javax.ejb.Local;

@Local
public interface BookManagerLocal extends BookManagerRemote {

	public void setMessage(String message);
	public String getMessage();
}
