public class WordleResponse {
    char c;
    int index;
    LetterResponse resp;

    public WordleResponse(char ch, int ind, LetterResponse response){
        c = ch;
        index = ind;
        resp = response;
    }

    public LetterResponse getResp(){
        return resp;
    }

    public char getChar(){
        return c;
    }
}