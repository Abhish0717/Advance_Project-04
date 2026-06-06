package com.sunilos.p4.model.test;

import java.sql.Timestamp;
import java.util.Date;

import com.sunilos.p4.bean.ProductBean;
import com.sunilos.p4.model.ProductModel;
import com.sunilos.p4.util.DataUtility;

public class TestProductModel {

	public static void main(String[] args) {
		testAdd();
	}

	private static void testAdd() {
		ProductModel model = new ProductModel();

		ProductBean bean = new ProductBean();

		bean.setProductName("Lenovo ideapa gaming 3");
		bean.setProductCategory("Laptop");
		bean.setOrderDate(DataUtility.getDate("02/02/2002"));
		bean.setPrice(55000);
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}
}
