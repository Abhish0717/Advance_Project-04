package com.sunilos.p4.model.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.sunilos.p4.bean.ProductBean;
import com.sunilos.p4.model.ProductModel;
import com.sunilos.p4.util.DataUtility;

public class TestProductModel {

	public static void main(String[] args) throws ParseException {
//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindByUniqueColumn();
		testSearch();
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

	private static void testUpdate() throws ParseException {
		ProductModel model = new ProductModel();
		ProductBean bean = new ProductBean();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		bean.setId(1);
		bean.setProductName("Lenovo ideapad gaming 3");
		bean.setProductCategory("Laptop");
		bean.setOrderDate(sdf.parse("05/15/2026"));
		bean.setPrice(55000);
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(bean);

	}

	private static void testDelete() {

		ProductModel model = new ProductModel();
		ProductBean bean = new ProductBean();
		bean.setId(3);

		model.delete(bean);
	}

	private static void testFindByPk() {

		ProductModel model = new ProductModel();

		try {

			ProductBean bean = model.findByPK(1);

			if (bean != null) {

				System.out.println("ID : " + bean.getId());
				System.out.println("Name : " + bean.getProductName());
				System.out.println("Category : " + bean.getProductCategory());
				System.out.println("Order Date : " + bean.getOrderDate());
				System.out.println("Price : " + bean.getPrice());

			} else {

				System.out.println("Record Not Found");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testFindByUniqueColumn() {

		ProductModel model = new ProductModel();

		try {

			ProductBean bean = model.findByName("Lenovo ideapad gaming 3");

			if (bean != null) {

				System.out.println("ID : " + bean.getId());
				System.out.println("Name : " + bean.getProductName());
				System.out.println("Category : " + bean.getProductCategory());
				System.out.println("Order Date : " + bean.getOrderDate());
				System.out.println("Price : " + bean.getPrice());

			} else {

				System.out.println("Record Not Found");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testSearch() {

		ProductModel model = new ProductModel();

		try {

			ProductBean bean = new ProductBean();

			// Search by category
			bean.setProductCategory("Laptop");

			List<ProductBean> list = model.search(bean);

			Iterator<ProductBean> it = list.iterator();

			while (it.hasNext()) {

				bean = (ProductBean) it.next();

				System.out.println("ID : " + bean.getId());
				System.out.println("Name : " + bean.getProductName());
				System.out.println("Category : " + bean.getProductCategory());
				System.out.println("Order Date : " + bean.getOrderDate());
				System.out.println("Price : " + bean.getPrice());

				System.out.println("--------------------------------");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
