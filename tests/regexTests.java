import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class regexTest {
    @Test
    void properName() {
        assertTrue(regex.properName("Bob"));
        assertFalse(regex.properName("bo"));
        assertFalse(regex.properName("B"));
    }

    @Test
    void integer() {
        assertTrue(regex.integer("613"));
        assertTrue(regex.integer("-61.3"));
        assertTrue(regex.integer("0.06130"));
        assertFalse(regex.integer("0613"));
        assertFalse(regex.integer("+613."));
    }

    @Test
    void ancestor() {
        assertTrue(regex.ancestor("mother"));
        assertTrue(regex.ancestor("father"));
        assertTrue(regex.ancestor("grandmother"));
        assertTrue(regex.ancestor("great-great-great-great-great-grandfather"));
        assertFalse(regex.ancestor("great-great mother"));
    }

    @Test
    void testPalindrome() {
        assertTrue(regex.palindrome("aabccCcbaA"));
        assertFalse(regex.palindrome("abccba"));
    }

    @Test
    void wordleAllWrongLetter() throws FileNotFoundException {
        List<List<WordleResponse>> userInput = new ArrayList<>();
        List<WordleResponse> word = new ArrayList<>();

        word.add(new WordleResponse('t',0, LetterResponse.WRONG_LETTER));
        word.add(new WordleResponse('r',1, LetterResponse.WRONG_LETTER));
        word.add(new WordleResponse('a',2, LetterResponse.WRONG_LETTER));
        word.add(new WordleResponse('i',3, LetterResponse.WRONG_LETTER));
        word.add(new WordleResponse('n',4, LetterResponse.WRONG_LETTER));

        userInput.add(word);

        List<String> result = regex.wordleMatches(userInput);

        for (String s:result) {
            assertFalse(s.contains("t"));
            assertFalse(s.contains("r"));
            assertFalse(s.contains("a"));
            assertFalse(s.contains("i"));
            assertFalse(s.contains("n"));
        }
    }

    @Test
    void testWordleAllWrongLocation() throws FileNotFoundException {
        List<List<WordleResponse>> userInput = new ArrayList<>();
        List<WordleResponse> word = new ArrayList<>();

        word.add(new WordleResponse('g',0, LetterResponse.WRONG_LOCATION));
        word.add(new WordleResponse('i',1, LetterResponse.WRONG_LOCATION));
        word.add(new WordleResponse('a',2, LetterResponse.WRONG_LOCATION));
        word.add(new WordleResponse('n',3, LetterResponse.WRONG_LOCATION));
        word.add(new WordleResponse('t',4, LetterResponse.WRONG_LOCATION));

        userInput.add(word);

        List<String> result = regex.wordleMatches(userInput);

        for (String s:result) {
            assertTrue(s.contains("g"));
            assertTrue(s.contains("i"));
            assertTrue(s.contains("a"));
            assertTrue(s.contains("n"));
            assertTrue(s.contains("t"));

            assertNotEquals('g', s.charAt(0));
            assertNotEquals('i', s.charAt(1));
            assertNotEquals('a', s.charAt(2));
            assertNotEquals('n', s.charAt(3));
            assertNotEquals('t', s.charAt(4));
        }
    }
    @Test
    void testWordleAllCorrectLocation() throws FileNotFoundException {
        List<List<WordleResponse>> userInput = new ArrayList<>();
        List<WordleResponse> word = new ArrayList<>();

        word.add(new WordleResponse('g',0, LetterResponse.CORRECT_LOCATION));
        word.add(new WordleResponse('i',1, LetterResponse.CORRECT_LOCATION));
        word.add(new WordleResponse('a',2, LetterResponse.CORRECT_LOCATION));
        word.add(new WordleResponse('n',3, LetterResponse.CORRECT_LOCATION));
        word.add(new WordleResponse('t',4, LetterResponse.CORRECT_LOCATION));

        userInput.add(word);

        List<String> result = regex.wordleMatches(userInput);
        assertEquals("giant", result.get(0));
    }
}