# Student Information System (SIS) - Enterprise Application Development in Java EE

## 📌 Giới thiệu dự án
Dự án được xây dựng theo đề thi thực hành môn **Enterprise Application Development in Java EE (SET01 - FPT Aptech)**:
- **Hệ thống**: Quản lý thông tin sinh viên, môn học và điểm số (Student Information System - SIS).
- **Mục tiêu**: Quản lý hồ sơ sinh viên, nhập điểm môn học, tự động tính điểm tổng kết và quy đổi xếp loại (Grade).

---

## 🛠️ Công nghệ & Môi trường sử dụng
- **Java**: Java 17 LTS
- **Web Container**: Apache Tomcat 11.0.25 (Jakarta EE 10 / Servlet 6.0, JSP 4.0, JSTL 3.0)
- **Cơ sở dữ liệu**: MySQL trên XAMPP (Port 3306)
- **Công cụ quản lý dự án**: Apache Maven
- **IDE**: IntelliJ IDEA (Chạy qua plugin Smart Tomcat)

---

## 🏗️ Cấu trúc thư mục chuẩn mô hình MVC
```
Assignment_SEM4_EAD/
├── .idea/runConfigurations/Tomcat_11.xml   # File chạy Tomcat 11 Smart Tomcat
├── sql/
│   └── init_database.sql                  # Script tạo Database sis và dữ liệu mẫu
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── entity/                    # Tầng thực thể (Question 1 & 2)
│   │   │   │   ├── Student.java
│   │   │   │   ├── Subject.java
│   │   │   │   ├── Score.java
│   │   │   │   └── StudentScoreDTO.java
│   │   │   ├── dao/                       # Tầng truy xuất CSDL
│   │   │   │   ├── DBUtil.java
│   │   │   │   ├── StudentDAO.java
│   │   │   │   ├── SubjectDAO.java
│   │   │   │   └── ScoreDAO.java
│   │   │   └── controller/                # Tầng điều hướng (Servlet & Filter)
│   │   │       ├── CharacterEncodingFilter.java # Lọc tiếng Việt UTF-8
│   │   │       ├── StudentListServlet.java      # Danh sách & Bộ lọc
│   │   │       ├── AddStudentServlet.java       # Thêm sinh viên (+ Student)
│   │   │       ├── AddScoreServlet.java         # Nhập điểm (+ Score)
│   │   │       ├── EditScoreServlet.java        # Chỉnh sửa điểm
│   │   │       └── DeleteScoreServlet.java      # Xóa điểm
│   │   ├── resources/
│   │   │   └── db.properties              # Cấu hình kết nối MySQL XAMPP
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── web.xml                # Descriptor chuẩn Servlet 6.0
│   │       ├── index.jsp                  # Điều hướng về /students
│   │       ├── student-list.jsp           # Giao diện chính kèm bộ lọc realtime
│   │       ├── add-student.jsp            # Form thêm sinh viên
│   │       ├── add-score.jsp              # Form nhập điểm
│   │       └── edit-score.jsp             # Form sửa điểm
│   └── test/java/org/example/
│       └── DAOTest.java                   # Unit Test kiểm tra CSDL và 5 bộ lọc
└── pom.xml                                # Cấu hình Maven
```

---

## 📊 Quy tắc tính điểm & Xếp loại (Business Logic)
1. **Công thức tính điểm**:
   $$\text{Grade Score} = 0.3 \times \text{Score 1} + 0.7 \times \text{Score 2}$$
2. **Bảng quy đổi Grade**:
   - `8.0` đến `10`: **A**
   - `6.0` đến `7.9`: **B**
   - `4.0` đến `5.9`: **D**
   - Dưới `4.0`: **F**

---

## 🔍 Tính năng Bộ lọc & Tìm kiếm nhanh
Hệ thống hỗ trợ 5 tiêu chí lọc kết hợp cả **Backend dynamic SQL** và **Client-side realtime search** (tìm tức thì khi gõ):
1. **Student Id**: Tìm kiếm theo mã sinh viên (VD: `2007A1`).
2. **Student Name**: Tìm kiếm theo họ và tên sinh viên.
3. **Subject Name**: Tìm kiếm theo tên hoặc mã môn học (`JAVA`, `PHP`, `WDA`...).
4. **Credit**: Lọc theo số tín chỉ (1, 2, 3, 4, 5).
5. **Grade**: Lọc nhanh theo phân loại học lực (`A`, `B`, `D`, `F`).

---

## 🚀 Hướng dẫn cài đặt & Khởi chạy

### 1. Khởi động MySQL trên XAMPP
1. Mở **XAMPP Control Panel** và bấm **Start** tại mục **MySQL**.
2. Import cơ sở dữ liệu:
   ```bash
   mysql -u root < sql/init_database.sql
   ```

### 2. Chạy ứng dụng trên IntelliJ IDEA
1. Mở dự án trong IntelliJ IDEA.
2. Kiểm tra plugin **Smart Tomcat** đã được cài đặt trong `Settings` > `Plugins`.
3. Tại thanh công cụ chạy trên cùng bên phải, cấu hình **Tomcat 11** đã được tạo sẵn.
4. Bấm **Run (▶️)**.
5. Truy cập ứng dụng tại:
   ```
   http://localhost:8080/Assignment_SEM4_EAD
   ```

