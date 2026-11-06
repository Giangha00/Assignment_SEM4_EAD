package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.ScoreDAO;
import org.example.dao.StudentDAO;
import org.example.dao.SubjectDAO;
import org.example.entity.Score;
import org.example.entity.Student;
import org.example.entity.Subject;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddScoreServlet", urlPatterns = {"/score/add"})
public class AddScoreServlet extends HttpServlet {

    private final StudentDAO studentDAO = new StudentDAO();
    private final SubjectDAO subjectDAO = new SubjectDAO();
    private final ScoreDAO scoreDAO = new ScoreDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadFormData(request);
        request.getRequestDispatcher("/add-score.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String studentIdStr = request.getParameter("student_id");
        String subjectIdStr = request.getParameter("subject_id");
        String score1Str = request.getParameter("score1");
        String score2Str = request.getParameter("score2");

        if (studentIdStr == null || subjectIdStr == null || score1Str == null || score2Str == null ||
                studentIdStr.isEmpty() || subjectIdStr.isEmpty() || score1Str.trim().isEmpty() || score2Str.trim().isEmpty()) {
            request.setAttribute("error", "Vui lòng điền đầy đủ các thông tin và điểm số!");
            loadFormData(request);
            request.getRequestDispatcher("/add-score.jsp").forward(request, response);
            return;
        }

        try {
            int studentId = Integer.parseInt(studentIdStr);
            int subjectId = Integer.parseInt(subjectIdStr);
            double score1 = Double.parseDouble(score1Str.trim());
            double score2 = Double.parseDouble(score2Str.trim());

            if (score1 < 0.0 || score1 > 10.0 || score2 < 0.0 || score2 > 10.0) {
                request.setAttribute("error", "Điểm số phải nằm trong khoảng từ 0.0 đến 10.0!");
                request.setAttribute("selectedStudentId", studentId);
                request.setAttribute("selectedSubjectId", subjectId);
                request.setAttribute("score1", score1Str);
                request.setAttribute("score2", score2Str);
                loadFormData(request);
                request.getRequestDispatcher("/add-score.jsp").forward(request, response);
                return;
            }

            Score score = new Score(studentId, subjectId, score1, score2);
            boolean success = scoreDAO.insertScore(score);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/students?success=score_created");
            } else {
                request.setAttribute("error", "Lỗi khi lưu điểm số vào cơ sở dữ liệu!");
                loadFormData(request);
                request.getRequestDispatcher("/add-score.jsp").forward(request, response);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Điểm số không hợp lệ, vui lòng nhập số thực!");
            loadFormData(request);
            request.getRequestDispatcher("/add-score.jsp").forward(request, response);
        }
    }

    private void loadFormData(HttpServletRequest request) {
        List<Student> students = studentDAO.getAllStudents();
        List<Subject> subjects = subjectDAO.getAllSubjects();
        request.setAttribute("students", students);
        request.setAttribute("subjects", subjects);
    }
}

