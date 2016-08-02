package com.ob.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.ob.dao.DealinformDAO;
import com.ob.model.Dealinform;
import com.ob.service.DealinformService;

public class DealinformServiceImpl implements DealinformService {
	private DealinformDAO dealinformDAO;
	
	public void setDealinformDAO(DealinformDAO dealinformDAO) {
		this.dealinformDAO = dealinformDAO;
	}

	/**
	 * ����δ���˵����ϡ�
	 */
	@Override
	public ArrayList<Dealinform> getBillYetDealInfo(String accountId) {
		ArrayList<Dealinform> dealinforms = new ArrayList<Dealinform>();
		ArrayList<Dealinform> dealInfoList = (ArrayList<Dealinform>)dealinformDAO.findByAccountid(accountId);
		//�˵���������Ϊһ���¡�
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		date = calendar.getTime();
		for (Dealinform dealinform : dealInfoList) {
			if ((dealinform.getDealtime().compareTo(date) > 0)) {
				//��ȡ������λ��
				int index = dealinform.getAccountid().length();
				dealinform.setAccountid(dealinform.getAccountid().substring(index-4, index));
				dealinforms.add(dealinform);
			}
		}
		
		return dealinforms;
	}

	/**
	 * δ���˵���ѯ��
	 */
	@Override
	public ArrayList<Dealinform> getBillHaveDealInfo(String accountId) {
		ArrayList<Dealinform> dealinforms = new ArrayList<Dealinform>();
		ArrayList<Dealinform> dealInfoList = (ArrayList<Dealinform>)dealinformDAO.findByAccountid(accountId);
		//�˵���������Ϊһ���¡�
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		date = calendar.getTime();
		for (Dealinform dealinform : dealInfoList) {
			if (!(dealinform.getDealtime().compareTo(date) > 0)) {
				//��ȡ������λ��
				int index = dealinform.getAccountid().length();
				dealinform.setAccountid(dealinform.getAccountid().substring(index-4, index));
				dealinforms.add(dealinform);
			}
		}
		
		return dealinforms;
	}

}
