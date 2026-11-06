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

@WebServlet(name = "EditScoreServlet", urlPatterns = {"/score/edit"})
public class EditScoreServlet extends HttpServlet {

    private final StudentDAO studentDAO = new StudentDAO();
    private final SubjectDAO subjectDAO = new SubjectDAO();
    private final ScoreDAO scoreDAO = new ScoreDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/students");
            return;
        }

        try {
            int scoreId = Integer.parseInt(idStr);
            Score score = scoreDAO.getScoreById(scoreId);
            if (score == null) {
                response.sendRedirect(request.getContextPath() + "/students");
                return;
            }

            request.setAttribute("score", score);
            request.setAttribute("selectedStudentId", score.getStudentId());
            request.setAttribute("selectedSubjectId", score.getSubjectId());
            request.setAttribute("score1", score.getScore1());
            request.setAttribute("score2", score.getScore2());
            loadFormData(request);
            request.getRequestDispatcher("/edit-score.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/students");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("student_score_id");
        String studentIdStr = request.getParameter("student_id");
        String subjectIdStr = request.getParameter("subject_id");
        String score1Str = request.getParameter("score1");
        String score2Str = request.getParameter("score2");

        if (idStr == null || studentIdStr == null || subjectIdStr == null || score1Str == null || score2Str == null) {
            response.sendRedirect(request.getContextPath() + "/students");
            return;
        }

        try {
            int scoreId = Integer.parseInt(idStr);
            int studentId = Integer.parseInt(studentIdStr);
            int subjectId = Integer.parseInt(subjectIdStr);
            double score1 = Double.parseDouble(score1Str.trim());
            double score2 = Double.parseDouble(score2Str.trim());

            if (score1 < 0.0 || score1 > 10.0 || score2 < 0.0 || score2 > 10.0) {
                request.setAttribute("error", "Điểm số phải nằm trong khoảng từ 0.0 đến 10.0!");
                request.setAttribute("score", new Score(scoreId, studentId, subjectId, score1, score2));
                request.setAttribute("selectedStudentId", studentId);
                request.setAttribute("selectedSubjectId", subjectId);
                request.setAttribute("score1", score1Str);
                request.setAttribute("score2", score2Str);
                loadFormData(request);
                request.getRequestDispatcher("/edit-score.jsp").forward(request, response);
                return;
            }

            Score score = new Score(scoreId, studentId, subjectId, score1, score2);
            boolean success = scoreDAO.updateScore(score);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/students?success=score_updated");
            } else {
                request.setAttribute("error", "Lỗi khi cập nhật điểm số vào cơ sở dữ liệu!");
                request.setAttribute("score", score);
                loadFormData(request);
                request.getRequestDispatcher("/edit-score.jsp").forward(request, response);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Điểm số không hợp lệ, vui lòng nhập số thực!");
            loadFormData(request);
            request.getRequestDispatcher("/edit-score.jsp").forward(request, response);
        }
    }

    private void loadFormData(HttpServletRequest request) {
        List<Student> students = studentDAO.getAllStudents();
        List<Subject> subjects = subjectDAO.getAllSubjects();
        request.setAttribute("students", students);
        request.setAttribute("subjects", subjects);
    }
}
