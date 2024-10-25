<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>학생 정보 수정</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<link rel="stylesheet" href="/resources/css/detail.css">
</head>
<body class="bg-light">
	<div class="container mt-5">
		<!-- 뒤로가기 버튼 -->
		<a href="<c:url value='/student/detail?stdNo=${student.stdNo}'/>"
			class="btn btn-link text-decoration-none mb-3"> <i
			class="bi bi-arrow-left"></i> 상세 정보로 돌아가기
		</a>

		<div class="detail-card p-4">
			<!-- 학생 정보 수정 폼 -->
			<h1 class="fs-2 mb-4">학생 정보 수정</h1>

			<form action="<c:url value='/student/update'/>" method="post"
				class="needs-validation" id="studentForm" novalidate>
				<input type="hidden" name="stdNo" value="${student.stdNo}">

				<div class="row g-4">
					<!-- 이름 입력 -->
					<div class="col-md-6">
						<div class="form-group">
							<label for="stdName" class="form-label info-label">이름</label> <input
								type="text" class="form-control" id="stdName" name="stdName"
								value="${student.stdName}" required>
							<div class="invalid-feedback">이름을 입력해주세요.</div>
						</div>
					</div>

					<!-- 나이 입력 -->
					<div class="col-md-6">
						<div class="form-group">
							<label for="stdAge" class="form-label info-label">나이</label> <input
								type="number" class="form-control" id="stdAge" name="stdAge"
								value="${student.stdAge}" required min="1">
							<div class="invalid-feedback">올바른 나이를 입력해주세요.</div>
						</div>
					</div>

					<!-- 성별 선택 -->
					<div class="col-md-6">
						<div class="form-group">
							<label class="form-label info-label">성별</label>
							<div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="stdGender"
										id="genderM" value="M"
										${student.stdGender == '남자' ? 'checked' : ''}> <label
										class="form-check-label" for="genderM">남</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="stdGender"
										id="genderF" value="F"
										${student.stdGender == '여자' ? 'checked' : ''}> <label
										class="form-check-label" for="genderF">여</label>
								</div>
							</div>
						</div>
					</div>

					<!-- 성적 입력 -->
					<div class="col-md-6">
						<div class="form-group">
							<label for="stdScore" class="form-label info-label">성적</label> <select
								class="form-select" id="stdScore" name="stdScore" required>
								<option value="">성적 선택</option>
								<option value="A" ${student.stdScore == 'A' ? 'selected' : ''}>A</option>
								<option value="B" ${student.stdScore == 'B' ? 'selected' : ''}>B</option>
								<option value="C" ${student.stdScore == 'C' ? 'selected' : ''}>C</option>
								<option value="D" ${student.stdScore == 'D' ? 'selected' : ''}>D</option>
								<option value="F" ${student.stdScore == 'F' ? 'selected' : ''}>F</option>
							</select>
							<div class="invalid-feedback">성적을 선택해주세요.</div>
						</div>
					</div>
				</div>

				<!-- 버튼 그룹 -->
				<div class="d-flex justify-content-end gap-2 mt-4">
					<button type="button" class="btn btn-secondary"
						onclick="location.href='<c:url value='/student/detail?stdNo=${student.stdNo}'/>'">
						취소</button>
					<button type="submit" class="btn btn-primary">
						<i class="bi bi-check-lg"></i> 저장
					</button>
				</div>
			</form>
		</div>
	</div>

	<c:if test="${not empty sessionScope.message}">
		<script>
	        alert("${message}");
	    </script>
		<c:remove var="message" scope="session" />
	</c:if>

	<script	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="/resources/js/update.js"></script>
</body>
</html>