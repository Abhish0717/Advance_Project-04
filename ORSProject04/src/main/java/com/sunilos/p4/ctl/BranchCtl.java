package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.BranchBean;
import com.sunilos.p4.model.BranchModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/BranchCtl")
public class BranchCtl extends BaseCtl<BranchBean, BranchModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("bname"))) {
			request.setAttribute("bname", PropertyReader.getValue("error.require", "Branch Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city", PropertyReader.getValue("error.require", "City"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("mname"))) {
			request.setAttribute("mname", PropertyReader.getValue("error.require", "Manager Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("contact"))) {
			request.setAttribute("contact", PropertyReader.getValue("error.require", "contact No."));
			pass = false;
		}

		return pass;
	}

	@Override
	protected BranchBean populateBean(HttpServletRequest request) {

		BranchBean bean = new BranchBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setBranchName(DataUtility.getString(request.getParameter("bname")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setManagerName(DataUtility.getString(request.getParameter("mname")));
		bean.setContactNo(DataUtility.getInt(request.getParameter("contact")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.BRANCH_VIEW;
	}

	@Override
	protected String getView(String op) {
		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.BRANCH_LIST_CTL;
		} else {
			return ORSView.BRANCH_VIEW;
		}
	}

	@Override
	protected BranchModel getModel() {
		return new BranchModel();
	}

}
