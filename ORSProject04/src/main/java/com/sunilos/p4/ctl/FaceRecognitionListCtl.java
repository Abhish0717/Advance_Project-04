package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.FaceRecognitionBean;
import com.sunilos.p4.model.FaceRecognitionModel;
import com.sunilos.p4.util.DataUtility;

@WebServlet("/ctl/FaceRecognitionListCtl")
public class FaceRecognitionListCtl extends BaseListCtl<FaceRecognitionBean, FaceRecognitionModel> {

	private static Logger log = Logger.getLogger(FaceRecognitionListCtl.class);

	@Override
	protected FaceRecognitionBean populateBean(HttpServletRequest request) {
		FaceRecognitionBean bean = new FaceRecognitionBean();
		bean.setFaceCode(DataUtility.getString(request.getParameter("code")));
		bean.setUserName(DataUtility.getString(request.getParameter("name")));
		bean.setImagePath(DataUtility.getString(request.getParameter("path")));
		bean.setStatus(DataUtility.getString(request.getParameter("status")));
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.FACE_RECOGNITION_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.FACE_RECOGNITION_LIST_VIEW;
	}

	@Override
	protected FaceRecognitionModel getModel() {
		return new FaceRecognitionModel();
	}

}