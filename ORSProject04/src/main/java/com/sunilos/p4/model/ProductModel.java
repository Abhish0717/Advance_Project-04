package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.ProductBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DatabaseException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class ProductModel extends BaseModel<ProductBean> {

	/**
	 * Add a Product
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	@Override
	public long add(ProductBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		ProductBean existbean = findByName(bean.getProductName());

		if (existbean != null) {
			throw new DuplicateRecordException("product Name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getProductName());
			pstmt.setString(3, bean.getProductCategory());
			pstmt.setDate(4, new java.sql.Date(bean.getOrderDate().getTime()));
			pstmt.setInt(5, bean.getPrice());
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
			throw new ApplicationException("Exception : Exception in add Product");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add Successfully and Code End");
		return pk;
	}

	/**
	 * Update a Role
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */
	@Override
	public void update(ProductBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model Update Started");
		Connection conn = null;
		int pk = 0;

		ProductBean existbean = findByName(bean.getProductName());

		if (existbean != null) {
			throw new DuplicateRecordException("product Name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_product set name=?,category=?,order_date=?,price=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getProductName());
			pstmt.setString(2, bean.getProductCategory());
			pstmt.setDate(3, new java.sql.Date(bean.getOrderDate().getTime()));
			pstmt.setInt(4, bean.getPrice());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : Update rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in Updated Product");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model Updated Successfully and Code End");

	}

	@Override
	public String getWhereClause(ProductBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getProductName() != null && bean.getProductName().length() > 0) {
				sql.append(" and name like '" + bean.getProductName() + "%'");
			}
			if (bean.getProductCategory() != null && bean.getProductCategory().length() > 0) {
				sql.append(" and category like '" + bean.getProductCategory() + "%'");
			}
			if (bean.getOrderDate() != null && bean.getOrderDate().getTime() > 0) {
				sql.append(" and order_date = " + new java.sql.Date(bean.getOrderDate().getTime()));
			}
			if (bean.getPrice() > 0) {
				sql.append(" and price = " + bean.getPrice());
			}

		}

		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_product";
	}

	@Override
	public ProductBean getBean() {
		return new ProductBean();
	}

	private static Logger log = Logger.getLogger(RoleModel.class);

	/**
	 * Find User by Role
	 * 
	 * @param name : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public ProductBean findByName(String name) throws ApplicationException {
		return findByUniqueColumn("name", name);
	}

}
