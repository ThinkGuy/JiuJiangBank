package com.ob.action;

import com.ob.model.Account;
import com.ob.service.AccountService;
import com.opensymphony.xwork2.ModelDriven;

public class CreditcardDealPasswordSetAction extends SuperAction implements
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

		if (!account.getDealpassword().equals(
				accountService.getDao().findById(account.getAccountid())
						.getDealpassword())) {
			request.setAttribute("info", "��ǰ�������벻��ȷ");
		} else if (!chPassword.equals(confirmPassword)) {
			request.setAttribute("info", "�������벻һ�£����������룡");

		} else if (!chPassword.matches("^[0-9]{6}$")) {
			request.setAttribute("info", "����������6������");
		} else {
			account.setDealpassword(Integer.parseInt((chPassword)));
			accountService.setDealPassword(account);
			return "dealPasswordSetSuccess";
		}
		return "dealPasswordSetFailed";
	}
	
	/**
	 * ����ȷ�ϡ�
	 * @return
	 */
	public String confirmPassword() {
		account.setAccountid(session.getAttribute("creditcard").toString());
		
		if (!account.getDealpassword().equals(
				accountService.getDao().findById(account.getAccountid())
						.getDealpassword())) {
			request.setAttribute("info", "��ǰ�������벻��ȷ");
			return "confirmFailedCancelDPWD";
		}
		
		cancelDealPassword(account);
		return "confirmSuccessCancelDPWD";
		
	}
	
	public void cancelDealPassword(Account account) {
		accountService.cancelDealPassword(account);
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
		return "showCreditCardDealPasswordSet";
	}

	/**
	 * ѡ�񿨡�
	 * 
	 * @return
	 */
	public String selectCard() {
		session.setAttribute("creditcard", request.getParameter("selectCd"));
		
		if ("changeDPWD".equals(request.getParameter("dealPWDOperation"))) {
			return "selectChangeDealPassword";
		} else if ("cancelDPWD".equals(request.getParameter("dealPWDOperation"))) {
			return "selectCancelDealPassword";
		} 
		
		return "selectFaildDealPassword";
	}

	@Override
	public Account getModel() {
		if (account == null) {
			account = new Account();
		}
		return account;
	}
}
