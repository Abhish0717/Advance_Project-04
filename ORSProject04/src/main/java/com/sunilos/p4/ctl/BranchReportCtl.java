package com.sunilos.p4.ctl;

import java.util.List;

import com.sunilos.p4.bean.BranchBean;
import com.sunilos.p4.model.BranchModel;

import jakarta.servlet.annotation.WebServlet;

@WebServlet("/ctl/BranchReportCtl")
public class BranchReportCtl extends BaseReportCtl<BranchBean> {

	@Override
	public String getView() {
		return ORSView.BRANCH_REPORT_VIEW;
	}

	@Override
	public String getCompiledReportKey() {
		return "BRANCH_LIST_COMPILED_REPORT";
	}

	@Override
	public List<BranchBean> getList() {
		BranchModel model = new BranchModel();
		@SuppressWarnings("unchecked")
		List<BranchBean> product = model.list();
		return product;
	}

}
