package org.example.dao;

import org.example.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT student_id, student_code, full_name, address FROM student_t ORDER BY student_id ASC";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("student_id"),
                        rs.getString("student_code"),
                        rs.getString("full_name"),
                        rs.getString("address")
                );
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }
        return list;
    }

    public Student getStudentById(int studentId) {
        String sql = "SELECT student_id, student_code, full_name, address FROM student_t WHERE student_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Student(
                        rs.getInt("student_id"),
                        rs.getString("student_code"),
                        rs.getString("full_name"),
                        rs.getString("address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }
        return null;
    }

    public boolean insertStudent(Student student) {
        String sql = "INSERT INTO student_t (student_code, full_name, address) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, student.getStudentCode());
            ps.setString(2, student.getFullName());
            ps.setString(3, student.getAddress());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(ps, conn);
        }
    }

    public boolean isStudentCodeExists(String studentCode) {
        String sql = "SELECT COUNT(*) FROM student_t WHERE student_code = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, studentCode);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }
        return false;
    }

    public boolean updateStudent(Student student) {
        String sql = "UPDATE student_t SET student_code = ?, full_name = ?, address = ? WHERE student_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, student.getStudentCode());
            ps.setString(2, student.getFullName());
            ps.setString(3, student.getAddress());
            ps.setInt(4, student.getStudentId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(ps, conn);
        }
    }

    public boolean deleteStudent(int studentId) {
        Connection conn = null;
        PreparedStatement psScore = null;
        PreparedStatement psStudent = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            // Xoa diem cua sinh vien truoc (tranh loi foreign key constraint)
            String sqlScore = "DELETE FROM student_score_t WHERE student_id = ?";
            psScore = conn.prepareStatement(sqlScore);
            psScore.setInt(1, studentId);
            psScore.executeUpdate();

            // Xoa sinh vien
            String sqlStudent = "DELETE FROM student_t WHERE student_id = ?";
            psStudent = conn.prepareStatement(sqlStudent);
            psStudent.setInt(1, studentId);
            int affected = psStudent.executeUpdate();

            conn.commit();
            return affected > 0;
        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ignored) {}
            }
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(psScore, psStudent, conn);
        }
    }
}

