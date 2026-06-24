package com.sunilos.p4.ctl;

import java.util.List;

import com.sunilos.p4.bean.TransactionBean;
import com.sunilos.p4.model.TransactionModel;

import jakarta.servlet.annotation.WebServlet;

@WebServlet("/ctl/TransactionReportCtl")
public class TransactionReportCtl extends BaseReportCtl<TransactionBean>{

	@Override
	public String getView() {
		return ORSView.TRANSACTION_REPORT_VIEW;
	}

	@Override
	public String getCompiledReportKey() {
		return "TRANSACTION_LIST_COMPILED_REPORT";
	}

	@Override
	public List<TransactionBean> getList() {
		TransactionModel model = new TransactionModel();
		@SuppressWarnings("unchecked")
		List<TransactionBean> product = model.list();
		return product;
	}

}
