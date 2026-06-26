package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.DoctorBean;
import com.sunilos.p4.model.DoctorModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/DoctorCtl")
public class DoctorCtl extends BaseCtl<DoctorBean, DoctorModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Doctor Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", "Invalid Doctor Name");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("specialization"))) {
			request.setAttribute("specialization", PropertyReader.getValue("error.require", "Specialization"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("experience"))) {
			request.setAttribute("experience", PropertyReader.getValue("error.require", "Experience"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("contact"))) {
			request.setAttribute("contact", PropertyReader.getValue("error.require", "Contact No."));
			pass = false;
		} else if (!DataValidator.isPhoneLength(request.getParameter("contact"))) {
			request.setAttribute("contact", "Contact No. must have 10 digits");
			pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("mobileNo"))) {
			request.setAttribute("contact", "Invalid Contact No");
			pass = false;
		}
		return pass;
	}

	@Override
	protected DoctorBean populateBean(HttpServletRequest request) {

		DoctorBean bean = new DoctorBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setDoctorName(DataUtility.getString(request.getParameter("name")));
		bean.setSpecialization(DataUtility.getString(request.getParameter("specialization")));
		bean.setExperience(DataUtility.getString(request.getParameter("experience")));
		bean.setContactNo(DataUtility.getString(request.getParameter("contact")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.DOCTOR_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op)) {
			return ORSView.DOCTOR_LIST_CTL;

		} else {
			return ORSView.DOCTOR_VIEW;
		}
	}

	@Override
	protected DoctorModel getModel() {
		return new DoctorModel();
	}

}