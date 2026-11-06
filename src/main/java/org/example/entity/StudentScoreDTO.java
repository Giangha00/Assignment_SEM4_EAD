package org.example.entity;

public class StudentScoreDTO {
    private int id;
    private int studentScoreId;
    private int studentId;
    private String studentCode;
    private String studentName;
    private int subjectId;
    private String subjectCode;
    private String subjectName;
    private double score1;
    private double score2;
    private int credit;

    public StudentScoreDTO() {
    }

    public StudentScoreDTO(int id, int studentScoreId, int studentId, String studentCode, String studentName,
                           int subjectId, String subjectCode, String subjectName,
                           double score1, double score2, int credit) {
        this.id = id;
        this.studentScoreId = studentScoreId;
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.subjectId = subjectId;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.score1 = score1;
        this.score2 = score2;
        this.credit = credit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentScoreId() {
        return studentScoreId;
    }

    public void setStudentScoreId(int studentScoreId) {
        this.studentScoreId = studentScoreId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public double getScore1() {
        return score1;
    }

    public void setScore1(double score1) {
        this.score1 = score1;
    }

    public double getScore2() {
        return score2;
    }

    public void setScore2(double score2) {
        this.score2 = score2;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    /**
     * Cong thuc de bai: Grade = 0.3 * score1 + 0.7 * score2
     */
    public double getGradeScore() {
        return Math.round((0.3 * score1 + 0.7 * score2) * 100.0) / 100.0;
    }

    /**
     * Score to Grade:
     * 8.0 to 10   -> A
     * 6.0 to 7.9  -> B
     * 4.0 to 5.9  -> D
     * less than 4 -> F
     */
    public String getGrade() {
        double finalScore = getGradeScore();
        if (finalScore >= 8.0) {
            return "A";
        } else if (finalScore >= 6.0) {
            return "B";
        } else if (finalScore >= 4.0) {
            return "D";
        } else {
            return "F";
        }
    }
}

