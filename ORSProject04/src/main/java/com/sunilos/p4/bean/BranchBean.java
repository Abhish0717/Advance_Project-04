package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BranchBean extends BaseBean {

	private String branchName;
	private String city;
	private String managerName;
	private int contactNo;

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public int getContactNo() {
		return contactNo;
	}

	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setBranchName(rs.getString("bname"));
			this.setCity(rs.getString("city"));
			this.setManagerName(rs.getString("mname"));
			this.setContactNo(rs.getInt("contact"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
