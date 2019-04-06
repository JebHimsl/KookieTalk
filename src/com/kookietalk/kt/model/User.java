package com.kookietalk.kt.model;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.kookietalk.kt.validations.ExpressionAssert;

public class User {

	private Integer userId;
	private String userName;
	@NotNull
	@NotEmpty
	@Email
	private String emailAddress;
	@NotNull
	@NotEmpty
	@Email
	private String confirmEmail;
	@NotNull
	@NotEmpty
	private String password;
	@NotNull
	@NotEmpty
	private String confirmPwd;
	@NotNull
	@NotEmpty
	private String firstName;
	@NotNull
	@NotEmpty
	private String lastName;
	private String middleName;
	private String nickName;
	private String mAddress1;
	private String mAddress2;
	private String mCity;
	private String mState;
	private String mCountry;
	private String mZip;
	private String bAddress1;
	private String bAddress2;
	private String bCity;
	private String bState;
	private String bCountry;
	private String bZip;
	private String hPhone;
	private String mPhone;
	private String wPhone;
	private int billingAddressId;
	private int mailingAddressId;
	private boolean mUse;
	@NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateOfBirth;
	private String role;
	@NotNull
	@NotEmpty
	private String timezoneId;
	private String cv;
	private CommonsMultipartFile[] image;

	public String getTimezoneId() {
		return timezoneId;
	}

	public void setTimezoneId(String timezoneId) {
		this.timezoneId = timezoneId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getBillingAddressId() {
		return billingAddressId;
	}

	public void setBillingAddressId(int billingAddressId) {
		this.billingAddressId = billingAddressId;
	}

	public int getMailingAddressId() {
		return mailingAddressId;
	}

	public void setMailingAddressId(int mailingAddressId) {
		this.mailingAddressId = mailingAddressId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getwPhone() {
		return wPhone;
	}

	public void setwPhone(String wPhone) {
		this.wPhone = wPhone;
	}

	public String getConfirmEmail() {
		return confirmEmail;
	}

	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getmAddress1() {
		return mAddress1;
	}

	public void setmAddress1(String mAddress1) {
		this.mAddress1 = mAddress1;
	}

	public String getmAddress2() {
		return mAddress2;
	}

	public void setmAddress2(String mAddress2) {
		this.mAddress2 = mAddress2;
	}

	public String getmCity() {
		return mCity;
	}

	public void setmCity(String mCity) {
		this.mCity = mCity;
	}

	public String getmState() {
		return mState;
	}

	public void setmState(String mState) {
		this.mState = mState;
	}

	public String getmZip() {
		return mZip;
	}

	public void setmZip(String mZip) {
		this.mZip = mZip;
	}

	public String getbAddress1() {
		return bAddress1;
	}

	public void setbAddress1(String bAddress1) {
		this.bAddress1 = bAddress1;
	}

	public String getbAddress2() {
		return bAddress2;
	}

	public void setbAddress2(String bAddress2) {
		this.bAddress2 = bAddress2;
	}

	public String getbCity() {
		return bCity;
	}

	public void setbCity(String bCity) {
		this.bCity = bCity;
	}

	public String getbState() {
		return bState;
	}

	public void setbState(String bState) {
		this.bState = bState;
	}

	public String getbZip() {
		return bZip;
	}

	public void setbZip(String bZip) {
		this.bZip = bZip;
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

	public boolean ismUse() {
		return mUse;
	}

	public void setmUse(boolean mUse) {
		this.mUse = mUse;
	}

	public String getmCountry() {
		return mCountry;
	}

	public void setmCountry(String mCountry) {
		this.mCountry = mCountry;
	}

	public String getbCountry() {
		return bCountry;
	}

	public void setbCountry(String bCountry) {
		this.bCountry = bCountry;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public CommonsMultipartFile[] getImage() {
		return image;
	}

	public void setImage(CommonsMultipartFile[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", emailAddress=" + emailAddress
				+ ", confirmEmail=" + confirmEmail + ", password=" + password + ", confirmPwd=" + confirmPwd
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName + ", nickName="
				+ nickName + ", mAddress1=" + mAddress1 + ", mAddress2=" + mAddress2 + ", mCity=" + mCity + ", mState="
				+ mState + ", mCountry=" + mCountry + ", mZip=" + mZip + ", bAddress1=" + bAddress1 + ", bAddress2="
				+ bAddress2 + ", bCity=" + bCity + ", bState=" + bState + ", bCountry=" + bCountry + ", bZip=" + bZip
				+ ", hPhone=" + hPhone + ", mPhone=" + mPhone + ", wPhone=" + wPhone + ", billingAddressId="
				+ billingAddressId + ", mailingAddressId=" + mailingAddressId + ", mUse=" + mUse + ", dateOfBirth="
				+ dateOfBirth + ", role=" + role + ", timezoneId=" + timezoneId + ", cv=" + cv + ", image="
				+ Arrays.toString(image) + "]";
	}

	

	
}
