<%@page import="com.sunilos.p4.util.MessageSource"%>
<%@page import="com.sunilos.p4.ctl.BaseCtl"%>
<%@page import="com.sunilos.p4.ctl.ORSView"%>
<%@page import="com.sunilos.p4.util.DataUtility"%>
<%@page import="com.sunilos.p4.util.ServletUtility"%>
<%@page import="java.util.List"%>

<jsp:useBean id="bean" class="com.sunilos.p4.bean.FaceRecognitionBean"
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
				<i class="bi bi-person-bounding-box me-2"></i>
				<%=bean.getId() > 0 ? "Edit Face Recognition" : "Add Face Recognition"%>
			</h5>
		</div>

		<div class="card-body px-4 py-4">

			<%
			if (_suc != null && !_suc.isEmpty()) {
			%>
			<div
				class="alert alert-success alert-dismissible fade show py-2 mx-3 mt-3"
				role="alert">
				<i class="bi bi-exclamation-triangle-fill me-2"></i>
				<%=_suc%>

				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
			<%
			}
			%>
			<%
			if (_err != null && !_err.isEmpty()) {
			%>
			<div class="alert alert-danger py-2" role="alert">
				<i class="bi bi-exclamation-triangle-fill me-2"></i>
				<%=_err%>

				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
			<%
			}
			%>

			<form action="<%=ORSView.FACE_RECOGNITION_CTL%>" method="POST">
				<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
					type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
				<input type="hidden" name="modifiedBy"
					value="<%=bean.getModifiedBy()%>"> <input type="hidden"
					name="createdDatetime"
					value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
				<input type="hidden" name="modifiedDatetime"
					value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

				<div class="mb-3">
					<label class="form-label fw-semibold">Face Code<span
						class="text-danger">*</span></label> <input type="text" name="code"
						placeholder="Enter Face Code" class="form-control" maxlength="200"
						value="<%=DataUtility.getStringData(bean.getFaceCode())%>">
					<div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("code", request)%></div>
				</div>

				<div class="mb-3">
					<label class="form-label fw-semibold">User Name <span
						class="text-danger">*</span>
					</label> <input type="text" name="name" placeholder="Enter User Name"
						class="form-control" maxlength="100"
						value="<%=DataUtility.getStringData(bean.getUserName())%>">
					<div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("name", request)%></div>
				</div>

				<div class="mb-3">
					<label class="form-label fw-semibold">Image Path<span
						class="text-danger">*</span></label> <input type="text" name="path"
						placeholder="Enter Image Path" class="form-control"
						maxlength="200"
						value="<%=DataUtility.getStringData(bean.getImagePath())%>">
					<div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("path", request)%></div>
				</div>

				<div class="mb-3">
					<label class="form-label fw-semibold">Status<span
						class="text-danger">*</span></label> <input type="text" name="status"
						placeholder="Enter Status" class="form-control" maxlength="200"
						value="<%=DataUtility.getStringData(bean.getStatus())%>">
					<div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("status", request)%></div>
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
					<a href="FaceRecognitionCtl" class="btn btn-danger ms-auto"> <i
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