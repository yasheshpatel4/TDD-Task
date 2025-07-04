import org.example.StringCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    private static final int MAX = Integer.MAX_VALUE;
    private static final int MIN = Integer.MIN_VALUE;

    // Test: Empty input should return 0
    @Test
    void add_emptyInput_returnsZero() {
        assertEquals(0, StringCalculator.add(""));
    }

    // Test: Single number should return itself
    @Test
    void add_singleNumber_returnsValue() {
        assertEquals(100, StringCalculator.add("100"));
    }

    // Test: Simple comma-separated numbers should return correct sum
    @Test
    void add_commaSeparated_returnsSum() {
        assertEquals(6, StringCalculator.add("1,2,3"));
    }

    // Test: Input with newline and comma as delimiters should return correct sum
    @Test
    void add_mixedDelimiters_returnsSum() {
        assertEquals(60, StringCalculator.add("10\n20,30"));
    }

    // Test: Input with spaces around numbers should still be parsed correctly
    @Test
    void add_numbersWithSpaces_returnsSum() {
        assertEquals(20, StringCalculator.add(" 5 , 15 "));
    }

    // Test: Input containing Integer.MAX_VALUE should return MAX
    @Test
    void add_inputMaxValue_returnsMaxValue() {
        assertEquals(MAX, StringCalculator.add(String.valueOf(MAX)));
    }

    // Test: Input containing Integer.MIN_VALUE should throw exception
    @Test
    void add_inputMinValue_throwsIllegalArgumentException() {
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                StringCalculator.add(String.valueOf(MIN))
        );
        assertTrue(e.getMessage().contains(String.valueOf(MIN)));
    }

    // Test: Adding numbers that exceed Integer.MAX_VALUE should throw overflow exception
    @Test
    void add_sumExceedsMaxValue_throwsArithmeticException() {
        String input = MAX + ",1";
        assertThrows(ArithmeticException.class, () -> StringCalculator.add(input));
    }

    // Test: Sum close to Integer.MAX_VALUE should return correct value
    @Test
    void add_sumNearMaxValue_returnsMaxValue() {
        String input = (MAX - 1) + ",1";
        assertEquals(MAX, StringCalculator.add(input));
    }

    // Test: Negative numbers should throw exception and list them in message
    @Test
    void add_negativeNumbers_throwsException() {
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                StringCalculator.add("5,-2,4,-7")
        );
        assertTrue(e.getMessage().contains("-2"));
        assertTrue(e.getMessage().contains("-7"));
    }

    // Test: Custom delimiter (single character) is supported
    @Test
    void add_customDelimiter_singleCharacter_returnsSum() {
        assertEquals(6, StringCalculator.add("//;\n1;2;3"));
    }

    // Test: Multi-character delimiter is supported
    @Test
    void add_customDelimiter_multiCharacter_returnsSum() {
        assertEquals(6, StringCalculator.add("//[***]\n1***2***3"));
    }

    // Test: Multiple single-character delimiters are supported
    @Test
    void add_multipleDelimiters_singleChar_returnsSum() {
        assertEquals(6, StringCalculator.add("//[*][%]\n1*2%3"));
    }

    // Test: Multiple multi-character delimiters are supported
    @Test
    void add_multipleDelimiters_multiChar_returnsSum() {
        assertEquals(6, StringCalculator.add("//[***][%%]\n1***2%%3"));
    }

    // Test: Input with non-numeric value should throw NumberFormatException
    @Test
    void add_nonNumericInput_throwsNumberFormatException() {
        assertThrows(NumberFormatException.class, () ->
                StringCalculator.add("1,abc,3")
        );
    }
}
