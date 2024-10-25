<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/css/main.css">
<title>학생 관리 시스템</title>
</head>
<body class="bg-light">
	<div class="container mt-5">
		<!-- 총 학생 수 대시보드 -->
		<div class="dashboard-card">
			<div class="total-students">
				<div class="total-students-icon">
					<i class="bi bi-people-fill fs-3"></i>
				</div>
				<div class="total-students-info">
					<h2 class="total-students-number">
						<c:out value="${studentList.size()}" />
						명
					</h2>
					<p class="total-students-label">총 등록 학생 수</p>
				</div>
			</div>
		</div>

		<!-- 학생 목록 카드 -->
		<div class="card">
			<div
				class="card-header d-flex justify-content-between align-items-center">
				<h2 class="mb-0 fs-4">학생 목록</h2>
				<a href="<c:url value='/student/insert'/>" class="btn btn-primary">
					<i class="bi bi-plus-circle"></i> 학생 추가
				</a>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-hover align-middle">
						<thead>
							<tr>
								<th>학번</th>
								<th>이름</th>
								<th>나이</th>
								<th>성별</th>
								<th>성적</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${studentList}" var="student">
								<tr>
									<td>${student.stdNo}</td>
									<td>
										<a href="<c:url value='/student/detail?stdNo=${student.stdNo}'/>"	class="student-name"> 
											${student.stdName} 
										</a>
									</td>
									<td>${student.stdAge}세</td>
									<td>${student.stdGender}<td>
										<span class="score-badge score-${student.stdScore}"> ${student.stdScore} </span>
									</td>
								</tr>
							</c:forEach>

							<c:if test="${empty studentList}">
								<tr>
									<td colspan="5" class="text-center py-4">
										<i class="bi bi-info-circle me-2"></i>등록된 학생이 없습니다.
									</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap Bundle with Popper -->
	<script	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	
	<c:if test="${not empty sessionScope.message}">
		<script>
			alert("${message}");
		</script>

		<c:remove var="message" scope="session" />
	</c:if>
</body>
</html>