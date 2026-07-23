<%@page import="com.sunilos.p4.util.MessageSource"%>
<%@page import="com.sunilos.p4.ctl.ORSView"%>
<%@page import="com.sunilos.p4.bean.UserBean"%>
<%@page import="com.sunilos.p4.bean.RoleBean"%>

<%
MessageSource ms = MessageSource.getInstance();
UserBean currentUser = (UserBean) session.getAttribute("user");
boolean loggedIn = currentUser != null;
boolean isStudent = loggedIn && currentUser.getRoleId() == RoleBean.STUDENT;
boolean isAdmin = loggedIn && currentUser.getRoleId() == RoleBean.ADMIN;
String firstName = loggedIn ? currentUser.getFirstName() : "Guest";
%>

<div class="container-fluid px-4 py-4" style="max-width: 1100px;">

	<!-- ===== Hero Banner ===== -->
	<div
		class="rounded-4 text-white p-4 p-md-5 mb-4 d-flex align-items-center gap-4 shadow"
		style="background: linear-gradient(135deg, #0d2137 0%, #1565c0 100%);">
		<div
			class="flex-shrink-0 rounded-circle d-flex align-items-center justify-content-center"
			style="width: 80px; height: 80px; background: rgba(255, 255, 255, 0.15); border: 2px solid rgba(255, 255, 255, 0.25); font-size: 2.3rem;">
			<i class="bi bi-mortarboard-fill"></i>
		</div>
		<div>
			<h1 class="fw-bold mb-1 fs-2">
				<%=ms.get("welcome.msg")%>
				<%
				if (loggedIn) {
				%>,
				<%=firstName%>!<%
				} else {
				%>
				<%=ms.get("welcome.msg2")%>
				<%
				}
				%>
			</h1>
			<p class="mb-0 opacity-75"><%=ms.get("welcome.subtitle")%></p>
		</div>
	</div>

	<!-- ===== Section label ===== -->
	<p class="text-uppercase text-muted fw-semibold small mb-3"
		style="letter-spacing: .08em;">
		<%
		if (isAdmin) {
		%><%=ms.get("admission.panel")%>
		<%
		} else if (isStudent) {
		%><%=ms.get("quick.access")%>
		<%
		} else {
		%><%=ms.get("welcome.to")%>
		<%
		}
		%>
	</p>

	<!-- ===== Dashboard Cards ===== -->
	<div class="row g-3">

		<%
		if (isStudent) {
		%>

		<div class="col-6 col-md-4 col-lg-3">
			<a href="<%=ORSView.GET_MARKSHEET_CTL%>" class="text-decoration-none">
				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-3 ors-card border-top border-4 border-primary">
					<div class="fs-1 text-primary mb-2">
						<i class="bi bi-file-earmark-text-fill"></i>
					</div>
					<h6 class="fw-bold mb-1"><%=ms.get("my.marksheet")%></h6>
					<p class="text-muted small mb-0"><%=ms.get("marksheet.subtitle")%></p>
				</div>
			</a>
		</div>

		<div class="col-6 col-md-4 col-lg-3">
			<a href="<%=ORSView.MY_PROFILE_CTL%>" class="text-decoration-none">
				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-3 ors-card border-top border-4 border-success">
					<div class="fs-1 text-success mb-2">
						<i class="bi bi-person-circle"></i>
					</div>
					<h6 class="fw-bold mb-1">My Profile</h6>
					<p class="text-muted small mb-0">View and edit your details</p>
				</div>
			</a>
		</div>

		<div class="col-6 col-md-4 col-lg-3">
			<a href="<%=ORSView.CHANGE_PASSWORD_CTL%>"
				class="text-decoration-none">
				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-3 ors-card border-top border-4 border-warning">
					<div class="fs-1 text-warning mb-2">
						<i class="bi bi-key-fill"></i>
					</div>
					<h6 class="fw-bold mb-1">Change Password</h6>
					<p class="text-muted small mb-0">Update your login password</p>
				</div>
			</a>
		</div>

		<div class="col-6 col-md-4 col-lg-3">
			<a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"
				class="text-decoration-none">
				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-3 ors-card border-top border-4 border-danger">
					<div class="fs-1 text-danger mb-2">
						<i class="bi bi-trophy-fill"></i>
					</div>
					<h6 class="fw-bold mb-1">Merit List</h6>
					<p class="text-muted small mb-0">View class rankings</p>
				</div>
			</a>
		</div>

		<%
		} else if (isAdmin) {
		%>

		<div class="col-6 col-md-4 col-lg-3">
			<a href="<%=ORSView.USER_LIST_CTL%>" class="text-decoration-none">
				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-3 ors-card border-top border-4 border-primary">
					<div class="fs-1 text-primary mb-2">
						<i class="bi bi-people-fill"></i>
					</div>
					<h6 class="fw-bold mb-1"><%=ms.get("user.title")%></h6>
					<p class="text-muted small mb-0"><%=ms.get("user.manage")%></p>
				</div>
			</a>
		</div>

		<div class="col-6 col-md-4 col-lg-3">
			<a href="<%=ORSView.COLLEGE_LIST_CTL%>" class="text-decoration-none">
				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-3 ors-card border-top border-4 border-success">
					<div class="fs-1 text-success mb-2">
						<i class="bi bi-building"></i>
					</div>
					<h6 class="fw-bold mb-1"><%=ms.get("college.title")%></h6>
					<p class="text-muted small mb-0"><%=ms.get("college.manage")%></p>
				</div>
			</a>
		</div>

		<div class="col-6 col-md-4 col-lg-3">
			<a href="<%=ORSView.STUDENT_LIST_CTL%>" class="text-decoration-none">
				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-3 ors-card border-top border-4 border-dark"
					style="border-top: 4px solid #7c3aed;">
					<div class="fs-1 mb-2" style="color: black;">
						<i class="bi bi-mortarboard-fill"></i>
					</div>
					<h6 class="fw-bold mb-1"><%=ms.get("student.title")%></h6>
					<p class="text-muted small mb-0"><%=ms.get("student.manage")%></p>
				</div>
			</a>
		</div>

		<div class="col-6 col-md-4 col-lg-3">
			<a href="<%=ORSView.MARKSHEET_LIST_CTL%>"
				class="text-decoration-none">
				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-3 ors-card border-top border-4 border-warning">
					<div class="fs-1 text-warning mb-2">
						<i class="bi bi-file-earmark-text-fill"></i>
					</div>
					<h6 class="fw-bold mb-1"><%=ms.get("marksheet.plural")%></h6>
					<p class="text-muted small mb-0"><%=ms.get("marksheet.manage.records")%></p>
				</div>
			</a>
		</div>

		<div class="col-6 col-md-4 col-lg-3">
			<a href="<%=ORSView.ROLE_LIST_CTL%>" class="text-decoration-none">
				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-3 ors-card border-top border-4 border-danger">
					<div class="fs-1 text-danger mb-2">
						<i class="bi bi-shield-fill-check me-2"></i>
					</div>
					<h6 class="fw-bold mb-1"><%=ms.get("role.title")%></h6>
					<p class="text-muted small mb-0"><%=ms.get("role.subtitle")%></p>
				</div>
			</a>
		</div>

		<div class="col-6 col-md-4 col-lg-3">
			<a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"
				class="text-decoration-none">
				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-3 ors-card border-top border-4 border-warning">
					<div class="fs-1 text-warning mb-2" style="color: #d0ca14;">
						<i class="bi bi-trophy-fill"></i>
					</div>
					<h6 class="fw-bold mb-1"><%=ms.get("marksheet.title")%></h6>
					<p class="text-muted small mb-0"><%=ms.get("marksheet.subtitle")%></p>
				</div>
			</a>
		</div>

		<div class="col-6 col-md-4 col-lg-3">
			<a href="<%=ORSView.GET_MARKSHEET_CTL%>" class="text-decoration-none">
				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-3 ors-card border-top border-4 border-primary">
					<div class="fs-1 text-primary mb-2">
						<i class="bi bi-search"></i>
					</div>
					<h6 class="fw-bold mb-1"><%=ms.get("marksheet.get")%></h6>
					<p class="text-muted small mb-0"><%=ms.get("marksheet.get.subtitle")%></p>
				</div>
			</a>
		</div>

		<div class="col-6 col-md-4 col-lg-3">
			<a href="<%=ORSView.MY_PROFILE_CTL%>" class="text-decoration-none">
				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-3 ors-card border-top border-4 border-secondary">
					<div class="fs-1 mb-2" style="color: #374151;">
						<i class="bi bi-person-gear"></i>
					</div>
					<h6 class="fw-bold mb-1"><%=ms.get("my.profile")%></h6>
					<p class="text-muted small mb-0"><%=ms.get("my.profile.subtitle")%></p>
				</div>
			</a>
		</div>

		<div class="col-6 col-md-4 col-lg-3">
			<a href="<%=ORSView.FACULTY_LIST_CTL%>" class="text-decoration-none">
				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-3 ors-card border-top border-4 border-info">
					<div class="fs-1 text-info mb-2">
						<i class="bi bi-person-badge-fill"></i>
					</div>
					<h6 class="fw-bold mb-1"><%=ms.get("faculty.title")%></h6>
					<p class="text-muted small mb-0"><%=ms.get("faculty.card.subtitle")%></p>
				</div>
			</a>
		</div>

		<div class="col-6 col-md-4 col-lg-3">
			<a href="<%=ORSView.PRODUCT_LIST_CTL%>" class="text-decoration-none">
				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-3 ors-card border-top border-4 border-success">
					<div class="fs-1 mb-2" style="color: green;">
						<i class="bi bi-cart"></i>
					</div>
					<h6 class="fw-bold mb-1"><%=ms.get("product.add")%></h6>
					<p class="text-muted small mb-0"><%=ms.get("product.card.subtitle")%></p>
				</div>
			</a>
		</div>

		<div class="col-6 col-md-4 col-lg-3">
			<a href="<%=ORSView.HOSPITAL_LIST_CTL%>" class="text-decoration-none">
				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-3 ors-card border-top border-4 border-danger">
					<div class="fs-1 mb-2" style="color: red;">
						<i class="bi bi-hospital-fill"></i>
					</div>
					<h6 class="fw-bold mb-1"><%=ms.get("hospital.record")%></h6>
					<p class="text-muted small mb-0"><%=ms.get("hospital.card.subtitle")%></p>
				</div>
			</a>
		</div>

		<%-- <div class="col-6 col-md-4 col-lg-3">
			<a href="<%=ORSView.DOCTOR_LIST_CTL%>" class="text-decoration-none">
				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-3 ors-card border-top border-4 border-danger">
					<div class="fs-1 mb-2" style="color: red;">
						<i class="bi bi-prescription2"></i>
					</div>
					<h6 class="fw-bold mb-1"><%=ms.get("doctor.title")%></h6>
					<p class="text-muted small mb-0"><%=ms.get("doctor.subtitle")%></p>
				</div>
			</a>
		</div> --%>

		<div class="col-6 col-md-4 col-lg-3">
			<a href="<%=ORSView.FACE_RECOGNITION_LIST_CTL%>"
				class="text-decoration-none">
				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-3 ors-card border-top border-4 border-success">
					<div class="fs-1 mb-2" style="color: green;">
						<i class="bi bi-person-bounding-box"></i>
					</div>
					<h6 class="fw-bold mb-1">Face Recognition</h6>
					<p class="text-muted small mb-0">View Face Recognition Data</p>
				</div>
			</a>
		</div>

		<%
		} else {
		%>

		<div class="col-6 col-md-4 col-lg-3">
			<a href="<%=ORSView.LOGIN_CTL%>" class="text-decoration-none">
				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-3 ors-card border-top border-4 border-primary">
					<div class="fs-1 text-primary mb-2">
						<i class="bi bi-box-arrow-in-right"></i>
					</div>
					<h6 class="fw-bold mb-1"><%=ms.get("login.title")%></h6>
					<p class="text-muted small mb-0"><%=ms.get("login.cardsubtitle")%></p>
				</div>
			</a>
		</div>

		<div class="col-6 col-md-4 col-lg-3">
			<a href="<%=ORSView.USER_REGISTRATION_CTL%>"
				class="text-decoration-none">
				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-3 ors-card border-top border-4 border-success">
					<div class="fs-1 text-success mb-2">
						<i class="bi bi-person-plus-fill"></i>
					</div>
					<h6 class="fw-bold mb-1"><%=ms.get("register.title")%></h6>
					<p class="text-muted small mb-0"><%=ms.get("register.cardsubtitle")%></p>
				</div>
			</a>
		</div>

		<div class="col-6 col-md-4 col-lg-3">
			<a href="<%=ORSView.GET_MARKSHEET_CTL%>" class="text-decoration-none">
				<div
					class="card h-100 border-0 shadow-sm rounded-4 text-center p-3 ors-card"
					style="border-top: 4px solid #7c3aed;">
					<div class="fs-1 mb-2" style="color: #7c3aed;">
						<i class="bi bi-search"></i>
					</div>
					<h6 class="fw-bold mb-1"><%=ms.get("marksheet.get")%></h6>
					<p class="text-muted small mb-0"><%=ms.get("marksheet.cardsubtitle")%></p>
				</div>
			</a>
		</div>

		<%
		}
		%>
	</div>

	<!-- /row -->
</div>