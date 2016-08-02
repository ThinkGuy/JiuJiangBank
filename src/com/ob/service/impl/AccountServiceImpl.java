package com.ob.service.impl;

import java.util.ArrayList;

import antlr.collections.List;

import com.ob.dao.AccountDAO;
import com.ob.dao.ClientDAO;
import com.ob.dao.DealinformDAO;
import com.ob.model. Account;
import com.ob.model.Client;
import com.ob.model.Dealinform;
import com.ob.service. AccountService;
import com.ob.service.ClientService;

public class AccountServiceImpl implements  AccountService {
	private AccountDAO dao;
	private ClientDAO clientDAO;
	private DealinformDAO dealinformDAO;
	
	@Override
	public AccountDAO getDao() {
		return dao;
	}

	public void setDao(AccountDAO dao) {
		this.dao = dao;
	}

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	public DealinformDAO getDealinformDAO() {
		return dealinformDAO;
	}

	public void setDealinformDAO(DealinformDAO dealinformDAO) {
		this.dealinformDAO = dealinformDAO;
	}

	@Override
	public boolean setLoss(Account account) {
		account = dao.findById(account.getAccountid());
		account.setIsloss(1);
		dao.merge(account);
		return true;
	}

	@Override
	public boolean isActive(Account account) {
		account = dao.findById(account.getAccountid());
		
		if (account.getIsactive() == 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean setActive(Account account) {
		account = dao.findById(account.getAccountid());
		account.setIsactive(1);
		dao.merge(account);
		return true;
	}
	
	@Override
	public ArrayList<String> getCdsOfClient(Account account) {
		ArrayList<String> accountIdList = new ArrayList<String>();
		ArrayList<Account> accounts = (ArrayList<Account>)dao.findByClientid(account.getClientid());
		
		for (Account item : accounts) {
			accountIdList.add(item.getAccountid());
		}
		
		return accountIdList;
	}

	
	@Override
	public ArrayList<String> getCdsOfClientByLoss(Account account) {
		ArrayList<String> accountIdList = new ArrayList<String>();
		ArrayList<Account> accounts = (ArrayList<Account>)dao.findByClientid(account.getClientid());
		
		for (Account item : accounts) {
			
			if (item.getIsloss() == 0) {
				accountIdList.add(item.getAccountid());
			}
		}
		
		return accountIdList;
	}
	
	@Override
	public ArrayList<String> getCdsOfClientByActive(Account account) {
		ArrayList<String> accountIdList = new ArrayList<String>();
		ArrayList<Account> accounts = (ArrayList<Account>)dao.findByClientid(account.getClientid());
		
		for (Account item : accounts) {
			
			if (item.getIsactive() == 1) {
				accountIdList.add(item.getAccountid());
			}
		}
		
		return accountIdList;
	}
	
	@Override
	public ArrayList<String> getCdsOfClientByNotActive(Account account) {
		ArrayList<String> accountIdList = new ArrayList<String>();
		ArrayList<Account> accounts = (ArrayList<Account>)dao.findByClientid(account.getClientid());
		
		for (Account item : accounts) {
			
			if (item.getIsactive() == 0) {
				accountIdList.add(item.getAccountid());
			}
		}
		
		return accountIdList;
	}
	
	@Override
	public ArrayList<String> getCdsOfClientByCancelDPWD(Account account) {
		ArrayList<String> accountIdList = new ArrayList<String>();
		ArrayList<Account> accounts = (ArrayList<Account>)dao.findByClientid(account.getClientid());
		
		for (Account item : accounts) {
			if (item.getDealwithoutpassword() == 0) {
				accountIdList.add(item.getAccountid());
			}
		}
		return accountIdList;
	}
	
	/**
	 * 确认网银密码。
	 */
	@Override
	public boolean confirmSearchPassword(Client client) {
		if (client.getUserpassword().equals(clientDAO.findById(client.getClientid()).getUserpassword().toString())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 确认信用卡查询密码。
	 */
	@Override
	public boolean confirmCDSearchPassword(Account account) {
		String searchPassword = account.getSearchpassword();
		account = dao.findById(account.getAccountid());
		if (searchPassword.equals(account.getSearchpassword())) {
			return true;
		}
		return false;
	}

	/**
	 * 查询积分。
	 */
	@Override
	public Integer getIntegration(Account account) {
		return dao.findById(account.getAccountid()).getIntegrate();
	}

	/**
	 * 可用额度查询。
	 */
	@Override
	public Integer getLimit(Account account) {
		return dao.findById(account.getAccountid()).getCdlimit();
	}

	/**
	 * 可用额度设置。
	 */
	@Override
	public boolean setLimit(Account account) {
		Integer limit = account.getCdlimit();
		account = dao.findById(account.getAccountid());
		account.setCdlimit(limit);
		dao.merge(account);
		return true;
	}


	
	@Override
	public boolean setDealPassword(Account account) {
		Integer dealPassword = account.getDealpassword();
		account  = dao.findById(account.getAccountid());
		account.setDealpassword(dealPassword);
		dao.merge(account);
		return true;
	}
	
	@Override
	public boolean setSearchPassword(Account account) {
		String searchPassword = account.getSearchpassword();
		account  = dao.findById(account.getAccountid());
		account.setSearchpassword(searchPassword);
		dao.merge(account);
		return true;
	}

	@Override
	public boolean changeDealPassword(Account account) {
		return false;
	}

	@Override
	public boolean cancelDealPassword(Account account) {
		account = dao.findById(account.getAccountid());
		account.setDealwithoutpassword(1);
		dao.merge(account);
		return true;
	}
}
