
package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BonusTest {

    // B1: Хоосон массив, threshold = 0
    @Test
    void bonusEmptyAttendance() {
        assertEquals(5, DataFlowTargets.bonus(new int[]{}, 0));
    }

    // B2: Ирсэн болон тасалсан өдрүүд холилдсон
    @Test
    void bonusMixedAttendance() {
        assertEquals(5, DataFlowTargets.bonus(
            new int[]{1, 1, 0, 1}, 3
        ));
    }

    // B3: Дараалсан 5 ирц, нэмэлт 3 оноо
    @Test
    void bonusFiveConsecutive() {
        assertEquals(8, DataFlowTargets.bonus(
            new int[]{1, 1, 1, 1, 1}, 5
        ));
    }

    // B4: Ирц босгод хүрээгүй
    @Test
    void bonusBelowThreshold() {
        assertEquals(0, DataFlowTargets.bonus(
            new int[]{1, 0}, 3
        ));
    }

    // B5: Null массив
    @Test
    void bonusNullAttendance() {
        assertThrows(IllegalArgumentException.class,
            () -> DataFlowTargets.bonus(null, 0));
    }

    // B6: Сөрөг threshold
    @Test
    void bonusNegativeThreshold() {
        assertThrows(IllegalArgumentException.class,
            () -> DataFlowTargets.bonus(new int[]{1}, -1));
    }
}
