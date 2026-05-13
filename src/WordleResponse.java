public class WordleResponse {
    char c;
    int index;
    LetterResponse resp;

    public WordleResponse(char ch, int ind){
        c = ch;
        index = ind;
    }

    public void setResp(LetterResponse response){
        resp = response;
    }

    public char getChar(){
        return c;
    }
}