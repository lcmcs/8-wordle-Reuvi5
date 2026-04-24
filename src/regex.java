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

    public static List<String> wordleMatches (List<List<WordleResponse>> list) {
        return null;
    }
    class WordleResponse {
        char c;
        int index;
        LetterResponse resp;
    }

    public static void main (String[]args){
        //System.out.println(properName("Ba"));
        //System.out.println(integer("+5."));
//        System.out.println(ancestor("mother"));
//        System.out.println(ancestor("grandmother"));
//        System.out.println(ancestor("great-great-grandmother"));
//        System.out.println(ancestor("great-great mother"));
        System.out.println(palindrome("aabccCcbaA"));
        System.out.println(palindrome("aabccCcbaA"));




    }
}
