package com.sunilos.p4.ctl;

import java.util.List;

import com.sunilos.p4.bean.HospitalBean;
import com.sunilos.p4.model.HospitalModel;

import jakarta.servlet.annotation.WebServlet;

@WebServlet("/ctl/HospitalReportCtl")
public class HospitalReportCtl extends BaseReportCtl<HospitalBean> {

	@Override
	public String getView() {
		return ORSView.HOSPITAL_REPORT_VIEW;
	}

	@Override
	public String getCompiledReportKey() {
		return "HOSPITAL_LISTCOMPLIED_REPORT";
	}

	@Override
	public List<HospitalBean> getList() {
		HospitalModel model = new HospitalModel();
		@SuppressWarnings("unchecked")
		List<HospitalBean> doctor = model.list();
		return doctor;
	}

}
