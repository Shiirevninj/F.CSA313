package mn.edu.must.sqat;

public class GradeCalculator {

    // 90+ -> A, 80-89 -> B, 70-79 -> C, 60-69 -> D, <60 -> F
    // 0-100 хязгаараас гарвал алдаа
    public String letterGrade(double score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }

        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    // Ирц 10, лаб 40, сорил1 10, сорил2 10, шалгалт 30
    public double totalScore(
            double att,
            double lab,
            double quiz1,
            double quiz2,
            double exam) {

        if (att < 0 || att > 10) {
            throw new IllegalArgumentException("Attendance must be between 0 and 10");
        }

        if (lab < 0 || lab > 40) {
            throw new IllegalArgumentException("Lab must be between 0 and 40");
        }

        if (quiz1 < 0 || quiz1 > 10) {
            throw new IllegalArgumentException("Quiz1 must be between 0 and 10");
        }

        if (quiz2 < 0 || quiz2 > 10) {
            throw new IllegalArgumentException("Quiz2 must be between 0 and 10");
        }

        if (exam < 0 || exam > 30) {
            throw new IllegalArgumentException("Exam must be between 0 and 30");
        }

        return att + lab + quiz1 + quiz2 + exam;
    }
}