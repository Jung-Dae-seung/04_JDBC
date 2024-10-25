<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>학생 상세 정보</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
	<link rel="stylesheet" href="/resources/css/detail.css">
</head>
<body class="bg-light">
	<div class="container mt-5">
		<!-- 뒤로가기 버튼 -->
		<a href="<c:url value='/'/>"class="btn btn-link text-decoration-none mb-3"> 
			<i class="bi bi-arrow-left"></i> 목록으로 돌아가기
		</a>

		<div class="detail-card p-4">
			<!-- 학생 기본 정보 헤더 -->
			<div class="student-header">
				<div class="d-flex justify-content-between align-items-start mb-3">
					<div>
						<p class="student-number mb-2">학번: ${student.stdNo}</p>
						<h1 class="fs-2 mb-0">${student.stdName}</h1>
					</div>
					<div class="d-flex gap-2">
						<a href="<c:url value='/student/update?stdNo=${student.stdNo}'/>" class="btn btn-primary btn-action"> 
							<i class="bi bi-pencil"></i>수정
						</a>
						<button type="button" class="btn btn-danger btn-action" onclick="confirmDelete(${student.stdNo})">
							<i class="bi bi-trash"></i> 삭제
						</button>
					</div>
				</div>
			</div>

			<!-- 상세 정보 -->
			<div class="row g-4">
				<div class="col-md-6">
					<div class="d-flex align-items-center py-2">
						<span class="info-label">나이</span> <span class="info-value">${student.stdAge}세</span>
					</div>
				</div>
				<div class="col-md-6">
					<div class="d-flex align-items-center py-2">
						<span class="info-label">성별</span> <span class="info-value">
							${student.stdGender}
						</span>
					</div>
				</div>
				<div class="col-md-6">
					<div class="d-flex align-items-center py-2">
						<span class="info-label">성적</span> 
						<span class="score-badge score-${student.stdScore}">
							${student.stdScore} 
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 삭제 확인 모달 -->
	<div class="modal fade" id="deleteModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">학생 삭제</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body">정말로 이 학생의 정보를 삭제하시겠습니까?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">취소</button>
					<form action="<c:url value='/student/delete?stdNo=?'/>" method="get" class="d-inline">
						<input type="hidden" name="stdNo" id="stdNo">
						<button type="submit" class="btn btn-danger">삭제</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<c:if test="${not empty sessionScope.message}">
		<script>
			alert("${message}");
		</script>

		<c:remove var="message" scope="session" />
	</c:if>

	<script	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="/resources/js/detail.js"></script>
</body>
</html>