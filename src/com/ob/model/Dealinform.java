package com.ob.model;

import java.util.Date;

/**
 * Dealinform entity. @author MyEclipse Persistence Tools
 */

public class Dealinform implements java.io.Serializable {

	// Fields

	private String dealid;
	private String accountid;
	private Date dealtime;
	private String dealinform;
	private Integer dealtype;
	private String dealDesti;
	private Integer dealstate;
	private Integer dealamount;

	// Constructors

	/** default constructor */
	public Dealinform() {
	}

	/** full constructor */
	public Dealinform(String dealid, String accountid, Date dealtime,
			String dealinform, Integer dealtype, String dealDesti,
			Integer dealstate, Integer dealamount) {
		this.dealid = dealid;
		this.accountid = accountid;
		this.dealtime = dealtime;
		this.dealinform = dealinform;
		this.dealtype = dealtype;
		this.dealDesti = dealDesti;
		this.dealstate = dealstate;
		this.dealamount = dealamount;
	}

	// Property accessors

	public String getDealid() {
		return this.dealid;
	}

	public void setDealid(String dealid) {
		this.dealid = dealid;
	}

	public String getAccountid() {
		return this.accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public Date getDealtime() {
		return this.dealtime;
	}

	public void setDealtime(Date dealtime) {
		this.dealtime = dealtime;
	}

	public String getDealinform() {
		return this.dealinform;
	}

	public void setDealinform(String dealinform) {
		this.dealinform = dealinform;
	}

	public Integer getDealtype() {
		return this.dealtype;
	}

	public void setDealtype(Integer dealtype) {
		this.dealtype = dealtype;
	}

	public String getDealDesti() {
		return this.dealDesti;
	}

	public void setDealDesti(String dealDesti) {
		this.dealDesti = dealDesti;
	}

	public Integer getDealstate() {
		return this.dealstate;
	}

	public void setDealstate(Integer dealstate) {
		this.dealstate = dealstate;
	}

	public Integer getDealamount() {
		return this.dealamount;
	}

	public void setDealamount(Integer dealamount) {
		this.dealamount = dealamount;
	}

	@Override
	public String toString() {
		return "Dealinform [dealid=" + dealid + ", accountid=" + accountid
				+ ", dealtime=" + dealtime + ", dealinform=" + dealinform
				+ ", dealtype=" + dealtype + ", dealDesti=" + dealDesti
				+ ", dealstate=" + dealstate + ", dealamount=" + dealamount
				+ "]";
	}
}