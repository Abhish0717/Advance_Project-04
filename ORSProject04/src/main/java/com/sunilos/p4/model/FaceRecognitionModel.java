package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.FaceRecognitionBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class FaceRecognitionModel extends BaseModel<FaceRecognitionBean> {

	@Override
	public long add(FaceRecognitionBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;

		FaceRecognitionBean existbean = findByName(bean.getUserName());

		if (existbean != null) {
			throw new DuplicateRecordException("User Name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getFaceCode());
			pstmt.setString(3, bean.getUserName());
			pstmt.setString(4, bean.getImagePath());
			pstmt.setString(5, bean.getStatus());
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
			throw new ApplicationException("Exception : Exception in add Face Recognition");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	@Override
	public void update(FaceRecognitionBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;
		FaceRecognitionBean existbean = findByName(bean.getUserName());

		if (existbean != null && existbean.getId() != bean.getId()) {
			throw new DuplicateRecordException("User Name already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction`
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set code = ?, name = ?, path = ?, status = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getFaceCode());
			pstmt.setString(2, bean.getUserName());
			pstmt.setString(3, bean.getImagePath());
			pstmt.setString(4, bean.getStatus());
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
			throw new ApplicationException("Exception : Exception in Updated Face Recognition");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public FaceRecognitionBean findByName(String name) throws ApplicationException {
		return findByUniqueColumn("name", name);
	}

	@Override
	public String getWhereClause(FaceRecognitionBean bean) {
		StringBuffer sql = new StringBuffer();
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getFaceCode() != null && bean.getFaceCode().length() > 0) {
				sql.append(" and code like '" + bean.getFaceCode() + "%'");
			}
			if (bean.getUserName() != null && bean.getUserName().length() > 0) {
				sql.append(" and name like '" + bean.getUserName() + "%'");
			}

			if (bean.getImagePath() != null && bean.getImagePath().length() > 0) {
				sql.append(" and path like '" + bean.getImagePath() + "%'");
			}
			if (bean.getStatus() != null && bean.getStatus().length() > 0) {
				sql.append(" and status like '" + bean.getStatus() + "%'");
			}
		}

		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_facerecognition";
	}

	@Override
	public FaceRecognitionBean getBean() {
		return new FaceRecognitionBean();
	}

}