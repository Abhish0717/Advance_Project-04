package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.FaceRecognitionBean;
import com.sunilos.p4.model.FaceRecognitionModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/FaceRecognitionCtl")
public class FaceRecognitionCtl extends BaseCtl<FaceRecognitionBean, FaceRecognitionModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("code"))) {
			request.setAttribute("code", PropertyReader.getValue("error.require", "Face Code"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "User name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("path"))) {
			request.setAttribute("path", PropertyReader.getValue("error.require", "Image Path"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("status"))) {
			request.setAttribute("status", PropertyReader.getValue("error.require", "status"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected FaceRecognitionBean populateBean(HttpServletRequest request) {

		FaceRecognitionBean bean = new FaceRecognitionBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setFaceCode(DataUtility.getString(request.getParameter("code")));
		bean.setUserName(DataUtility.getString(request.getParameter("name")));
		bean.setImagePath(DataUtility.getString(request.getParameter("path")));
		bean.setStatus(DataUtility.getString(request.getParameter("status")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.FACE_RECOGNITION_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.FACE_RECOGNITION_LIST_CTL;

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			return ORSView.FACE_RECOGNITION_CTL;
		} else {
			return ORSView.FACE_RECOGNITION_VIEW;
		}
	}

	@Override
	protected FaceRecognitionModel getModel() {
		return new FaceRecognitionModel();
	}

}