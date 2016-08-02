package com.ob.action;

import com.ob.model.Account;
import com.ob.model.Client;
import com.ob.service.AccountService;
import com.ob.service.DealinformService;
import com.opensymphony.xwork2.ModelDriven;

public class CreditCardBillHaveAction extends SuperAction implements
		ModelDriven<Account> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Account account;
	private Client client = new Client();
	private AccountService accountService;
	private DealinformService dealinformService;

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public void setDealinformService(DealinformService dealinformService) {
		this.dealinformService = dealinformService;
	}

	/**
	 * ѡ�񿨡�
	 * 
	 * @return
	 */
	public String selectCard() {
		session.setAttribute("creditcard", request.getParameter("selectCd"));
		return "selectOkBillHave";
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
				accountService.getCdsOfClientByActive(account));
		return "showCreditCardBillHave";
	}

	/**
	 * ����ȷ�ϡ�
	 * 
	 * @return
	 */
	public String confirmPassword() {
		account.setAccountid(session.getAttribute("creditcard").toString());
		account.setSearchpassword(request.getParameter("searchPassword"));

		if (!accountService.confirmCDSearchPassword(account)) {
			return "confirmFailedBillHave";
		}

		request.setAttribute("dealInfoList",
				dealinformService.getBillHaveDealInfo(account.getAccountid()));

		return "confirmSuccessBillHave";
	}

	@Override
	public Account getModel() {

		if (account == null) {
			account = new Account();
		}
		return account;
	}
}

