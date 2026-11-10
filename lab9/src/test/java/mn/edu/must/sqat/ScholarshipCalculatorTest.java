
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
}
