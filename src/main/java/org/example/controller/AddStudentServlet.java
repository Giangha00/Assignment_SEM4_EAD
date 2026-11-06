package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.StudentDAO;
import org.example.entity.Student;

import java.io.IOException;

@WebServlet(name = "AddStudentServlet", urlPatterns = {"/student/add"})
public class AddStudentServlet extends HttpServlet {

    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/add-student.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String studentCode = request.getParameter("student_code");
        String fullName = request.getParameter("full_name");
        String address = request.getParameter("address");

        if (studentCode == null || studentCode.trim().isEmpty() ||
                fullName == null || fullName.trim().isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ Mã sinh viên và Họ tên!");
            request.setAttribute("student_code", studentCode);
            request.setAttribute("full_name", fullName);
            request.setAttribute("address", address);
            request.getRequestDispatcher("/add-student.jsp").forward(request, response);
            return;
        }

        studentCode = studentCode.trim();
        fullName = fullName.trim();
        address = (address != null) ? address.trim() : "";

        if (studentDAO.isStudentCodeExists(studentCode)) {
            request.setAttribute("error", "Mã sinh viên '" + studentCode + "' đã tồn tại trong hệ thống!");
            request.setAttribute("student_code", studentCode);
            request.setAttribute("full_name", fullName);
            request.setAttribute("address", address);
            request.getRequestDispatcher("/add-student.jsp").forward(request, response);
            return;
        }

        Student student = new Student(studentCode, fullName, address);
        boolean success = studentDAO.insertStudent(student);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/students?success=student_created");
        } else {
            request.setAttribute("error", "Lỗi khi lưu sinh viên vào cơ sở dữ liệu!");
            request.getRequestDispatcher("/add-student.jsp").forward(request, response);
        }
    }
}

