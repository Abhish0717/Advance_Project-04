package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.DoctorBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class DoctorModel extends BaseModel<DoctorBean> {

	@Override
	public long add(DoctorBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;

		DoctorBean existbean = findByName(bean.getDoctorName());

		if (existbean != null) {
			throw new DuplicateRecordException("Doctor Name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getDoctorName());
			pstmt.setString(3, bean.getSpecialization());
			pstmt.setString(4, bean.getExperience());
			pstmt.setString(5, bean.getContactNo());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Doctor Info");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	@Override
	public void update(DoctorBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;
		DoctorBean existbean = findByName(bean.getDoctorName());

		if (existbean != null && existbean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Doctor Name already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction`
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set name = ?, specialization = ?, experience = ?, contact_no = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getDoctorName());
			pstmt.setString(2, bean.getSpecialization());
			pstmt.setString(3, bean.getExperience());
			pstmt.setString(4, bean.getContactNo());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : Update rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in Updated Doctor Info");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public DoctorBean findByName(String name) throws ApplicationException {
		return findByUniqueColumn("name", name);
	}

	@Override
	public String getWhereClause(DoctorBean bean) {
		StringBuffer sql = new StringBuffer();
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getDoctorName() != null && bean.getDoctorName().length() > 0) {
				sql.append(" and name like '" + bean.getDoctorName() + "%'");
			}
			if (bean.getSpecialization() != null && bean.getSpecialization().length() > 0) {
				sql.append(" and specialization like '" + bean.getSpecialization() + "%'");
			}
			if (bean.getExperience() != null && bean.getExperience().length() > 0) {
				sql.append(" and experience like '" + bean.getExperience() + "%'");
			}
			if (bean.getContactNo() != null && bean.getContactNo().length() > 0) {
				sql.append(" and contact_no like '" + bean.getContactNo() + "%'");
			}
		}

		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_doctor";
	}

	@Override
	public DoctorBean getBean() {
		return new DoctorBean();
	}

}