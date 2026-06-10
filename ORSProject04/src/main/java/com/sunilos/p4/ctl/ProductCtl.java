package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.ProductBean;
import com.sunilos.p4.model.ProductModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/ProductCtl")
public class ProductCtl extends BaseCtl<ProductBean, ProductModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("product"))) {
			request.setAttribute("product", PropertyReader.getValue("error.require", "Product Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("category"))) {
			request.setAttribute("category", PropertyReader.getValue("error.require", "Product Category"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("order_date"))) {
			request.setAttribute("order_date", PropertyReader.getValue("error.require", "Order Date"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("order_date"))) {
			request.setAttribute("order_date", "Invalid Order Date");

		}

		if (DataValidator.isNull(request.getParameter("price"))) {
			request.setAttribute("price", PropertyReader.getValue("error.require", "Price"));
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("price"))) {
			request.setAttribute("price", "Invalid Price Value");

		}

		return pass;
	}

	@Override
	protected ProductBean populateBean(HttpServletRequest request) {

		ProductBean bean = new ProductBean();

		bean.setProductName(DataUtility.getString(request.getParameter("product")));
		bean.setProductCategory(DataUtility.getString(request.getParameter("category")));
		bean.setOrderDate(DataUtility.getDate(request.getParameter("order_date")));
		bean.setPrice(DataUtility.getInt(request.getParameter("price")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.PRODUCT_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op)) {
			return ORSView.PRODUCT_CTL;
		}
		return ORSView.PRODUCT_VIEW;
	}

	@Override
	protected ProductModel getModel() {
		return new ProductModel();
	}

}