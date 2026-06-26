package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorBean extends BaseBean {
	private String DoctorName;
	private String specialization;
	private String experience;
	private String contactNo;

	public String getDoctorName() {
		return DoctorName;
	}

	public void setDoctorName(String doctorName) {
		DoctorName = doctorName;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return DoctorName;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setDoctorName(rs.getString("name"));
			this.setSpecialization(rs.getString("specialization"));
			this.setExperience(rs.getString("experience"));
			this.setContactNo(rs.getString("contact_no"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
