import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class BracesCounterTest {

    @Test
    public void isUnevenNumberOfBracesIsTrue() {
        String braces = "({)";
        assertTrue(BracesCounter.isUnevenNumberOfBraces(braces));
    }

    @Test
    public void isUnevenNumberOfBracesIsFalse() {
        String braces = "()";
        assertFalse(BracesCounter.isUnevenNumberOfBraces(braces));
    }

    @Test
    public void findCorrespondingClosingBracketReturnsInvalid() {
        String braces = "({}]";
        String matchingOpeningCharacter = braces.substring(0, 1);
        String matchingClosingCharacter = BracesCounter.BRACKETS_MAP.get(matchingOpeningCharacter);

        assertEquals(BracesCounter.findCorrespondingClosingBracket(braces,
                matchingOpeningCharacter,
                matchingClosingCharacter,
                1), -1);
    }

    @Test
    public void findCorrespondingClosingBracketReturnsValid() {
        String braces = "({})";
        String matchingOpeningCharacter = braces.substring(0, 1);
        String matchingClosingCharacter = BracesCounter.BRACKETS_MAP.get(matchingOpeningCharacter);

        assertEquals(BracesCounter.findCorrespondingClosingBracket(braces,
                matchingOpeningCharacter,
                matchingClosingCharacter,
                1), 3);
    }

    @Test
    public void findCorrespondingClosingBracketReturnsCorrectMatchingClosingBracket1() {
        /* in this test, there are a number of potential matching closing brackets
           so we must ensure it returns the correct one
           [()][[]{}] -> there are three closing square brackets with no opening between
           the first opening bracket and the first corresponding closing bracket
           so we expect index 3 to be returned
         */
        String braces = "[()][[]{}]";
        String matchingOpeningCharacter = braces.substring(0, 1);
        String matchingClosingCharacter = BracesCounter.BRACKETS_MAP.get(matchingOpeningCharacter);

        assertEquals(BracesCounter.findCorrespondingClosingBracket(braces,
                matchingOpeningCharacter,
                matchingClosingCharacter,
                1), 3);
    }

    @Test
    public void findCorrespondingClosingBracketReturnsCorrectMatchingClosingBracket2() {
        /* in this test, there are a number of potential matching closing brackets
           so we must ensure it returns the correct one
           {(){[]}} -> there are two closing curly brackets so the 2nd must be returned
           which is index 7
         */
        String braces = "{(){[]}}";
        String matchingOpeningCharacter = braces.substring(0, 1);
        String matchingClosingCharacter = BracesCounter.BRACKETS_MAP.get(matchingOpeningCharacter);

        assertEquals(BracesCounter.findCorrespondingClosingBracket(braces,
                matchingOpeningCharacter,
                matchingClosingCharacter,
                1), 7);
    }

    @Test
    public void findCorrespondingClosingBracketReturnsCorrectMatchingClosingBracket3() {
        /* in this test, there are a number of potential matching closing brackets
           so we must ensure it returns the correct one
           {(){[]}} -> there are two closing curly brackets so the 2nd must be returned
           which is index 7
         */
        String braces = "{(){[]}(){[({})]}}";
        String matchingOpeningCharacter = braces.substring(0, 1);
        String matchingClosingCharacter = BracesCounter.BRACKETS_MAP.get(matchingOpeningCharacter);

        int result = BracesCounter.findCorrespondingClosingBracket(braces,
                matchingOpeningCharacter,
                matchingClosingCharacter,
                1);

        assertEquals(result, 17);
    }

    @Test
    public void findCorrespondingClosingBracketReturnsCorrectMatchingClosingBracket4() {
        /* in this test, there there is another set of opening brackets after the closure of the first set
           so to find the matching index, we need to start the search for the closure after the index of this
           opening bracket (at index =
           which is index 7
         */
        String braces = "{(){[]}(){[({})]}}()";
        String matchingOpeningCharacter = braces.substring(1, 2);
        String matchingClosingCharacter = BracesCounter.BRACKETS_MAP.get(matchingOpeningCharacter);

        int result = BracesCounter.findCorrespondingClosingBracket(braces,
                matchingOpeningCharacter,
                matchingClosingCharacter,
                19);

        assertEquals(result, 19);
    }

    @Test
    public void countBracesInvalidAsUnevenBraces() {
        String braces = "({)";
        assertEquals(BracesCounter.count(braces), BracesCounter.INVALID);
    }

    @Test
    public void countBracesValidEquals1() {
        String braces = "()";
        assertEquals(BracesCounter.count(braces), 1);
    }

    @Test
    public void countBracesRobustTest() {
        String braces = "({[][]}())[()]({[]})";
        assertEquals(BracesCounter.count(braces), 10);
    }
}
