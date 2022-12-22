import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameController {

    Character answer;
    SecretWordController secretWordController = new SecretWordController();
    String secretWord = secretWordController.toString();;
    Scanner scanner = new Scanner(System.in);
    String scanned;
    ArrayList<Character> guesses = new ArrayList<>();
    String guessesString = "";
    int guessAmount;

    public void scan(String input){
        guessAmount++;
        scanned = input;
        if(scanned.length() == 1){
            if(!guesses.contains(scanned.charAt(0))){
                guesses.add(scanned.charAt(0));
                guessesString = guessesString + scanned.charAt(0) + ", ";
            }
        }
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
        return dots;
    }
    public boolean haveWon(){
        if(scanned.equals(secretWord)){
            return true;
        }
        else{
            return false;
        }
    }

}
