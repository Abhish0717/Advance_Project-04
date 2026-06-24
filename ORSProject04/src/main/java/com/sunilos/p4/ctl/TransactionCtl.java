package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.TransactionBean;
import com.sunilos.p4.model.TransactionModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/TransactionCtl")
public class TransactionCtl extends BaseCtl<TransactionBean, TransactionModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("transaction"))) {
			request.setAttribute("transaction", PropertyReader.getValue("error.require", "Transaction Date"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("amount"))) {
			request.setAttribute("amount", PropertyReader.getValue("error.require", "Amount"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("type"))) {
			request.setAttribute("type", PropertyReader.getValue("error.require", "Type"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("account"))) {
			request.setAttribute("account", PropertyReader.getValue("error.require", "Account Number"));
			pass = false;
		} 
//		else if (!DataValidator.isName(request.getParameter("account"))) {
//			request.setAttribute("account", "Invalid Account Number");
//			pass = false;
//		}

		return pass;
	}

	@Override
	protected TransactionBean populateBean(HttpServletRequest request) {

		TransactionBean bean = new TransactionBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setTransactionDate(DataUtility.getDate(request.getParameter("transaction")));
		bean.setAmount(DataUtility.getLong(request.getParameter("amount")));
		bean.setType(DataUtility.getString(request.getParameter("type")));
		bean.setAccountNo(DataUtility.getString(request.getParameter("account")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.TRANSACTION_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op)) {
			return ORSView.TRANSACTION_LIST_CTL;

		} else {
			return ORSView.TRANSACTION_VIEW;
		}
	}

	@Override
	protected TransactionModel getModel() {
		return new TransactionModel();
	}

}