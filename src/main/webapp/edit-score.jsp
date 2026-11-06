<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cập Nhật Điểm Sinh Viên - SIS</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary-green: #486a47;
            --primary-dark: #375336;
            --bg-page: #f8fafc;
            --text-dark: #1e293b;
            --border-color: #cbd5e1;
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

        .top-banner {
            background-color: var(--primary-green);
            color: #ffffff;
            padding: 18px 40px;
            font-size: 24px;
            font-weight: 700;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.12);
        }

        .container {
            max-width: 600px;
            margin: 40px auto;
            padding: 0 20px;
        }

        .form-card {
            background: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
            padding: 32px;
            border: 1px solid #e2e8f0;
        }

        .form-header {
            margin-bottom: 24px;
            border-bottom: 2px solid #f1f5f9;
            padding-bottom: 16px;
        }

        .form-header h2 {
            font-size: 22px;
            font-weight: 700;
            color: var(--primary-dark);
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .alert-error {
            background-color: #fef2f2;
            color: #991b1b;
            border: 1px solid #fecaca;
            padding: 12px 16px;
            border-radius: 6px;
            margin-bottom: 20px;
            font-size: 14px;
            display: flex;
            align-items: center;
            gap: 8px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-size: 14px;
            font-weight: 600;
            color: #334155;
        }

        .form-group label .required {
            color: #ef4444;
        }

        .form-control {
            width: 100%;
            padding: 11px 14px;
            font-size: 14.5px;
            border: 1px solid var(--border-color);
            border-radius: 6px;
            outline: none;
            transition: all 0.2s ease;
            font-family: inherit;
            background-color: #ffffff;
        }

        .form-control:focus {
            border-color: var(--primary-green);
            box-shadow: 0 0 0 3px rgba(72, 106, 71, 0.2);
        }

        .row-scores {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 16px;
        }

        .note-formula {
            background-color: #f8fafc;
            border-left: 3px solid var(--primary-green);
            padding: 10px 14px;
            font-size: 13px;
            color: #475569;
            margin-bottom: 20px;
            border-radius: 0 6px 6px 0;
        }

        .btn-group {
            display: flex;
            gap: 12px;
            margin-top: 28px;
        }

        .btn {
            padding: 11px 22px;
            font-size: 14.5px;
            font-weight: 600;
            border-radius: 6px;
            cursor: pointer;
            border: none;
            text-decoration: none;
            display: inline-flex;
            align-items: center;
            justify-content: center;
            gap: 8px;
            transition: all 0.2s;
        }

        .btn-primary {
            background-color: var(--primary-green);
            color: #ffffff;
            flex: 1;
        }

        .btn-primary:hover {
            background-color: var(--primary-dark);
        }

        .btn-secondary {
            background-color: #e2e8f0;
            color: #475569;
        }

        .btn-secondary:hover {
            background-color: #cbd5e1;
            color: #1e293b;
        }
    </style>
</head>
<body>

    <header class="top-banner">
        Student Information System
    </header>

    <div class="container">
        <div class="form-card">
            <div class="form-header">
                <h2><i class="fa-solid fa-pen-to-square"></i> Cập Nhật Điểm Môn Học</h2>
            </div>

            <c:if test="${not empty error}">
                <div class="alert-error">
                    <i class="fa-solid fa-circle-exclamation"></i>
                    <span>${error}</span>
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/score/edit" method="post">
                <input type="hidden" name="student_score_id" value="${score.studentScoreId}">

                <!-- Select Student -->
                <div class="form-group">
                    <label for="student_id">Chọn Sinh viên <span class="required">*</span></label>
                    <select id="student_id" name="student_id" class="form-control" required autofocus>
                        <c:forEach var="s" items="${students}">
                            <option value="${s.studentId}" ${selectedStudentId == s.studentId ? 'selected' : ''}>
                                ${s.studentCode} - ${s.fullName}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Select Subject -->
                <div class="form-group">
                    <label for="subject_id">Chọn Môn học <span class="required">*</span></label>
                    <select id="subject_id" name="subject_id" class="form-control" required>
                        <c:forEach var="sub" items="${subjects}">
                            <option value="${sub.subjectId}" ${selectedSubjectId == sub.subjectId ? 'selected' : ''}>
                                ${sub.subjectCode} - ${sub.subjectName} (${sub.credit} tín chỉ)
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Scores Row -->
                <div class="row-scores">
                    <div class="form-group">
                        <label for="score1">Score 1 (30%) <span class="required">*</span></label>
                        <input type="number" step="0.1" min="0" max="10" id="score1" name="score1"
                               class="form-control" placeholder="0.0 - 10.0" value="${score1}" required>
                    </div>

                    <div class="form-group">
                        <label for="score2">Score 2 (70%) <span class="required">*</span></label>
                        <input type="number" step="0.1" min="0" max="10" id="score2" name="score2"
                               class="form-control" placeholder="0.0 - 10.0" value="${score2}" required>
                    </div>
                </div>

                <div class="note-formula">
                    <i class="fa-solid fa-circle-info"></i> Công thức: <strong>Grade Score = 0.3 &times; Score 1 + 0.7 &times; Score 2</strong>
                </div>

                <div class="btn-group">
                    <button type="submit" class="btn btn-primary">
                        <i class="fa-solid fa-floppy-disk"></i> Cập Nhật Điểm
                    </button>
                    <a href="${pageContext.request.contextPath}/students" class="btn btn-secondary">
                        <i class="fa-solid fa-arrow-left"></i> Quay lại
                    </a>
                </div>
            </form>
        </div>
    </div>

</body>
</html>

