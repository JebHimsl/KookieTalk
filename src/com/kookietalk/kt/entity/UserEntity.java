package com.kookietalk.kt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@Column(name = "USER_ID")
	private int userId;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "EMAIL_ADDRESS")
	private String emailAddress;
	@Column(name = "FIRST_NAME")
	private String fName;
	@Column(name = "LAST_NAME")
	private String lName;
	@Column(name = "MIDDLE_NAME")
	private String mName;
	@Column(name = "NICK_NAME")
	private String nName;
	@Column(name = "HOME_PHONE")
	private String hPhone;
	@Column(name = "MOBILE_PHONE")
	private String mPhone;
	@Column(name = "WORK_PHONE")
	private String wPhone;
	@Column(name = "MAILING_ADDRESS_ID")
	private int mailingAddressId;
	@Column(name = "TIMEZONE_ID_ID")
	private int TimeZoneId;

	public int getTimeZoneId() {
		return TimeZoneId;
	}

	public void setTimeZoneId(int timeZoneId) {
		TimeZoneId = timeZoneId;
	}

	public int getMailingAddressId() {
		return mailingAddressId;
	}

	public void setMailingAddressId(int mailingAddressId) {
		this.mailingAddressId = mailingAddressId;
	}

	public int getBillingAddressId() {
		return billingAddressId;
	}

	public void setBillingAddressId(int billingAddressId) {
		this.billingAddressId = billingAddressId;
	}

	@Column(name = "BILLING_ADDRESS_ID")
	private int billingAddressId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getnName() {
		return nName;
	}

	public void setnName(String nName) {
		this.nName = nName;
	}

	public String gethPhone() {
		return hPhone;
	}

	public void sethPhone(String hPhone) {
		this.hPhone = hPhone;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public String getwPhone() {
		return wPhone;
	}

	public void setwPhone(String wPhone) {
		this.wPhone = wPhone;
	}

}
