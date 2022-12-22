import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SecretWordController {

    private ArrayList<String> secretWords = new ArrayList<>();

    private void insertWords() {
        secretWords.add("germany");
        secretWords.add("nightshade");
        secretWords.add("rudimentary");
        secretWords.add("cultural");
        secretWords.add("scientology");
        secretWords.add("banana");
        secretWords.add("horse");
        secretWords.add("jesus");
        secretWords.add("orange");
        secretWords.add("ketchup");
        //TODO: seperate into word types, and tell user which category
    }

    @Override
    public String toString(){
        if(secretWords.size() == 0){
            insertWords();
        }
        Random rn = new Random();
        String randomword = secretWords.get(rn.nextInt(secretWords.size()));
        return randomword;
    }

}