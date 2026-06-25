package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TransactionBean extends BaseBean {
	private Date transactionDate;
	private Long amount;
	private String type;
	private String accountNo;

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return type;
	}

	@Override
	public void setResultset(ResultSet rs) {
		super.setResultset(rs);
		try {
			this.setTransactionDate(rs.getDate("transaction_date"));
			this.setAmount(rs.getLong("amount"));
			this.setType(rs.getString("type"));
			this.setAccountNo(rs.getString("account_no"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
