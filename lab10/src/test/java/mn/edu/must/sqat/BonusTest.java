
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

    // B7: Өмнөх хамгийн урт дарааллыг хадгалах
    // best = streak (43) утга дараагийн богино
    // дарааллын үед дахин тодорхойлогдохгүй.
    @Test
    void bonusPreservesBestStreak() {
        assertEquals(8, DataFlowTargets.bonus(
                new int[]{1, 1, 1, 1, 1, 0, 1, 1}, 7
        ));
    }

    // B8: score хувьсагчийн 49 -> 54 DU хос
    // present < threshold тул score = 5 ажиллахгүй.
    // best >= 5 тул score = score + 3 ажиллана.
    @Test
    void bonusOnlyStreakPoints() {
        assertEquals(3, DataFlowTargets.bonus(
                new int[]{1, 1, 1, 1, 1}, 6
        ));
    }

    @Test
    void bonusEmptyBelowThreshold() {
        assertEquals(0, DataFlowTargets.bonus(new int[]{}, 1));
    }
    // B10: M2 алдааг илрүүлэх тест
// Нийт ирц 5 боловч хамгийн урт дараалал 3.
    @Test
    void bonusDetectsIncorrectBest() {
        assertEquals(0, DataFlowTargets.bonus(
                new int[]{1, 1, 0, 1, 1, 1}, 6
        ));
    }


}
