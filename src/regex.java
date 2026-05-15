import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regex {
    public static boolean properName(String s) {
        Pattern p = Pattern.compile("[A-Z]{1}[a-z]+");
        Matcher m = p.matcher(s);

        return m.matches();
    }

    public static boolean integer(String s) {
        Pattern p = Pattern.compile("[-\\+]?(0(\\.\\d+)?|[1-9]+\\d*(\\.\\d+)?)");
        Matcher m = p.matcher(s);

        return m.matches();
    }

    public static boolean ancestor(String s) {
        //i'm assuming it's just for mother and father, not uncle and aunt
        Pattern p = Pattern.compile("((great-)*grand|grand)?(mother|father)$");
        Matcher m = p.matcher(s);

        return m.matches();
    }

    public static boolean palindrome(String s) {
        Pattern p = Pattern.compile("([a-z])([a-z])([a-z])([a-z])([a-z])\\5\\4\\3\\2\\1", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(s);

        return m.matches();
    }

    public static List<String> wordleMatches(List<List<WordleResponse>> list) throws FileNotFoundException {
        StringBuilder[] positions = new StringBuilder[5];

        //this will contain a bunch of "lookaheads"
        StringBuilder contains = new StringBuilder("^");

        for (int i = 0; i < 5; i++) {

            //per position either there will be either a list of letters that can't be there or the correct letter
            StringBuilder incorrectLetters = new StringBuilder();
            Character correctLetter = null;

            for (int j = 0; j < list.size(); j++) {
                positions[i] = new StringBuilder("");
                WordleResponse temp = list.get(j).get(i);
                if (temp.getResp().equals(LetterResponse.CORRECT_LOCATION)) {
                    correctLetter = temp.getChar();
                } else if (temp.getResp().equals(LetterResponse.WRONG_LOCATION)) {
                    contains.append("(?=.*" + temp.getChar() + ")");
                    incorrectLetters.append(temp.getChar());
                } else {
                    contains.append("(?!.*" + temp.getChar() + ")");
                    incorrectLetters.append(temp.getChar());
                }

                if (correctLetter != null){
                    positions[i].append(correctLetter);
                } else {
                    positions[i].append("[^" + incorrectLetters + "]");
                }
            }
        }
        StringBuilder correctLocation = new StringBuilder("^" + positions[0] + positions[1] + positions[2] + positions[3] + positions[4]);

        contains.append(".*$");
        System.out.println(correctLocation);
        System.out.println(contains);


        Pattern p1 = Pattern.compile(String.valueOf(correctLocation));
        Pattern p2 = Pattern.compile(String.valueOf(contains));

        List<String> matches = getStringList(p1, p2);
        return matches;
    }

    private static List<String> getStringList(Pattern p1, Pattern p2) {
        List<String> matches = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("valid-wordle-words.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher m1 = p1.matcher(line);
                Matcher m2 = p2.matcher(line);
                if (m1.matches() && m2.matches()) {
                    matches.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return matches;
    }


    public static void main(String[] args) throws FileNotFoundException {
        List<List<WordleResponse>> multipleWords = new ArrayList<>();

        List word1 = new ArrayList<WordleResponse>(5);
        List word2 = new ArrayList<WordleResponse>(5);
        List word3 = new ArrayList<WordleResponse>(5);

        word1.add(new WordleResponse('a', 0, LetterResponse.CORRECT_LOCATION));
        word1.add(new WordleResponse('e', 1, LetterResponse.WRONG_LOCATION));
        word1.add(new WordleResponse('r', 2, LetterResponse.WRONG_LOCATION));
        word1.add(new WordleResponse('s', 3, LetterResponse.WRONG_LETTER));
        word1.add(new WordleResponse('t', 4, LetterResponse.WRONG_LETTER));

        word2.add(new WordleResponse('l', 0, LetterResponse.WRONG_LOCATION));
        word2.add(new WordleResponse('e', 1, LetterResponse.WRONG_LOCATION));
        word2.add(new WordleResponse('o', 2, LetterResponse.WRONG_LETTER));
        word2.add(new WordleResponse('u', 3, LetterResponse.WRONG_LETTER));
        word2.add(new WordleResponse('d', 4, LetterResponse.WRONG_LETTER));

        word3.add(new WordleResponse('p', 0, LetterResponse.WRONG_LETTER));
        word3.add(new WordleResponse('c', 1, LetterResponse.WRONG_LETTER));
        word3.add(new WordleResponse('m', 2, LetterResponse.WRONG_LETTER));
        word3.add(new WordleResponse('h', 3, LetterResponse.WRONG_LETTER));
        word3.add(new WordleResponse('g', 4, LetterResponse.WRONG_LETTER));

        multipleWords.add(word3);
        multipleWords.add(word1);
        multipleWords.add(word2);
        System.out.println(wordleMatches(multipleWords));
    }
}
