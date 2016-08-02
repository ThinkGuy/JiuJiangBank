package com.ob.action;

import com.ob.model.Account;
import com.ob.service.AccountService;
import com.opensymphony.xwork2.ModelDriven;

public class CreditcardSearchPasswordSetAction extends SuperAction implements
		ModelDriven<Account> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Account account;
	private AccountService accountService;

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	/**
	 * �����������á�
	 * 
	 * @return
	 */
	public String setPassword() {
		account.setAccountid(session.getAttribute("creditcard").toString());
		String chPassword = request.getParameter("chPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		if (!account.getSearchpassword().equals(
				accountService.getDao().findById(account.getAccountid())
						.getSearchpassword())) {
			request.setAttribute("info", "��ǰ�������벻��ȷ");
		} else if (!chPassword.equals(confirmPassword)) {
			request.setAttribute("info", "�������벻һ�£����������룡");

		} else if (chPassword.length() > 16) {
			request.setAttribute("info", "����������С��16�������ַ�");
		} else {
			account.setSearchpassword(chPassword);
			accountService.setSearchPassword(account);
			return "changeSearchPasswordSuccess";
		}
		return "changeSearchPasswordFailed";
	}
	
	/**
	 * ��ʾ���ÿ���
	 * 
	 * @return
	 */
	public String showCreditCard() {
		account.setClientid(1);
		session.setAttribute("clientId", 1);
		//
		request.setAttribute("accountIdList",
				accountService.getCdsOfClientByActive(account));
		return "showCreditCardSearchPasswordSet";
	}
	
	/**
	 * ѡ�񿨡�
	 * 
	 * @return
	 */
	public String selectCard() {
		session.setAttribute("creditcard", request.getParameter("selectCd"));
		return "selectSearchPassword";
	}
	
	@Override
	public Account getModel() {
		if (account == null) {
			account = new Account();
		}
		return account;
	}
}
