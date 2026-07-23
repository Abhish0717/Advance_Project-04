<%@page import="com.sunilos.p4.util.MessageSource"%>
<%@page import="com.sunilos.p4.bean.RoleBean"%>
<%@page import="com.sunilos.p4.model.RoleModel"%>
<%@page import="com.sunilos.p4.ctl.UserListCtl"%>
<%@page import="com.sunilos.p4.ctl.BaseCtl"%>
<%@page import="com.sunilos.p4.ctl.ORSView"%>
<%@page import="com.sunilos.p4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.sunilos.p4.bean.UserBean"%>

<%
MessageSource ms = MessageSource.getInstance();
int pageNo = ServletUtility.getPageNo(request);
int pageSize = ServletUtility.getPageSize(request);
int index = ((pageNo - 1) * pageSize) + 1;
List list = ServletUtility.getList(request);
Iterator<UserBean> it = list.iterator();
String _err = ServletUtility.getErrorMessage(request);
String _suc = ServletUtility.getSuccessMessage(request);
%>

<div class="container-fluid px-4 py-4" style="max-width: 1000px;">
	<div class="card border-0 shadow-sm rounded-4 overflow-hidden">

		<div
			class="card-header text-white border-0 py-3 px-4 d-flex justify-content-between align-items-center"
			style="background: linear-gradient(135deg, #0d2137 0%, #1565c0 100%);">
			<h5 class="mb-0 fw-bold">
				<i class="bi bi-people-fill me-2"></i>
				<%=ms.get("user.list")%>
			</h5>
			<div class="d-flex gap-2">
				<a href="<%=ORSView.USER_REPORT_CTL%>" target="_blank"
					class="btn btn-sm btn-warning fw-semibold"> <i
					class="bi bi-file-earmark-pdf me-1"></i> <%=ms.get("print.pdf")%>
				</a> <a href="<%=ORSView.USER_REPORT_CTL%>?type=doc" target="_blank"
					class="btn btn-sm btn-info fw-semibold"> <i
					class="bi bi-file-earmark-word me-1"></i><%=ms.get("print.doc")%>
				</a> <a href="UserCtl"
					class="btn btn-sm btn-light text-primary fw-semibold"> <i
					class="bi bi-plus-circle me-1"></i> <%=ms.get("user.add")%>
				</a>
			</div>
		</div>

		<form action="<%=ORSView.USER_LIST_CTL%>" method="POST">
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<div
				class="p-3 bg-light border-bottom d-flex flex-wrap gap-2 align-items-center">
				<input type="text" name="firstName"
					class="form-control form-control-sm" style="max-width: 180px;"
					placeholder="<%=ms.get("register.enterfirstname")%>"
					value="<%=ServletUtility.getParameter("firstName", request)%>">
				<input type="text" name="login" class="form-control form-control-sm"
					style="max-width: 200px;"
					placeholder="<%=ms.get("login.enterloginid")%>"
					value="<%=ServletUtility.getParameter("login", request)%>">
				<button type="submit" name="operation"
					value="<%=BaseCtl.OP_SEARCH%>" class="btn btn-primary btn-sm">
					<i class="bi bi-search me-1"></i>
					<%=ms.get("button.search")%>
				</button>
				<button type="submit" name="operation"
					value="<%=BaseCtl.OP_DELETE%>"
					class="btn btn-danger btn-sm ms-auto"
					onclick="return confirm('Delete this user?')">
					<i class="bi bi-trash me-1"></i>
					<%=ms.get("button.delete")%>
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
							<th><%=ms.get("user.photo") %></th>
							<th><%=ms.get("first.name")%></th>
							<th><%=ms.get("last.name")%></th>
							<th><%=ms.get("login.userid")%></th>
							<th><%=ms.get("register.gender")%></th>
							<th><%=ms.get("dob.title")%></th>
							<th><%=ms.get("role.title")%></th>
							<th><%=ms.get("label.action")%></th>
						</tr>
					</thead>
					<tbody>
						<%
						while (it.hasNext()) {
							UserBean bean = it.next();
							UserBean user = (UserBean) session.getAttribute("user");
							RoleModel model = new RoleModel();
							RoleBean roleBean = model.findByPk(bean.getRoleId());
						%>
						<tr>
							<td><input type="checkbox" name="ids"
								value="<%=bean.getId()%>"
								<%=(user.getId() == bean.getId() || bean.getRoleId() == RoleBean.ADMIN) ? "disabled" : ""%>></td>
							<td class="text-muted small"><%=index++%></td>
							<td><img
								src="<%=ORSView.UPLOAD_PHOTO_CTL%>?id=<%=bean.getId()%>"
								onerror="this.style.display='none';" alt="User Photo"
								class="rounded-circle border" width="30" height="30"
								style="object-fit: cover;"></td>
							<td class="fw-semibold"><%=bean.getFirstName()%></td>
							<td><%=bean.getLastName()%></td>
							<td><%=bean.getLogin()%></td>
							<td><%=bean.getGender()%></td>
							<td class="text-muted small"><%=bean.getDob()%></td>
							<td><%=roleBean.getName()%></td>
							<td><a href="UserCtl?id=<%=bean.getId()%>"
								<%=(user.getId() == bean.getId() || bean.getRoleId() == RoleBean.ADMIN) ? "onclick='return false;'" : ""%>
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
