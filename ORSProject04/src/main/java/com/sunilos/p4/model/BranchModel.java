package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.PaymentBean;
import com.sunilos.p4.bean.BranchBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class BranchModel extends BaseModel<BranchBean> {

	@Override
	public BranchBean getBean() {
		return new BranchBean();
	}

	@Override
	public long add(BranchBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		BranchBean existbean = findByBranchName(bean.getBranchName());

		if (existbean != null) {
			throw new DuplicateRecordException("Branch Name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO " + getTable() + " VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getBranchName());
			pstmt.setString(3, bean.getCity());
			pstmt.setString(4, bean.getManagerName());
			pstmt.setInt(5, bean.getContactNo());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in Add Branch");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	@Override
	public void update(BranchBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;
		BranchBean existbean = findByBranchName(bean.getBranchName());

		if (existbean != null && existbean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Branch Name already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
//			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction`
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set bname = ?, city = ?, mname = ?, contact = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getBranchName());
			pstmt.setString(2, bean.getCity());
			pstmt.setString(3, bean.getManagerName());
			pstmt.setInt(4, bean.getContactNo());
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
			throw new ApplicationException("Exception : Exception in Updated Branch");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	@Override
	public String getWhereClause(BranchBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getBranchName() != null && bean.getBranchName().length() > 0) {
				sql.append(" AND bname like '" + bean.getBranchName() + "%'");
			}
			if (bean.getCity() != null && bean.getCity().length() > 0) {
				sql.append(" AND city like '" + bean.getCity() + "%'");
			}
			if (bean.getManagerName() != null && bean.getManagerName().length() > 0) {
				sql.append(" AND mname = " + bean.getManagerName());
			}
			if (bean.getContactNo() > 0) {
				sql.append(" AND contact = " + bean.getContactNo());
			}
		}

		return sql.toString();
	}

	public BranchBean findByBranchName(String productName) {
		return findByUniqueColumn("bname", productName);
	}

	@Override
	public String getTable() {
		return "st_branch";
	}

}
