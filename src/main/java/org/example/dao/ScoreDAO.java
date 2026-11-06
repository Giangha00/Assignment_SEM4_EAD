package org.example.dao;

import org.example.entity.Score;
import org.example.entity.StudentScoreDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreDAO {

    public List<StudentScoreDTO> getAllScoreDTOs() {
        return searchScoreDTOs(null, null, null, null, null);
    }

    public List<StudentScoreDTO> searchScoreDTOs(String studentCode, String studentName, String subjectName, Integer credit, String grade) {
        List<StudentScoreDTO> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT ss.student_score_id, s.student_id, s.student_code, s.full_name, " +
                "sub.subject_id, sub.subject_code, sub.subject_name, ss.score1, ss.score2, sub.credit " +
                "FROM student_score_t ss " +
                "JOIN student_t s ON ss.student_id = s.student_id " +
                "JOIN subject_t sub ON ss.subject_id = sub.subject_id " +
                "WHERE 1=1 "
        );

        List<Object> params = new ArrayList<>();

        if (studentCode != null && !studentCode.trim().isEmpty()) {
            sql.append("AND s.student_code LIKE ? ");
            params.add("%" + studentCode.trim() + "%");
        }
        if (studentName != null && !studentName.trim().isEmpty()) {
            sql.append("AND s.full_name LIKE ? ");
            params.add("%" + studentName.trim() + "%");
        }
        if (subjectName != null && !subjectName.trim().isEmpty()) {
            sql.append("AND (sub.subject_name LIKE ? OR sub.subject_code LIKE ?) ");
            params.add("%" + subjectName.trim() + "%");
            params.add("%" + subjectName.trim() + "%");
        }
        if (credit != null && credit > 0) {
            sql.append("AND sub.credit = ? ");
            params.add(credit);
        }

        sql.append("ORDER BY ss.student_score_id ASC");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            rs = ps.executeQuery();
            int stt = 1;
            while (rs.next()) {
                StudentScoreDTO dto = new StudentScoreDTO(
                        stt,
                        rs.getInt("student_score_id"),
                        rs.getInt("student_id"),
                        rs.getString("student_code"),
                        rs.getString("full_name"),
                        rs.getInt("subject_id"),
                        rs.getString("subject_code"),
                        rs.getString("subject_name"),
                        rs.getDouble("score1"),
                        rs.getDouble("score2"),
                        rs.getInt("credit")
                );

                if (grade != null && !grade.trim().isEmpty()) {
                    if (dto.getGrade().equalsIgnoreCase(grade.trim())) {
                        dto.setId(stt++);
                        list.add(dto);
                    }
                } else {
                    dto.setId(stt++);
                    list.add(dto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }
        return list;
    }

    public boolean insertScore(Score score) {
        String sql = "INSERT INTO student_score_t (student_id, subject_id, score1, score2) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, score.getStudentId());
            ps.setInt(2, score.getSubjectId());
            ps.setDouble(3, score.getScore1());
            ps.setDouble(4, score.getScore2());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(ps, conn);
        }
    }

    public Score getScoreById(int scoreId) {
        String sql = "SELECT student_score_id, student_id, subject_id, score1, score2 FROM student_score_t WHERE student_score_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, scoreId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Score(
                        rs.getInt("student_score_id"),
                        rs.getInt("student_id"),
                        rs.getInt("subject_id"),
                        rs.getDouble("score1"),
                        rs.getDouble("score2")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }
        return null;
    }

    public boolean updateScore(Score score) {
        String sql = "UPDATE student_score_t SET student_id = ?, subject_id = ?, score1 = ?, score2 = ? WHERE student_score_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, score.getStudentId());
            ps.setInt(2, score.getSubjectId());
            ps.setDouble(3, score.getScore1());
            ps.setDouble(4, score.getScore2());
            ps.setInt(5, score.getStudentScoreId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(ps, conn);
        }
    }

    public boolean deleteScore(int scoreId) {
        String sql = "DELETE FROM student_score_t WHERE student_score_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, scoreId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(ps, conn);
        }
    }
}

