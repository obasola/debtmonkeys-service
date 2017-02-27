/*
 * Created on 26 Feb 2017 ( Time 16:22:54 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.debtmonkeys.service.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@NotNull
	private Integer id;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------
	@NotNull
	@Size(min = 1, max = 45)
	private String accountName;

	@NotNull
	private Integer accountTypeId;

	private Double originalBalance;

	private Double currentBalance;

	private Double minPaymentDue;

	private Date dateLastPayment;

	private Date dateNextPaymentDue;

	private Date dateOpened;

	private Date dateClosed;

	private Date dateCreated;

	private Date dateModified;

	private Byte autoPayment;

	@Size(max = 65)
	private String webSite;

	@NotNull
	private Integer accountOwnerId;

	private AccountOwner accountOwner;
	private AccountType accountType;

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountTypeId(Integer accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public Integer getAccountTypeId() {
		return this.accountTypeId;
	}

	public void setOriginalBalance(Double originalBalance) {
		this.originalBalance = originalBalance;
	}

	public Double getOriginalBalance() {
		return this.originalBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Double getCurrentBalance() {
		return this.currentBalance;
	}

	public void setMinPaymentDue(Double minPaymentDue) {
		this.minPaymentDue = minPaymentDue;
	}

	public Double getMinPaymentDue() {
		return this.minPaymentDue;
	}

	public void setDateLastPayment(Date dateLastPayment) {
		this.dateLastPayment = dateLastPayment;
	}

	public Date getDateLastPayment() {
		return this.dateLastPayment;
	}

	public void setDateNextPaymentDue(Date dateNextPaymentDue) {
		this.dateNextPaymentDue = dateNextPaymentDue;
	}

	public Date getDateNextPaymentDue() {
		return this.dateNextPaymentDue;
	}

	public void setDateOpened(Date dateOpened) {
		this.dateOpened = dateOpened;
	}

	public Date getDateOpened() {
		return this.dateOpened;
	}

	public void setDateClosed(Date dateClosed) {
		this.dateClosed = dateClosed;
	}

	public Date getDateClosed() {
		return this.dateClosed;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public Date getDateModified() {
		return this.dateModified;
	}

	public void setAutoPayment(Byte autoPayment) {
		this.autoPayment = autoPayment;
	}

	public Byte getAutoPayment() {
		return this.autoPayment;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getWebSite() {
		return this.webSite;
	}

	public void setAccountOwnerId(Integer accountOwnerId) {
		this.accountOwnerId = accountOwnerId;
	}

	public Integer getAccountOwnerId() {
		return this.accountOwnerId;
	}
	
	

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public AccountOwner getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(AccountOwner accountOwner) {
		this.accountOwner = accountOwner;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(id);
		sb.append("|");
		sb.append(accountName);
		sb.append("|");
		sb.append(accountTypeId);
		sb.append("|");
		sb.append(originalBalance);
		sb.append("|");
		sb.append(currentBalance);
		sb.append("|");
		sb.append(minPaymentDue);
		sb.append("|");
		sb.append(dateLastPayment);
		sb.append("|");
		sb.append(dateNextPaymentDue);
		sb.append("|");
		sb.append(dateOpened);
		sb.append("|");
		sb.append(dateClosed);
		sb.append("|");
		sb.append(dateCreated);
		sb.append("|");
		sb.append(dateModified);
		sb.append("|");
		sb.append(autoPayment);
		sb.append("|");
		sb.append(webSite);
		sb.append("|");
		sb.append(accountOwnerId);
		return sb.toString();
	}

}