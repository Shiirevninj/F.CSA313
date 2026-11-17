
package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GcdTest {

    @Test
    @DisplayName("GCD zero iterations: x DU 13-20, y DU 13-15F")
    void gcdZeroIterations() {
        assertEquals(7, DataFlowTargets.gcd(7, 0));
    }

    @Test
    @DisplayName("GCD two iterations: loop-carried DU pairs")
    void gcdTwoIterations() {
        assertEquals(4, DataFlowTargets.gcd(12, 8));
    }

    @Test
    @DisplayName("GCD one iteration: initial and updated definitions")
    void gcdOneIteration() {
        assertEquals(5, DataFlowTargets.gcd(0, 5));
    }
}
