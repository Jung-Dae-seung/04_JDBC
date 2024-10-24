<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>학생 추가</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
    <style>
        .form-card {
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        
        .form-label {
            font-weight: 500;
            color: #666;
        }
        
        .form-control:focus, .form-select:focus {
            border-color: #1976d2;
            box-shadow: 0 0 0 0.25rem rgba(25, 118, 210, 0.25);
        }
        
        .btn-action {
            padding: 0.5rem 1.25rem;
            display: flex;
            align-items: center;
            gap: 8px;
            transition: all 0.2s;
        }
        
        .btn-action:hover {
            transform: translateY(-1px);
        }

        .required-mark {
            color: #dc3545;
            margin-left: 4px;
        }
    </style>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <!-- 뒤로가기 버튼 -->
        <a href="<c:url value='/student/list'/>" class="btn btn-link text-decoration-none mb-3">
            <i class="bi bi-arrow-left"></i> 목록으로 돌아가기
        </a>

        <div class="form-card p-4">
            <h2 class="mb-4">학생 추가</h2>
            
            <form action="<c:url value='/student/insert'/>" method="post" id="studentForm">
                <div class="row g-4">
                    <!-- 학번 -->
                    <div class="col-md-6">
                        <label for="stdNo" class="form-label">
                            학번<span class="required-mark">*</span>
                        </label>
                        <input type="number" 
                               class="form-control" 
                               id="stdNo" 
                               name="stdNo" 
                               required>
                    </div>
                    
                    <!-- 이름 -->
                    <div class="col-md-6">
                        <label for="stdName" class="form-label">
                            이름<span class="required-mark">*</span>
                        </label>
                        <input type="text" 
                               class="form-control" 
                               id="stdName" 
                               name="stdName" 
                               maxlength="20" 
                               required>
                    </div>
                    
                    <!-- 나이 -->
                    <div class="col-md-6">
                        <label for="stdAge" class="form-label">
                            나이<span class="required-mark">*</span>
                        </label>
                        <input type="number" 
                               class="form-control" 
                               id="stdAge" 
                               name="stdAge" 
                               required>
                    </div>
                    
                    <!-- 성별 -->
                    <div class="col-md-6">
                        <label for="stdGender" class="form-label">성별</label>
                        <select class="form-select" id="stdGender" name="stdGender">
                            <option value="M">남자</option>
                            <option value="F">여자</option>
                        </select>
                    </div>
                    
                    <!-- 성적 -->
                    <div class="col-md-6">
                        <label for="stdScore" class="form-label">성적</label>
                        <select class="form-select" id="stdScore" name="stdScore">
                            <option value="A">A</option>
                            <option value="B">B</option>
                            <option value="C">C</option>
                            <option value="D">D</option>
                            <option value="F">F</option>
                        </select>
                    </div>
                </div>

                <!-- 버튼 그룹 -->
                <div class="d-flex gap-2 justify-content-center mt-5">
                    <button type="submit" class="btn btn-primary btn-action">
                        <i class="bi bi-check-lg"></i> 저장
                    </button>
                    <a href="<c:url value='/student/list'/>" class="btn btn-secondary btn-action">
                        <i class="bi bi-x-lg"></i> 취소
                    </a>
                </div>
            </form>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.getElementById('studentForm').addEventListener('submit', function(e) {
            const stdAge = document.getElementById('stdAge').value;
            if (stdAge < 1 || stdAge > 120) {
                e.preventDefault();
                alert('유효한 나이를 입력해주세요 (1-120)');
            }
        });
    </script>
</body>
</html>