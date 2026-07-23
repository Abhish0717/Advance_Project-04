package com.sunilos.p4.ctl;

import java.util.List;

import com.sunilos.p4.bean.FaceRecognitionBean;
import com.sunilos.p4.model.FaceRecognitionModel;

import jakarta.servlet.annotation.WebServlet;

@WebServlet("/ctl/FaceRecognitionReportCtl")
public class FaceRecognitionReportCtl extends BaseReportCtl<FaceRecognitionBean> {

	@Override
	public String getView() {
		return ORSView.FACE_RECOGNITION_REPORT_VIEW;
	}

	@Override
	public String getCompiledReportKey() {
		return "FACE_RECOGNITION_LISTCOMPLIED_REPORT";
	}

	@Override
	public List<FaceRecognitionBean> getList() {
		FaceRecognitionModel model = new FaceRecognitionModel();
		return model.list();
	}
}