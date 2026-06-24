package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.TransactionBean;
import com.sunilos.p4.model.TransactionModel;
import com.sunilos.p4.util.DataUtility;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/TransactionListCtl")
public class TransactionListCtl extends BaseListCtl<TransactionBean, TransactionModel> {

    @Override
    protected TransactionBean populateBean(HttpServletRequest request) {
        TransactionBean bean = new TransactionBean();
        bean.setTransactionDate(DataUtility.getDate(request.getParameter("transaction")));
		bean.setAmount(DataUtility.getLong(request.getParameter("amount")));
		bean.setType(DataUtility.getString(request.getParameter("type")));
		bean.setAccountNo(DataUtility.getString(request.getParameter("account")));
        return bean;
    }

    @Override
    protected String getView() {
        return ORSView.TRANSACTION_LIST_VIEW;
    }

    @Override
    protected String getView(String op) {
        return ORSView.TRANSACTION_LIST_VIEW;
    }

    @Override
    protected TransactionModel getModel() {
        return new TransactionModel();
    }

}