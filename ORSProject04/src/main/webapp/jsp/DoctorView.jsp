<%@page import="com.sunilos.p4.util.MessageSource"%>
<%@page import="com.sunilos.p4.ctl.BaseCtl"%>
<%@page import="com.sunilos.p4.ctl.ORSView"%>
<%@page import="com.sunilos.p4.util.DataUtility"%>
<%@page import="com.sunilos.p4.util.ServletUtility"%>
<%@page import="java.util.List"%>

<jsp:useBean id="bean" class="com.sunilos.p4.bean.DoctorBean"
	scope="request"></jsp:useBean>

<%
MessageSource ms = MessageSource.getInstance();
String _suc = ServletUtility.getSuccessMessage(request);
String _err = ServletUtility.getErrorMessage(request);
%>

<div class="container py-4" style="max-width: 580px;">
	<div class="card border-0 shadow-sm rounded-4 overflow-hidden">

		<div class="card-header text-white border-0 py-3 px-4"
			style="background: linear-gradient(135deg, #0d2137 0%, #1565c0 100%);">
			<h5 class="mb-0 fw-bold">
				<i class="bi bi-prescription2 me-2"></i>
				<%=bean.getId() > 0 ? ms.get("doctor.edit") : ms.get("doctor.add")%>
			</h5>
		</div>

		<div class="card-body px-4 py-4">

			<%
			if (_suc != null && !_suc.isEmpty()) {
			%>
			<div class="alert alert-success py-2">
				<i class="bi bi-check-circle-fill me-2"></i><%=_suc%></div>
			<%
			}
			%>
			<%
			if (_err != null && !_err.isEmpty()) {
			%>
			<div class="alert alert-danger py-2">
				<i class="bi bi-exclamation-triangle-fill me-2"></i><%=_err%></div>
			<%
			}
			%>

			<form action="<%=ORSView.DOCTOR_CTL%>" method="POST">
				<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
					type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
				<input type="hidden" name="modifiedBy"
					value="<%=bean.getModifiedBy()%>"> <input type="hidden"
					name="createdDatetime"
					value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
				<input type="hidden" name="modifiedDatetime"
					value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

				<div class="mb-3">
					<label class="form-label fw-semibold"><%=ms.get("doctor.name") %><span
						class="text-danger">*</span></label> <input type="text" name="name"
						placeholder="<%=ms.get("doctor.name.placeholder")%>"
						class="form-control" maxlength="100"
						value="<%=DataUtility.getStringData(bean.getDoctorName())%>">
					<div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("name", request)%></div>
				</div>

				<div class="mb-3">
					<label class="form-label fw-semibold"><%=ms.get("doctor.specialization") %><span
						class="text-danger">*</span></label> <input type="text"
						name="specialization"
						placeholder="<%=ms.get("doctor.specialization.placeholder")%>"
						class="form-control" maxlength="200"
						value="<%=DataUtility.getStringData(bean.getSpecialization())%>">
					<div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("specialization", request)%></div>
				</div>

				<div class="mb-3">
					<label class="form-label fw-semibold"><%=ms.get("doctor.experience") %><span
						class="text-danger">*</span></label> <input type="text" name="experience"
						placeholder="<%=ms.get("doctor.experience.placeholder")%>"
						class="form-control" maxlength="200"
						value="<%=DataUtility.getStringData(bean.getExperience())%>">
					<div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("experience", request)%></div>
				</div>

				<div class="mb-3">
					<label class="form-label fw-semibold"><%=ms.get("doctor.contact") %><span
						class="text-danger">*</span></label> <input type="text" name="contact"
						placeholder="<%=ms.get("doctor.contact.placeholder")%>"
						class="form-control" maxlength="200"
						value="<%=DataUtility.getStringData(bean.getContactNo())%>">
					<div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("contact", request)%></div>
				</div>

				<div class="d-flex gap-2 pt-2 border-top">
					<button type="submit" name="operation" value="<%=BaseCtl.OP_SAVE%>"
						class="btn btn-primary">
						<i class="bi bi-save me-1"></i>
						<%=ms.get("button.save")%>
					</button>
					<%
					if (bean.getId() > 0) {
					%>
					<button type="submit" name="operation"
						value="<%=BaseCtl.OP_CANCEL%>" class="btn btn-danger ms-auto">
						<i class="bi bi-x-circle me-1"></i>
						<%=ms.get("button.cancel")%>
					</button>
					<%
					} else {
					%>
					<a href="DoctorCtl" class="btn btn-danger ms-auto"> <i
						class="bi bi-arrow-clockwise me-1"></i> <%=ms.get("button.reset")%>
					</a>
					<%
					}
					%>
				</div>
			</form>
		</div>
	</div>
</div>