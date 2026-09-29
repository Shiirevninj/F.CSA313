package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class GradeCalculatorTest {

    @Test
    @DisplayName("95 оноо A дүн байх ёстой")
    void score95IsA() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(95);

        // Assert
        assertEquals("A", grade);
    }

    @Test
    @DisplayName("85 оноо B дүн байх ёстой")
    void score85IsB() {
        GradeCalculator calc = new GradeCalculator();

        String grade = calc.letterGrade(85);

        assertEquals("B", grade);
    }

    @Test
    @DisplayName("75 оноо C дүн байх ёстой")
    void score75IsC() {
        GradeCalculator calc = new GradeCalculator();

        String grade = calc.letterGrade(75);

        assertEquals("C", grade);
    }

    @Test
    @DisplayName("65 оноо D дүн байх ёстой")
    void score65IsD() {
        GradeCalculator calc = new GradeCalculator();

        String grade = calc.letterGrade(65);

        assertEquals("D", grade);
    }

    @Test
    @DisplayName("30 оноо F дүн байх ёстой")
    void score30IsF() {
        GradeCalculator calc = new GradeCalculator();

        String grade = calc.letterGrade(30);

        assertEquals("F", grade);
    }

    @Test
    @DisplayName("90 оноо яг A дүн байх ёстой")
    void ninetyIsExactlyA() {
        GradeCalculator calc = new GradeCalculator();

        String grade = calc.letterGrade(90);

        assertEquals("A", grade);
    }

    @Test
    @DisplayName("89.99 оноо B дүн байх ёстой")
    void belowNinetyIsB() {
        GradeCalculator calc = new GradeCalculator();

        String grade = calc.letterGrade(89.99);

        assertEquals("B", grade);
    }

    @Test
    @DisplayName("-1 оноо IllegalArgumentException үүсгэх ёстой")
    void negativeScoreThrowsException() {
        GradeCalculator calc = new GradeCalculator();

        assertThrows(
                IllegalArgumentException.class,
                () -> calc.letterGrade(-1)
        );
    }

    @Test
    @DisplayName("101 оноо IllegalArgumentException үүсгэх ёстой")
    void overHundredThrowsException() {
        GradeCalculator calc = new GradeCalculator();

        assertThrows(
                IllegalArgumentException.class,
                () -> calc.letterGrade(101)
        );
    }

    @Test
    @DisplayName("Бүх хэсгийн дээд онооны нийлбэр 100 байх ёстой")
    void totalScoreIs100() {
        GradeCalculator calc = new GradeCalculator();

        double total = calc.totalScore(10, 40, 10, 10, 30);

        assertEquals(100, total);
    }

    @Test
    @DisplayName("Ирц сөрөг бол IllegalArgumentException үүсгэх ёстой")
    void negativeAttendanceThrowsException() {
        GradeCalculator calc = new GradeCalculator();

        assertThrows(
                IllegalArgumentException.class,
                () -> calc.totalScore(-5, 40, 10, 10, 30)
        );
    }

    @Test
    @DisplayName("Лабын оноо 40-өөс их бол IllegalArgumentException үүсгэх ёстой")
    void labOverMaximumThrowsException() {
        GradeCalculator calc = new GradeCalculator();

        assertThrows(
                IllegalArgumentException.class,
                () -> calc.totalScore(10, 41, 10, 10, 30)
        );
    }
    @ParameterizedTest
    @CsvSource({
            "10, 40, 10, 10, 30, 100",
            "5, 20, 5, 5, 15, 50",
            "0, 0, 0, 0, 0, 0",
            "8, 35, 7, 9, 25, 84"
    })
    @DisplayName("Олон төрлийн зөв онооны нийлбэрийг зөв тооцох ёстой")
    void totalScoreParameterized(
            double att,
            double lab,
            double quiz1,
            double quiz2,
            double exam,
            double expected) {

        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        double total = calc.totalScore(att, lab, quiz1, quiz2, exam);

        // Assert
        assertEquals(expected, total);
    }
    @ParameterizedTest
    @CsvSource({
            "95, A",
            "90, A",
            "89.99, B",
            "80, B",
            "70, C",
            "60, D",
            "59.99, F",
            "0, F",
            "100, A"
    })
    @DisplayName("Хязгаарын оноонуудыг зөв үсгэн дүнд хөрвүүлэх ёстой")
    void letterGradeBoundaries(double score, String expected) {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String grade = calc.letterGrade(score);

        // Assert
        assertEquals(expected, grade);
    }
}
