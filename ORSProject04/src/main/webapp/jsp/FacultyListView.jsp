<%@page import="com.sunilos.p4.util.MessageSource"%>
<%@page import="com.sunilos.p4.ctl.BaseCtl"%>
<%@page import="com.sunilos.p4.ctl.ORSView"%>
<%@page import="com.sunilos.p4.util.ServletUtility"%>
<%@page import="com.sunilos.p4.bean.FacultyBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<%
MessageSource ms = MessageSource.getInstance();
int pageNo = ServletUtility.getPageNo(request);
int pageSize = ServletUtility.getPageSize(request);
int index = ((pageNo - 1) * pageSize) + 1;
List list = ServletUtility.getList(request);
Iterator<FacultyBean> it = list.iterator();
String _err = ServletUtility.getErrorMessage(request);
String _suc = ServletUtility.getSuccessMessage(request);
%>

<div class="container-fluid px-4 py-4" style="max-width: 1200px;">
	<div class="card border-0 shadow-sm rounded-4 overflow-hidden">

		<div
			class="card-header text-white border-0 py-3 px-4 d-flex justify-content-between align-items-center"
			style="background: linear-gradient(135deg, #0d2137 0%, #1565c0 100%);">
			<h5 class="mb-0 fw-bold">
				<i class="bi bi-person-badge-fill me-2"></i>
				<%=ms.get("faculty.list")%>
			</h5>
			<div class="d-flex gap-2">
				<a href="<%=ORSView.FACULTY_REPORT_CTL%>" target="_blank"
					class="btn btn-sm btn-warning fw-semibold"> <i
					class="bi bi-file-earmark-pdf me-1"></i> <%=ms.get("print.pdf")%>
				</a> <a href="<%=ORSView.FACULTY_REPORT_CTL%>?type=doc" target="_blank"
					class="btn btn-sm btn-info fw-semibold"> <i
					class="bi bi-file-earmark-word me-1"></i> <%=ms.get("print.doc")%>
				</a> <a href="FacultyCtl"
					class="btn btn-sm btn-light text-primary fw-semibold"> <i
					class="bi bi-plus-circle me-1"></i> <%=ms.get("faculty.add")%>
				</a>
			</div>
		</div>

		<form action="<%=ORSView.FACULTY_LIST_CTL%>" method="POST">
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<div
				class="p-3 bg-light border-bottom d-flex flex-wrap gap-2 align-items-center">
				<input type="text" name="firstName"
					class="form-control form-control-sm" style="max-width: 140px;"
					placeholder="<%=ms.get("register.enterfirstname")%>"
					value="<%=ServletUtility.getParameter("firstName", request)%>">
				<input type="text" name="lastName"
					class="form-control form-control-sm" style="max-width: 140px;"
					placeholder="<%=ms.get("register.enterlastname")%>"
					value="<%=ServletUtility.getParameter("lastName", request)%>">
				<input type="text" name="email" class="form-control form-control-sm"
					style="max-width: 200px;"
					placeholder="<%=ms.get("email.placeholder")%>"
					value="<%=ServletUtility.getParameter("email", request)%>">
				<button type="submit" name="operation"
					value="<%=BaseCtl.OP_SEARCH%>" class="btn btn-primary btn-sm">
					<i class="bi bi-search me-1"></i>
					<%=ms.get("button.search")%>
				</button>
				<button type="submit" name="operation"
					value="<%=BaseCtl.OP_DELETE%>"
					class="btn btn-danger btn-sm ms-auto">
					<i class="bi bi-trash me-1"></i>
					<%=ms.get("button.delete")%>
				</button>
			</div>

			<%
			if (_err != null && !_err.isEmpty()) {
			%>
			<div class="alert alert-danger py-2 mx-3 mt-3">
				<i class="bi bi-exclamation-triangle-fill me-2"></i><%=_err%></div>
			<%
			}
			%>
			<%
			if (_suc != null && !_suc.isEmpty()) {
			%>
			<div class="alert alert-success py-2 mx-3 mt-3">
				<i class="bi bi-exclamation-triangle-fill me-2"></i><%=_err%></div>
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
							<th><%=ms.get("college.title")%></th>
							<th><%=ms.get("first.name")%></th>
							<th><%=ms.get("last.name")%></th>
							<th><%=ms.get("email.id")%></th>
							<th><%=ms.get("mobile.title")%></th>
							<th><%=ms.get("register.gender")%></th>
							<th><%=ms.get("dob.title")%></th>
							<th><%=ms.get("label.action")%></th>
						</tr>
					</thead>
					<tbody>
						<%
						while (it.hasNext()) {
							FacultyBean bean = it.next();
						%>
						<tr>
							<td><input type="checkbox" name="ids"
								value="<%=bean.getId()%>"></td>
							<td class="text-muted small"><%=index++%></td>
							<td class="small"><%=bean.getCollegeName()%></td>
							<td class="fw-semibold"><%=bean.getFirstName()%></td>
							<td><%=bean.getLastName()%></td>
							<td class="small"><%=bean.getEmail()%></td>
							<td class="small"><%=bean.getMobileNo()%></td>
							<td>
								<%
								if ("Male".equals(bean.getGender())) {
								%> <span class="badge bg-primary">Male</span> <%
 } else if ("Female".equals(bean.getGender())) {
 %> <span class="badge bg-danger">Female</span> <%
 } else if (bean.getGender() != null && !bean.getGender().isEmpty()) {
 %> <span class="badge bg-secondary"><%=bean.getGender()%></span> <%
 }
 %>
							</td>
							<td class="text-muted small"><%=bean.getDob()%></td>
							<td><a href="FacultyCtl?id=<%=bean.getId()%>"
								class="btn btn-sm btn-outline-primary"> <i
									class="bi bi-pencil"></i> <%=ms.get("link.edit")%>
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
