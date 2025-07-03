import org.example.StringCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

class StringCalculatorTest {

    @Test
    void testMaxIntValue() {
        assertEquals(Integer.MAX_VALUE, StringCalculator.add(String.valueOf(Integer.MAX_VALUE)));
    }

    @Test
    void testMinIntThrowsException() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            StringCalculator.add(String.valueOf(Integer.MIN_VALUE));
        });
        assertTrue(e.getMessage().contains(String.valueOf(Integer.MIN_VALUE)));
    }

    @Test
    void testSumOverflowThrows() {
        String input = Integer.MAX_VALUE + "," + 1;
        assertThrows(ArithmeticException.class, () -> {
            StringCalculator.add(input);
        });
    }

    @Test
    void testSumNearMaxAllowed() {
        String input = (Integer.MAX_VALUE - 1) + "," + 1;
        assertEquals(Integer.MAX_VALUE, StringCalculator.add(input));
    }

    @Test
    void testEmptyInputReturnsZero() {
        assertEquals(0, StringCalculator.add(""));
    }

    @Test
    void testMultipleSmallNumbers() {
        assertEquals(6, StringCalculator.add("1,2,3"));
    }
}