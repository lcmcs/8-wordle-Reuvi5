import java.io.*;
import java.util.ArrayList;
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
        String target = "hello";
        Pattern p = Pattern.compile(target);
        Matcher m = p.matcher(list.toString());
        List<String> matches = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("valid-wordle-words.txt"))) {
            String line;
            while ((line = br.readLine()) != null && m.matches()) {
                matches.add(line);
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

        List<List<WordleResponse>> multipleWords = new ArrayList<List<WordleResponse>>();

        List word1 = new ArrayList<WordleResponse>(5);
        List word2 = new ArrayList<WordleResponse>(5);
        List word3 = new ArrayList<WordleResponse>(5);
        List word4 = new ArrayList<WordleResponse>(5);

        WordleResponse letter1 = new WordleResponse('h',0,LetterResponse.CORRECT_LOCATION);
        word1.add(letter1);
        WordleResponse letter2 = new WordleResponse('e',1,LetterResponse.CORRECT_LOCATION);
        word1.add(letter2);
        WordleResponse letter3 = new WordleResponse('l',2,LetterResponse.CORRECT_LOCATION);
        word1.add(letter3);
        WordleResponse letter4 = new WordleResponse('l',3,LetterResponse.CORRECT_LOCATION);
        word1.add(letter4);
        WordleResponse letter5 = new WordleResponse('o',4,LetterResponse.CORRECT_LOCATION);
        word1.add(letter5);

        multipleWords.add(word1);
        System.out.println(wordleMatches(multipleWords));

    }
}
