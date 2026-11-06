package org.example;

import org.example.dao.ScoreDAO;
import org.example.dao.StudentDAO;
import org.example.dao.SubjectDAO;
import org.example.entity.Student;
import org.example.entity.StudentScoreDTO;
import org.example.entity.Subject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DAOTest {

    @Test
    public void testStudentDAO() {
        StudentDAO dao = new StudentDAO();
        List<Student> students = dao.getAllStudents();
        assertNotNull(students);
        assertFalse(students.isEmpty());
        System.out.println("Students count: " + students.size());
    }

    @Test
    public void testSubjectDAO() {
        SubjectDAO dao = new SubjectDAO();
        List<Subject> subjects = dao.getAllSubjects();
        assertNotNull(subjects);
        assertFalse(subjects.isEmpty());
        System.out.println("Subjects count: " + subjects.size());
    }

    @Test
    public void testScoreDAO() {
        ScoreDAO dao = new ScoreDAO();
        List<StudentScoreDTO> dtos = dao.getAllScoreDTOs();
        assertNotNull(dtos);
        assertFalse(dtos.isEmpty());
        for (StudentScoreDTO dto : dtos) {
            System.out.printf("Student: %s | Subject: %s | Final: %.2f | Grade: %s\n",
                    dto.getStudentCode(), dto.getSubjectCode(), dto.getGradeScore(), dto.getGrade());
            assertNotNull(dto.getGrade());
        }
    }
}

