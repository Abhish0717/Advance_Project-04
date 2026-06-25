package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.TransactionBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class TransactionModel extends BaseModel<TransactionBean> {

	@Override
	public long add(TransactionBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;

		TransactionBean existbean = findByNumber(bean.getAccountNo());

		if (existbean != null) {
			throw new DuplicateRecordException("Account No. already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO " + getTable() + " VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setDate(2, new java.sql.Date(bean.getTransactionDate().getTime()));
			pstmt.setLong(3, bean.getAmount());
			pstmt.setString(4, bean.getType());
			pstmt.setString(5, bean.getAccountNo());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Transaction");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	@Override
	public void update(TransactionBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;
		TransactionBean existbean = findByNumber(bean.getAccountNo());

		if (existbean != null && existbean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Account No already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction`
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set transaction_date = ?, amount = ?, type = ?, account_no = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setDate(1, new java.sql.Date(bean.getTransactionDate().getTime()));
			pstmt.setLong(2, bean.getAmount());
			pstmt.setString(3, bean.getType());
			pstmt.setString(4, bean.getAccountNo());
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
			throw new ApplicationException("Exception : Exception in Updated Transaction");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public TransactionBean findByNumber(String accountNo) throws ApplicationException {
		return findByUniqueColumn("account_no", accountNo);
	}

	@Override
	public String getWhereClause(TransactionBean bean) {
		StringBuffer sql = new StringBuffer();
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getTransactionDate() != null && bean.getTransactionDate().getTime() > 0) {
				sql.append(" and transaction_date = " + new java.sql.Date(bean.getTransactionDate().getTime()));
			}
			if (bean.getAmount() > 0) {
				sql.append(" and amount = " + bean.getAmount());
			}
			if (bean.getType() != null && bean.getType().length() > 0) {
				sql.append(" and type like '" + bean.getType() + "%'");
			}
			if (bean.getAccountNo() != null && bean.getAccountNo().length() > 0) {
				sql.append(" and account_no like '" + bean.getAccountNo() + "%'");
			}
		}

		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_transaction";
	}

	@Override
	public TransactionBean getBean() {
		return new TransactionBean();
	}

}