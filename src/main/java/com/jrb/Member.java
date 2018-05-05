package com.jrb;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author
 */

public class Member {

	private String memid;

	private String lastname;

	private String firstname;

	private String middlename;

	private String status;

	private Date memdt;

	private Date expdt;

	private Date ytdtotdt;

	private double ytdtot;

	private long password;

	private long passwordatt;

	public Member() {
		this.memid = "";
		this.lastname = "";
		this.firstname = "";
		this.middlename = "";
		this.status = "";
		this.memdt = null;
		this.expdt = null;
		this.ytdtotdt = null;
		this.ytdtot = 0;
		this.password = -1;
		this.passwordatt = 0;
	}

	public String getMemid() {
		return memid;
	}

	public void setMemid(String memid) {
		this.memid = memid;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getMemdt() {
		return memdt;
	}

	public void setMemdt(Date memdt) {
		this.memdt = memdt;
	}

	public String getMemdtS() {
		return new SimpleDateFormat("MM-dd-yyyy").format(this.memdt);
	}

	public Date getExpdt() {
		return expdt;
	}

	public void setExpdt(Date expdt) {
		this.expdt = expdt;
	}

	public String getExpdtS() {
		return new SimpleDateFormat("MM-dd-yyyy").format(this.expdt);
	}

	public Date getYtdtotdt() {
		return ytdtotdt;
	}

	public void setYtdtotdt(Date ytdtotdt) {
		this.ytdtotdt = ytdtotdt;
	}

	public String getYtdtotdtS() {
		return ytdtotdt.toString();
	}

	public double getYtdtot() {
		return ytdtot;
	}

	public void setYtdtot(double ytdtot) {
		this.ytdtot = ytdtot;
	}

	public long getPassword() {
		return password;
	}

	public void setPassword(long password) {
		this.password = password;
	}

	public void setPasswordatt(long pwd) {
		this.passwordatt = pwd;
	}

	public boolean isAuthenticated() {
		if (this.password > 0) {
			if (this.password == this.passwordatt) {
				return true;
			}
		}
		return false;
	}
	public void renew() {
		int yr = this.expdt.getYear(); //fixme
		yr++;
		
	}

	public String toString() {
		return this.memid + ", " + this.lastname + ", " + this.firstname + ", " + this.memdt +
				", " + this.expdt + ", " + this.password + ", " + this.status;
	}

}