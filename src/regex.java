import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regex {
    public static boolean properName (String s){
        Pattern p = Pattern.compile("[A-Z]{1}[a-z]+");
        Matcher m = p.matcher(s);

        return m.matches();
    }

    public static boolean integer (String s){
        Pattern p = Pattern.compile("[-\\+]?(0(\\.\\d+)?|[1-9]+\\d*(\\.\\d+)?)");
        Matcher m = p.matcher(s);

        return m.matches();
    }
    public static boolean ancestor (String s){
        //i'm assuming it's just for mother and father, not uncle and aunt
        Pattern p = Pattern.compile("((great-)*grand|grand)?(mother|father)$");
        Matcher m = p.matcher(s);

        return m.matches();
    }
    public static boolean palindrome (String s){
        Pattern p = Pattern.compile("([a-z])([a-z])([a-z])([a-z])([a-z])\\5\\4\\3\\2\\1",Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(s);

        return m.matches();
    }

    public static List<String> wordleMatches (List<List<WordleResponse>> list) throws FileNotFoundException {

        StringBuilder correctLocation = new StringBuilder("^");
        StringBuilder contains = new StringBuilder("^");

        for (int i = 0; i < 5; i++) {
            StringBuilder correctLocationTemp = new StringBuilder("");
            WordleResponse temp = list.get(0).get(i);
            if (temp.getResp().equals(LetterResponse.CORRECT_LOCATION)){
                correctLocationTemp.append(temp.getChar());
            }
            else if (temp.getResp().equals(LetterResponse.WRONG_LOCATION)){
                contains.append("(?=.*" + temp.getChar() + ")");
                correctLocation.append( "[^" + temp.getChar() + "]");
            }
            else {
                contains.append("(?!.*" + temp.getChar() + ")");
                correctLocationTemp.append(".");
            }
            correctLocation.append(correctLocationTemp);
        }
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
                    if (m1.matches() && m2.matches()){
                        matches.add(line);
                    }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return matches;
    }


    public static void main (String[]args) throws FileNotFoundException {
//        System.out.println(properName("Ba"));
//        System.out.println(integer("+5."));
//        System.out.println(ancestor("mother"));
//        System.out.println(ancestor("grandmother"));
//        System.out.println(ancestor("great-great-grandmother"));
//        System.out.println(ancestor("great-great mother"));
//        System.out.println(palindrome("aabccCcbaA"));
//        System.out.println(palindrome("aabccCcbaA"));

        List<List<WordleResponse>> multipleWords = new ArrayList<>();

        List word1 = new ArrayList<WordleResponse>(5);
        List word2 = new ArrayList<WordleResponse>(5);
        List word3 = new ArrayList<WordleResponse>(5);
        List word4 = new ArrayList<WordleResponse>(5);

        word1.add(new WordleResponse('c',0, LetterResponse.CORRECT_LOCATION));
        word1.add(new WordleResponse('r',1, LetterResponse.WRONG_LOCATION));
        word1.add(new WordleResponse('a',2, LetterResponse.WRONG_LETTER));
        word1.add(new WordleResponse('n',3, LetterResponse.WRONG_LOCATION));
        word1.add(new WordleResponse('e',4, LetterResponse.WRONG_LETTER));

        word2.add(new WordleResponse('c',0, LetterResponse.WRONG_LOCATION));
        word2.add(new WordleResponse('o',1, LetterResponse.WRONG_LOCATION));
        word2.add(new WordleResponse('u',2, LetterResponse.WRONG_LOCATION));
        word2.add(new WordleResponse('g',3, LetterResponse.WRONG_LOCATION));
        word2.add(new WordleResponse('h',4, LetterResponse.WRONG_LOCATION));

        word3.add(new WordleResponse('t',0, LetterResponse.WRONG_LETTER));
        word3.add(new WordleResponse('r',1, LetterResponse.WRONG_LETTER));
        word3.add(new WordleResponse('a',2, LetterResponse.WRONG_LETTER));
        word3.add(new WordleResponse('i',3, LetterResponse.WRONG_LETTER));
        word3.add(new WordleResponse('n',4, LetterResponse.WRONG_LETTER));

//        WordleResponse letter6 = new WordleResponse('t',0);
//        word2.add(letter6);
//        WordleResponse letter7 = new WordleResponse('r',1);
//        word2.add(letter7);
//        WordleResponse letter8 = new WordleResponse('a',2);
//        word2.add(letter8);
//        WordleResponse letter9 = new WordleResponse('i',3);
//        word2.add(letter9);
//        WordleResponse letter10 = new WordleResponse('n',4);
//        word2.add(letter10);

        multipleWords.add(word3);
        multipleWords.add(word2);
        System.out.println(wordleMatches(multipleWords));

    }
}
