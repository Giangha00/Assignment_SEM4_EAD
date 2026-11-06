<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Information System</title>
    <!-- Fonts & Font Awesome Icons -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary-green: #486a47;
            --primary-dark: #375336;
            --accent-green: #597d58;
            --bg-page: #f8fafc;
            --table-header: #3b523a;
            --text-dark: #1e293b;
            --text-muted: #64748b;
            --border-color: #e2e8f0;
        }

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Inter', sans-serif;
            background-color: var(--bg-page);
            color: var(--text-dark);
            min-height: 100vh;
            padding-bottom: 50px;
        }

        /* Header Banner */
        .top-banner {
            background-color: var(--primary-green);
            color: #ffffff;
            padding: 18px 40px;
            font-size: 26px;
            font-weight: 700;
            letter-spacing: 0.5px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.12);
        }

        .container {
            max-width: 1200px;
            margin: 28px auto;
            padding: 0 20px;
        }

        .title-section {
            text-align: center;
            margin-bottom: 22px;
        }

        .title-section h2 {
            font-size: 28px;
            font-weight: 700;
            color: var(--text-dark);
            letter-spacing: -0.3px;
        }

        /* Alert notifications */
        .alert {
            padding: 12px 20px;
            border-radius: 8px;
            margin-bottom: 20px;
            display: flex;
            align-items: center;
            gap: 10px;
            font-weight: 500;
            animation: fadeIn 0.3s ease-in-out;
        }

        .alert-success {
            background-color: #ecfdf5;
            color: #065f46;
            border: 1px solid #a7f3d0;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-8px); }
            to { opacity: 1; transform: translateY(0); }
        }

        /* Action Buttons */
        .action-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 16px;
            flex-wrap: wrap;
            gap: 12px;
        }

        .action-buttons {
            display: flex;
            gap: 12px;
        }

        .btn {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            padding: 9px 18px;
            font-size: 14px;
            font-weight: 600;
            border-radius: 6px;
            text-decoration: none;
            transition: all 0.2s ease;
            cursor: pointer;
            border: none;
        }

        .btn-action {
            background-color: var(--primary-green);
            color: #ffffff;
            box-shadow: 0 2px 4px rgba(72, 106, 71, 0.25);
        }

        .btn-action:hover {
            background-color: var(--primary-dark);
            transform: translateY(-1px);
        }

        /* Filter Card */
        .filter-card {
            background: #ffffff;
            border: 1px solid var(--border-color);
            border-radius: 8px;
            padding: 18px 20px;
            margin-bottom: 20px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.04);
        }

        .filter-header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 14px;
        }

        .filter-header h3 {
            font-size: 14.5px;
            font-weight: 700;
            color: var(--text-dark);
            display: flex;
            align-items: center;
            gap: 8px;
        }

        .filter-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
            gap: 12px;
            align-items: end;
        }

        .filter-group {
            display: flex;
            flex-direction: column;
            gap: 6px;
        }

        .filter-group label {
            font-size: 12.5px;
            font-weight: 600;
            color: #475569;
        }

        .filter-control {
            width: 100%;
            padding: 8px 12px;
            font-size: 13.5px;
            border: 1px solid var(--border-color);
            border-radius: 6px;
            outline: none;
            background-color: #f8fafc;
            color: var(--text-dark);
            transition: border-color 0.2s;
            font-family: inherit;
        }

        .filter-control:focus {
            background-color: #ffffff;
            border-color: var(--primary-green);
            box-shadow: 0 0 0 2px rgba(72, 106, 71, 0.15);
        }

        .filter-actions {
            display: flex;
            gap: 8px;
        }

        .btn-filter {
            background-color: var(--primary-green);
            color: #ffffff;
            padding: 8px 16px;
            font-size: 13.5px;
        }

        .btn-filter:hover {
            background-color: var(--primary-dark);
        }

        .btn-reset {
            background-color: #e2e8f0;
            color: #475569;
            padding: 8px 14px;
            font-size: 13.5px;
        }

        .btn-reset:hover {
            background-color: #cbd5e1;
            color: #1e293b;
        }

        .result-count {
            font-size: 13.5px;
            color: var(--text-muted);
            font-weight: 500;
        }

        /* Data Table */
        .table-card {
            background: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
            overflow: hidden;
            border: 1px solid var(--border-color);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            text-align: left;
        }

        thead th {
            background-color: var(--table-header);
            color: #ffffff;
            padding: 14px 18px;
            font-size: 13.5px;
            font-weight: 600;
            text-transform: capitalize;
            letter-spacing: 0.2px;
            white-space: nowrap;
        }

        tbody td {
            padding: 13px 18px;
            border-bottom: 1px solid var(--border-color);
            font-size: 14px;
            color: #334155;
        }

        tbody tr:last-child td {
            border-bottom: none;
        }

        tbody tr:hover {
            background-color: #f1f5f9;
        }

        /* Grade Badges */
        .grade-badge {
            display: inline-block;
            font-weight: 700;
            padding: 4px 10px;
            border-radius: 4px;
            font-size: 13px;
            min-width: 28px;
            text-align: center;
        }

        .grade-A {
            background-color: #dcfce7;
            color: #15803d;
            border: 1px solid #bbf7d0;
        }

        .grade-B {
            background-color: #e0f2fe;
            color: #0369a1;
            border: 1px solid #bae6fd;
        }

        .grade-D {
            background-color: #fef9c3;
            color: #a16207;
            border: 1px solid #fef08a;
        }

        .grade-F {
            background-color: #fee2e2;
            color: #b91c1c;
            border: 1px solid #fecaca;
        }

        .action-link {
            color: #3b82f6;
            text-decoration: none;
            font-size: 15px;
            padding: 6px 8px;
            border-radius: 4px;
            transition: all 0.15s;
        }

        .action-link:hover {
            color: #1d4ed8;
            background-color: #eff6ff;
        }

        .empty-row {
            text-align: center;
            padding: 36px;
            color: var(--text-muted);
            font-style: italic;
        }

        .footer-info {
            margin-top: 15px;
            font-size: 13px;
            color: var(--text-muted);
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap;
            gap: 10px;
        }
    </style>
