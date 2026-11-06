package org.example.entity;

public class Score {
    private int studentScoreId;
    private int studentId;
    private int subjectId;
    private double score1;
    private double score2;

    public Score() {
    }

    public Score(int studentId, int subjectId, double score1, double score2) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.score1 = score1;
        this.score2 = score2;
    }

    public Score(int studentScoreId, int studentId, int subjectId, double score1, double score2) {
        this.studentScoreId = studentScoreId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.score1 = score1;
        this.score2 = score2;
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

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
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

    /**
     * Cong thuc tinh diem: Grade = 0.3 * score1 + 0.7 * score2
     */
    public double calculateGradeScore() {
        return Math.round((0.3 * score1 + 0.7 * score2) * 100.0) / 100.0;
    }

    /**
     * Bang quy doi Grade:
     * 8.0 to 10   -> A
     * 6.0 to 7.9  -> B
     * 4.0 to 5.9  -> D
     * less than 4 -> F
     */
    public String calculateGrade() {
        double gradeScore = calculateGradeScore();
        if (gradeScore >= 8.0) {
            return "A";
        } else if (gradeScore >= 6.0) {
            return "B";
        } else if (gradeScore >= 4.0) {
            return "D";
        } else {
            return "F";
        }
    }

    @Override
    public String toString() {
        return "Score{" +
                "studentScoreId=" + studentScoreId +
                ", studentId=" + studentId +
                ", subjectId=" + subjectId +
                ", score1=" + score1 +
                ", score2=" + score2 +
                '}';
    }
}

