<%@page import="com.sunilos.p4.util.MessageSource"%>
<%@page import="com.sunilos.p4.ctl.DoctorListCtl"%>
<%@page import="com.sunilos.p4.ctl.BaseCtl"%>
<%@page import="com.sunilos.p4.ctl.ORSView"%>
<%@page import="com.sunilos.p4.bean.DoctorBean"%>
<%@page import="com.sunilos.p4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<%
MessageSource ms = MessageSource.getInstance();
int pageNo = ServletUtility.getPageNo(request);
int pageSize = ServletUtility.getPageSize(request);
int index = ((pageNo - 1) * pageSize) + 1;
List list = ServletUtility.getList(request);
Iterator<DoctorBean> it = list.iterator();
String _suc = ServletUtility.getSuccessMessage(request);
String _err = ServletUtility.getErrorMessage(request);
%>

<div class="container-fluid px-4 py-4" style="max-width: 900px;">
	<div class="card border-0 shadow-sm rounded-4 overflow-hidden">
		<div
			class="card-header text-white border-0 py-3 px-4 d-flex justify-content-between align-items-center"
			style="background: linear-gradient(135deg, #0d2137 0%, #1565c0 100%);">
			<h5 class="mb-0 fw-bold">
				<i class="bi bi-prescription2 me-2"></i><%=ms.get("doctor.list") %>
			</h5>
			<div class="d-flex gap-2">
				<a href="<%=ORSView.DOCTOR_REPORT_CTL%>" target="_blank"
					class="btn btn-sm btn-warning fw-semibold"> <i
					class="bi bi-file-earmark-pdf me-1"></i> <%=ms.get("print.pdf") %>
				</a> <a href="<%=ORSView.DOCTOR_REPORT_CTL%>?type=doc" target="_blank"
					class="btn btn-sm btn-info fw-semibold"> <i
					class="bi bi-file-earmark-word me-1"></i> <%=ms.get("print.doc") %>
			<a href="<%=ORSView.DOCTOR_CTL%>"
				class="btn btn-sm btn-light text-primary fw-semibold"> <i
				class="bi bi-plus-circle me-1"></i> <%=ms.get("doctor.add.button") %>
			</a>
		</div>
	</div>
	<form action="<%=ORSView.DOCTOR_LIST_CTL%>" method="POST">
		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">

		<div
			class="p-3 bg-light border-bottom d-flex flex-wrap gap-2 align-items-center">
			<input type="text" name="name" class="form-control form-control-sm"
				style="max-width: 220px;" placeholder="<%=ms.get("doctor.name.placeholder") %>"
				value="<%=ServletUtility.getParameter("name", request)%>">
			<button type="submit" name="operation" value="<%=BaseCtl.OP_SEARCH%>"
				class="btn btn-primary btn-sm">
				<i class="bi bi-search me-1"></i>
				<%=ms.get("button.search")%>
			</button>
			<button type="submit" name="operation" value="<%=BaseCtl.OP_DELETE%>"
				class="btn btn-danger btn-sm ms-auto"
				onclick="return confirm('<%=ms.get("doctor.delete.confirm")%>')">
				<i class="bi bi-trash me-1"></i>
				<%=ms.get("button.delete.selected")%>
			</button>
		</div>
		<%
		if (_err != null && !_err.isEmpty()) {
		%>
		<div
			class="alert alert-danger alert-dismissible fade show py-2 mx-3 mt-3"
			role="alert">
			<i class="bi bi-exclamation-triangle-fill me-2"></i>
			<%=_err%>

			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
		<%
		}
		%>

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
		<div class="table-responsive">
			<table class="table table-hover align-middle mb-0">
				<thead class="table-light">
					<tr>
						<th width="40"><input type="checkbox"
							onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)"></th>
						<th><%=ms.get("serial.no")%></th>
						<th><%=ms.get("doctor.name")%></th>
						<th><%=ms.get("doctor.specialization")%></th>
						<th><%=ms.get("doctor.experience")%></th>
						<th><%=ms.get("doctor.contact")%></th>
					</tr>
				</thead>
				<tbody>
					<%
					while (it.hasNext()) {
						DoctorBean bean = it.next();
					%>
					<tr>
						<td><input type="checkbox" name="ids"
							value="<%=bean.getId()%>"></td>
						<td class="text-muted small"><%=index++%></td>
						<td class="fw-semibold"><%=bean.getDoctorName()%></td>
						<td><%=bean.getSpecialization()%></td>
						<td><%=bean.getExperience()%></td>
						<td><%=bean.getContactNo()%></td>
						<td><a href="DoctorCtl?id=<%=bean.getId()%>"
							class="btn btn-sm btn-outline-primary"> <i
								class="bi bi-pencil"></i> <%=ms.get("button.edit")%>
						</a></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
		<div class="p-3 border-top">
			<%@ include file="ListFooter.jsp"%>
		</div>
	</form>
</div>
</div>