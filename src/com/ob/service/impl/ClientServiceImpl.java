package com.ob.service.impl;

import com.ob.dao.ClientDAO;
import com.ob.service.ClientService;

public class ClientServiceImpl implements ClientService {
	private ClientDAO clientDAO;

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}
}
