package com.ob.action;

import com.ob.model.Account;
import com.ob.model.Client;
import com.ob.service.AccountService;
import com.opensymphony.xwork2.ModelDriven;

public class CreditcardLossAction extends SuperAction implements
		ModelDriven<Account> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Account account;
	private Client client = new Client();
	private AccountService accountService;

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * ���ÿ���ʧ���á�
	 * 
	 * @return
	 */
	public void loss() {
		account.setAccountid(session.getAttribute("creditcard").toString());
		accountService.setLoss(account);
	}

	/**
	 * ��ʾ���ÿ���
	 * 
	 * @return
	 */
	public String showCreditCard() {
		account.setClientid(1);
		session.setAttribute("clientId", 1);
		request.setAttribute("accountIdList",
				accountService.getCdsOfClientByLoss(account));
		return "showCreditCardLoss";
	}

	/**
	 * ����ȷ�ϡ�
	 * 
	 * @return
	 */
	public String confirmPassword() {
		client.setUserpassword(request.getParameter("accountPassword"));
		client.setClientid(Integer.parseInt(session.getAttribute("clientId")
				.toString()));

		if (!accountService.confirmSearchPassword(client)) {
			return "confirmFailedLoss";
		}
		loss();
		return "confirmSuccessLoss";
	}

	/**
	 * ѡ�񿨡�
	 * 
	 * @return
	 */
	public String selectCard() {
		session.setAttribute("creditcard", request.getParameter("selectCd"));
		return "selectOkLoss";
	}

	@Override
	public Account getModel() {

		if (account == null) {
			account = new Account();
		}
		return account;
	}
}
