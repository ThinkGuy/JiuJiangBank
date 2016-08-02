package com.ob.action;

import com.ob.model.Account;
import com.ob.model.Client;
import com.ob.service.AccountService;
import com.opensymphony.xwork2.ModelDriven;

public class CreditcardActiveAction extends SuperAction implements
			ModelDriven<Account> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Account account;
	private Client client = new Client();
	private AccountService accountService;

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	/**
	 * ���ÿ����
	 */
	public void active() {
		account.setAccountid(session.getAttribute("creditcard").toString());
		accountService.setActive(account);
	}
	
	/**
	 * ��ʾ���ÿ���
	 * @return
	 */
	public String showCreditCard() {
		account.setClientid(1);
		session.setAttribute("clientId", 1);
		request.setAttribute("accountIdList", accountService.getCdsOfClientByNotActive(account));
		return "showCreditCardActive";
	}
	
	/**
	 * ѡ�񿨡�
	 * @return
	 */
	public String selectCard() {
		session.setAttribute("creditcard", request.getParameter("selectCd"));
		session.setAttribute("cdLimit", request.getParameter("cdLimit"));
		return "selectOkActive";
	}
	
	/**
	 * ���ý������롣
	 * @return
	 */
	public String setDealPassword() {
		account.setAccountid(session.getAttribute("creditcard").toString());
		String dealPassword = request.getParameter("dealPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		if (!dealPassword.equals(confirmPassword)) {
			request.setAttribute("info", "�������벻һ�£����������룡");

		} else if (!dealPassword.matches("^[0-9]{6}$")) {
			request.setAttribute("info", "����������6������");
		} else {
			account.setDealpassword(Integer.parseInt((dealPassword)));
			accountService.setDealPassword(account);
			return "dealPasswordSetSuccessActive";
		}
		return "dealPasswordSetFailedActive";
	}
	
	/**
	 * ���ò�ѯ���롣
	 * @return
	 */
	public String setSearchPassword() {
		account.setAccountid(session.getAttribute("creditcard").toString());
		String searchPassword = request.getParameter("searchPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		if (!searchPassword.equals(confirmPassword)) {
			request.setAttribute("info", "�������벻һ�£����������룡");

		} else if (searchPassword.length() > 16) {
			request.setAttribute("info", "����������С��16�������ַ�");
		} else {
			account.setSearchpassword(searchPassword);
			accountService.setSearchPassword(account);
			active();
			return "searchPasswordSetSuccessActive";
		}
		return "searchPasswordSetFailedActive";
	}
	
	
	@Override
	public Account getModel() {
		if (account == null) {
			account = new Account();
		}
		return account;
	}
}
