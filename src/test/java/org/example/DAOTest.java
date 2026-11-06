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

    @Test
    public void testFilters() {
        ScoreDAO dao = new ScoreDAO();

        // 1. Filter by Student Id
        List<StudentScoreDTO> byStudentId = dao.searchScoreDTOs("2007A10", null, null, null, null);
        assertNotNull(byStudentId);
        assertFalse(byStudentId.isEmpty());
        assertEquals("2007A10", byStudentId.get(0).getStudentCode());

        // 2. Filter by Student Name
        List<StudentScoreDTO> byName = dao.searchScoreDTOs(null, "Nguyễn Văn A", null, null, null);
        assertNotNull(byName);
        assertFalse(byName.isEmpty());

        // 3. Filter by Subject Name
        List<StudentScoreDTO> bySubject = dao.searchScoreDTOs(null, null, "JAVA", null, null);
        assertNotNull(bySubject);
        assertFalse(bySubject.isEmpty());

        // 4. Filter by Credit
        List<StudentScoreDTO> byCredit = dao.searchScoreDTOs(null, null, null, 4, null);
        assertNotNull(byCredit);
        assertFalse(byCredit.isEmpty());
        assertEquals(4, byCredit.get(0).getCredit());

        // 5. Filter by Grade
        List<StudentScoreDTO> byGradeA = dao.searchScoreDTOs(null, null, null, null, "A");
        assertNotNull(byGradeA);
        for (StudentScoreDTO dto : byGradeA) {
            assertEquals("A", dto.getGrade());
        }
    }
}
