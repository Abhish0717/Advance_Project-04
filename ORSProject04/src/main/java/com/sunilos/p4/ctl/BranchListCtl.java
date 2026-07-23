package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.BranchBean;
import com.sunilos.p4.model.BranchModel;
import com.sunilos.p4.util.DataUtility;

@WebServlet("/ctl/BranchListCtl")
public class BranchListCtl extends BaseListCtl<BranchBean, BranchModel> {

    private static Logger log = Logger.getLogger(BranchListCtl.class);

    @Override
    protected BranchBean populateBean(HttpServletRequest request) {
        BranchBean bean = new BranchBean();
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
        return ORSView.BRANCH_LIST_VIEW;
    }

    @Override
    protected BranchModel getModel() {
        return new BranchModel();
    }

}