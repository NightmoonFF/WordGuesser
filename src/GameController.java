import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameController {
    SecretWordController secretWordController = new SecretWordController();
    String secretWord = secretWordController.toString();
    ArrayList<Character> guesses = new ArrayList<>();

    int correctGuessAmount;
    String tmpDots;

    public void scan(char input){
        guesses.add(input);
    }


    public String printDots(){
        String dots = "";
        ArrayList<Character> chars = new ArrayList<>();
        chars.clear();
        for(int i = 0; i < guesses.size(); i++){
            if(guesses.get(i) != null){
                chars.add(guesses.get(i));
            }
        }

        for(int i = 0; i < secretWord.length(); i++) {

            if (chars.contains(secretWord.charAt(i))) {
                dots = dots + secretWord.charAt(i);

                }
            else{
                dots = dots + "*";
                }
        }

        if(!dots.equals(tmpDots)){
            correctGuessAmount++;
        }
        tmpDots = dots;
        return dots;
    }
    public boolean haveWon(){
        if(!printDots().contains("*")){
            return true;
        }
        else{
            return false;
        }
    }

}
