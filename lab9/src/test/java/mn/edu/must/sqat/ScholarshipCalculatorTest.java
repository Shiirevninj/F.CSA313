
package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calculator =
            new ScholarshipCalculator();

    @Test
    void testLetterGradeA() {
        assertEquals("A", calculator.letterGrade(95));
    }

    @Test
    void testLetterGradeB() {
        assertEquals("B", calculator.letterGrade(85));
    }

    @Test
    void testGpaNormal() {
        double[] scores = {95, 85};
        assertEquals(3.5, calculator.gpa(scores), 0.001);
    }

    @Test
    void testScholarship100() {
        assertEquals(100, calculator.scholarship(3.9, 16, false));
    }

    @Test
    void testScholarship70() {
        assertEquals(70, calculator.scholarship(3.5, 13, false));
    }

    @Test
    void testLetterGradeC() {
        assertEquals("C", calculator.letterGrade(75));
    }

    @Test
    void testLetterGradeD() {
        assertEquals("D", calculator.letterGrade(65));
    }

    @Test
    void testLetterGradeF() {
        assertEquals("F", calculator.letterGrade(50));
    }

    @Test
    void testLetterGradeNegativeScore() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.letterGrade(-1));
    }

    @Test
    void testLetterGradeAbove100() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.letterGrade(101));
    }

    @Test
    void testGpaWithC() {
        double[] scores = {75, 75};
        assertEquals(2.0, calculator.gpa(scores), 0.001);
    }

    @Test
    void testGpaWithD() {
        double[] scores = {65, 65};
        assertEquals(1.0, calculator.gpa(scores), 0.001);
    }

    @Test
    void testGpaWithF() {
        double[] scores = {50, 50};
        assertEquals(0.0, calculator.gpa(scores), 0.001);
    }
    @Test
    void testGpaNull() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.gpa(null));
    }

    @Test
    void testGpaEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.gpa(new double[]{}));
    }

    @Test
    void testScholarshipNegativeGpa() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.scholarship(-1.0, 15, false));
    }

    @Test
    void testScholarshipAboveMaxGpa() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.scholarship(4.5, 15, false));
    }

    @Test
    void testScholarshipNegativeCredits() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.scholarship(3.5, -1, false));
    }

    @Test
    void testScholarshipWithViolation() {
        assertEquals(0, calculator.scholarship(3.9, 16, true));
    }

    @Test
    void testScholarship100InsufficientCredits() {
        assertEquals(70, calculator.scholarship(3.9, 14, false));
    }

    @Test
    void testScholarship70InsufficientCredits() {
        assertEquals(50, calculator.scholarship(3.5, 11, false));
    }

    @Test
    void testScholarship50() {
        assertEquals(50, calculator.scholarship(3.1, 12, false));
    }

    @Test
    void testScholarshipZero() {
        assertEquals(0, calculator.scholarship(2.5, 15, false));
    }

    @Test
    void testGpaSingleElement() {
        double[] scores = {95};
        assertEquals(4.0, calculator.gpa(scores), 0.001);
    }

    @Test
    void testGpaMultipleElements() {
        double[] scores = {95, 85, 75, 65};
        assertEquals(2.5, calculator.gpa(scores), 0.001);
    }



}
