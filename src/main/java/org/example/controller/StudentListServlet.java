package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.ScoreDAO;
import org.example.entity.StudentScoreDTO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentListServlet", urlPatterns = {"/students", "/index"})
public class StudentListServlet extends HttpServlet {

    private final ScoreDAO scoreDAO = new ScoreDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String studentId = request.getParameter("student_id");
        String studentName = request.getParameter("student_name");
        String subjectName = request.getParameter("subject_name");
        String creditStr = request.getParameter("credit");
        String grade = request.getParameter("grade");

        Integer credit = null;
        if (creditStr != null && !creditStr.trim().isEmpty()) {
            try {
                credit = Integer.parseInt(creditStr.trim());
            } catch (NumberFormatException ignored) {
            }
        }

        List<StudentScoreDTO> scoreList = scoreDAO.searchScoreDTOs(
                studentId, studentName, subjectName, credit, grade
        );

        request.setAttribute("scoreList", scoreList);
        request.setAttribute("filterStudentId", studentId);
        request.setAttribute("filterStudentName", studentName);
        request.setAttribute("filterSubjectName", subjectName);
        request.setAttribute("filterCredit", creditStr);
        request.setAttribute("filterGrade", grade);

        request.getRequestDispatcher("/student-list.jsp").forward(request, response);
    }
}

