<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm mới Sinh viên - SIS</title>
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
        }

        .form-control:focus {
            border-color: var(--primary-green);
            box-shadow: 0 0 0 3px rgba(72, 106, 71, 0.2);
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
                <h2><i class="fa-solid fa-user-plus"></i> Thêm Sinh Viên Mới (+ Student)</h2>
            </div>

            <c:if test="${not empty error}">
                <div class="alert-error">
                    <i class="fa-solid fa-circle-exclamation"></i>
                    <span>${error}</span>
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/student/add" method="post">
                <div class="form-group">
                    <label for="student_code">Mã sinh viên (Student Code) <span class="required">*</span></label>
                    <input type="text" id="student_code" name="student_code" class="form-control"
                           placeholder="Ví dụ: 2007A10" value="${student_code}" required autofocus>
                </div>

                <div class="form-group">
                    <label for="full_name">Họ và tên (Full Name) <span class="required">*</span></label>
                    <input type="text" id="full_name" name="full_name" class="form-control"
                           placeholder="Ví dụ: Nguyễn Văn A" value="${full_name}" required>
                </div>

                <div class="form-group">
                    <label for="address">Địa chỉ (Address)</label>
                    <input type="text" id="address" name="address" class="form-control"
                           placeholder="Ví dụ: Hà Nội" value="${address}">
                </div>

                <div class="btn-group">
                    <button type="submit" class="btn btn-primary">
                        <i class="fa-solid fa-floppy-disk"></i> Lưu Sinh Viên
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

