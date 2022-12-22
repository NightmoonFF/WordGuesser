import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SecretWordController {

    private ArrayList<String> secretWords = new ArrayList<>();

    private void insertWords() {
        secretWords.add("HEJ");
        secretWords.add("GERMANY");
        secretWords.add("NIGHTSHADE");
        secretWords.add("RUDIMENTARY");
        secretWords.add("CULTURAL");
        secretWords.add("SCIENTOLOGY");
        secretWords.add("BANANA");
        secretWords.add("HORSE");
        secretWords.add("JESUS");
        secretWords.add("ORANGE");
        secretWords.add("KETCHUP");
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