</head>
<body>

    <!-- Top Banner -->
    <header class="top-banner">
        Student Information System
    </header>

    <div class="container">
        <!-- Main Title -->
        <div class="title-section">
            <h2>Student Infomation</h2>
        </div>

        <!-- Success Notifications -->
        <c:if test="${param.success eq 'student_created'}">
            <div class="alert alert-success">
                <i class="fa-solid fa-circle-check"></i>
                <span>Thêm mới sinh viên thành công!</span>
            </div>
        </c:if>
        <c:if test="${param.success eq 'score_created'}">
            <div class="alert alert-success">
                <i class="fa-solid fa-circle-check"></i>
                <span>Thêm điểm môn học thành công!</span>
            </div>
        </c:if>
        <c:if test="${param.success eq 'score_updated'}">
            <div class="alert alert-success">
                <i class="fa-solid fa-circle-check"></i>
                <span>Cập nhật điểm môn học thành công!</span>
            </div>
        </c:if>

        <!-- Action Bar: Buttons + Summary Counter -->
        <div class="action-bar">
            <div class="action-buttons">
                <a href="${pageContext.request.contextPath}/student/add" class="btn btn-action">
                    <i class="fa-solid fa-user-plus"></i> + Student
                </a>
                <a href="${pageContext.request.contextPath}/score/add" class="btn btn-action">
                    <i class="fa-solid fa-plus"></i> + Score
                </a>
            </div>
            <div class="result-count" id="resultCount">
                Tổng số bản ghi: <strong>${scoreList.size()}</strong>
            </div>
        </div>

        <!-- Filter Card: Tìm kiếm theo Student Id, Student Name, Subject Name, Credit, Grade -->
        <div class="filter-card">
            <div class="filter-header">
                <h3><i class="fa-solid fa-filter"></i> Bộ lọc & Tìm kiếm nhanh</h3>
                <small style="color: var(--text-muted);">Hỗ trợ tìm kiếm trực tiếp hoặc gửi lọc về server</small>
            </div>
            <form action="${pageContext.request.contextPath}/students" method="get" id="filterForm">
                <div class="filter-grid">
                    <!-- 1. Student Id (Student Code) -->
                    <div class="filter-group">
                        <label for="filter_student_id"><i class="fa-solid fa-id-card"></i> Student Id</label>
                        <input type="text" id="filter_student_id" name="student_id" class="filter-control"
                               placeholder="VD: 2007A1" value="${filterStudentId}">
                    </div>

                    <!-- 2. Student Name -->
                    <div class="filter-group">
                        <label for="filter_student_name"><i class="fa-solid fa-user"></i> Student Name</label>
                        <input type="text" id="filter_student_name" name="student_name" class="filter-control"
                               placeholder="VD: Nguyễn Văn A" value="${filterStudentName}">
                    </div>

                    <!-- 3. Subject Name -->
                    <div class="filter-group">
                        <label for="filter_subject_name"><i class="fa-solid fa-book"></i> Subject Name</label>
                        <input type="text" id="filter_subject_name" name="subject_name" class="filter-control"
                               placeholder="VD: JAVA, PHP..." value="${filterSubjectName}">
                    </div>

                    <!-- 4. Credit -->
                    <div class="filter-group">
                        <label for="filter_credit"><i class="fa-solid fa-award"></i> Credit</label>
                        <select id="filter_credit" name="credit" class="filter-control">
                            <option value="">-- Tất cả tín chỉ --</option>
                            <option value="1" ${filterCredit eq '1' ? 'selected' : ''}>1 tín chỉ</option>
                            <option value="2" ${filterCredit eq '2' ? 'selected' : ''}>2 tín chỉ</option>
                            <option value="3" ${filterCredit eq '3' ? 'selected' : ''}>3 tín chỉ</option>
                            <option value="4" ${filterCredit eq '4' ? 'selected' : ''}>4 tín chỉ</option>
                            <option value="5" ${filterCredit eq '5' ? 'selected' : ''}>5 tín chỉ</option>
                        </select>
                    </div>

                    <!-- 5. Grade -->
                    <div class="filter-group">
                        <label for="filter_grade"><i class="fa-solid fa-star"></i> Grade</label>
                        <select id="filter_grade" name="grade" class="filter-control">
                            <option value="">-- Tất cả xếp loại --</option>
                            <option value="A" ${filterGrade eq 'A' ? 'selected' : ''}>Grade A (8.0 - 10)</option>
                            <option value="B" ${filterGrade eq 'B' ? 'selected' : ''}>Grade B (6.0 - 7.9)</option>
                            <option value="D" ${filterGrade eq 'D' ? 'selected' : ''}>Grade D (4.0 - 5.9)</option>
                            <option value="F" ${filterGrade eq 'F' ? 'selected' : ''}>Grade F (&lt; 4.0)</option>
                        </select>
                    </div>

                    <!-- Action buttons -->
                    <div class="filter-actions">
                        <button type="submit" class="btn btn-filter">
                            <i class="fa-solid fa-magnifying-glass"></i> Lọc
                        </button>
                        <a href="${pageContext.request.contextPath}/students" class="btn btn-reset" title="Xóa tất cả bộ lọc">
                            <i class="fa-solid fa-arrow-rotate-left"></i> Xóa
                        </a>
                    </div>
                </div>
            </form>
        </div>

        <!-- Data Table -->
        <div class="table-card">
            <table id="dataTable">
                <thead>
                    <tr>
                        <th style="width: 50px;">Id</th>
                        <th>Student Id</th>
                        <th>Student Name</th>
                        <th>Subject Name</th>
                        <th>Score 1</th>
                        <th>Score 2</th>
                        <th>Credit</th>
                        <th>Grade</th>
                        <th style="width: 60px; text-align: center;"></th>
                    </tr>
                </thead>
                <tbody id="tableBody">
                    <c:choose>
                        <c:when test="${not empty scoreList}">
                            <c:forEach var="item" items="${scoreList}">
                                <tr class="data-row"
                                    data-student-id="${item.studentCode.toLowerCase()}"
                                    data-student-name="${item.studentName.toLowerCase()}"
                                    data-subject-name="${(item.subjectCode + ' ' + item.subjectName).toLowerCase()}"
                                    data-credit="${item.credit}"
                                    data-grade="${item.grade.toUpperCase()}">
                                    <td class="col-stt">${item.id}</td>
                                    <td><strong>${item.studentCode}</strong></td>
                                    <td>${item.studentName}</td>
                                    <td>${item.subjectCode}</td>
                                    <td>${item.score1}</td>
                                    <td>${item.score2}</td>
                                    <td>${item.credit}</td>
                                    <td>
                                        <span class="grade-badge grade-${item.grade}" title="Điểm tổng kết: ${item.gradeScore}">
                                            ${item.grade}
                                        </span>
                                    </td>
                                    <td style="text-align: center;">
                                        <a href="${pageContext.request.contextPath}/score/edit?id=${item.studentScoreId}"
                                           class="action-link" title="Chỉnh sửa điểm">
                                            <i class="fa-solid fa-pencil"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr id="serverEmptyRow">
                                <td colspan="9" class="empty-row">Không tìm thấy dữ liệu điểm phù hợp với bộ lọc!</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    <tr id="clientEmptyRow" style="display: none;">
                        <td colspan="9" class="empty-row">Không tìm thấy kết quả nào khớp với điều kiện tìm kiếm nhanh!</td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div class="footer-info">
            <span>* Công thức tính điểm: <strong>Grade Score = 0.3 &times; Score1 + 0.7 &times; Score2</strong></span>
            <span>Quy đổi: <strong>A (&ge;8.0) | B (6.0 - 7.9) | D (4.0 - 5.9) | F (&lt;4.0)</strong></span>
        </div>
    </div>

    <!-- Client-side Realtime Instant Search as you type -->
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const studentIdInput = document.getElementById('filter_student_id');
            const studentNameInput = document.getElementById('filter_student_name');
            const subjectNameInput = document.getElementById('filter_subject_name');
            const creditSelect = document.getElementById('filter_credit');
            const gradeSelect = document.getElementById('filter_grade');

            const rows = document.querySelectorAll('.data-row');
            const clientEmptyRow = document.getElementById('clientEmptyRow');
            const resultCount = document.getElementById('resultCount');

            function applyLiveFilter() {
                const sIdVal = studentIdInput.value.trim().toLowerCase();
                const sNameVal = studentNameInput.value.trim().toLowerCase();
                const subNameVal = subjectNameInput.value.trim().toLowerCase();
                const creditVal = creditSelect.value.trim();
                const gradeVal = gradeSelect.value.trim().toUpperCase();

                let visibleCount = 0;

                rows.forEach(row => {
                    const rowSId = row.getAttribute('data-student-id') || '';
                    const rowSName = row.getAttribute('data-student-name') || '';
                    const rowSubName = row.getAttribute('data-subject-name') || '';
                    const rowCredit = row.getAttribute('data-credit') || '';
                    const rowGrade = row.getAttribute('data-grade') || '';

                    const matchSId = !sIdVal || rowSId.includes(sIdVal);
                    const matchSName = !sNameVal || rowSName.includes(sNameVal);
                    const matchSubName = !subNameVal || rowSubName.includes(subNameVal);
                    const matchCredit = !creditVal || rowCredit === creditVal;
                    const matchGrade = !gradeVal || rowGrade === gradeVal;

                    if (matchSId && matchSName && matchSubName && matchCredit && matchGrade) {
                        row.style.display = '';
                        visibleCount++;
                        // Cập nhật lại số thứ tự hiển thị
                        const sttCell = row.querySelector('.col-stt');
                        if (sttCell) {
                            sttCell.textContent = visibleCount;
                        }
                    } else {
                        row.style.display = 'none';
                    }
                });

                if (clientEmptyRow) {
                    clientEmptyRow.style.display = (visibleCount === 0 && rows.length > 0) ? '' : 'none';
                }

                if (resultCount) {
                    resultCount.innerHTML = 'Hiển thị: <strong>' + visibleCount + '</strong> / ' + rows.length + ' bản ghi';
                }
            }

            // Lắng nghe sự kiện input/change để lọc tức thì theo thời gian thực
            [studentIdInput, studentNameInput, subjectNameInput].forEach(input => {
                input.addEventListener('input', applyLiveFilter);
            });

            [creditSelect, gradeSelect].forEach(select => {
                select.addEventListener('change', applyLiveFilter);
            });
        });
    </script>

</body>
</html>
