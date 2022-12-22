import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

public class GUI extends Application {

    GameController gameController = new GameController();
    SecretWordController secretWordController = new SecretWordController();
    Label lblPrompt, lblDots, lblInfo;
    VBox vbxBottom;
    Button[] btnArray = new Button[26];
    HBox hbxAlphabet1, hbxAlphabet2, hbxAlphabet3, hbxPlayAgain;
    Button btnPlayAgain;

    @Override
    public void start(Stage stage){
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane);
        pane.setPrefSize(400, 400);
        stage.setScene(scene);
        stage.show();
        pane.setPadding(new Insets(50, 50, 50, 50));
        initGUI(pane);
        initGUIAlphabet();
        lblDots.requestFocus();
    }

    private void initGUI(BorderPane pane){
        //TOP
        VBox vbxTop = new VBox();
        pane.setTop(vbxTop);
        vbxTop.setAlignment(Pos.CENTER);
        Label lblTest = new Label("WORD GUESSER");
        vbxTop.getChildren().add(lblTest);
        lblTest.setFont(Font.font("", FontWeight.BOLD, 30));

        lblInfo = new Label();
        lblInfo.setText("Welcome to word guesser!\nThe length of your word is: " + gameController.secretWord.length() + "\n");
        vbxTop.getChildren().add(lblInfo);

        //CENTER
        VBox vbxCenter = new VBox();
        pane.setCenter(vbxCenter);
        vbxCenter.setAlignment(Pos.CENTER);
        lblPrompt = new Label("");
        vbxCenter.getChildren().add(lblPrompt);
        lblPrompt.setAlignment(Pos.BOTTOM_CENTER);
        Label lblBlank = new Label();
        vbxCenter.getChildren().add(lblBlank);
        lblDots = new Label();
        vbxCenter.getChildren().add(lblDots);
        String dots = new String();
        for (int i = 0; i < gameController.secretWord.length(); i++){
            dots = dots+"*";
        }
        lblDots.setText(dots);
        lblDots.setFont(Font.font("", FontWeight.BOLD, 20));

        //BOTTOM
        vbxBottom = new VBox();
        pane.setBottom(vbxBottom);

        hbxPlayAgain = new HBox();
        hbxPlayAgain.setAlignment(Pos.BOTTOM_RIGHT);
        btnPlayAgain = new Button("Play Again");
        btnPlayAgain.setAlignment(Pos.BOTTOM_RIGHT);
        btnPlayAgain.setOnAction(event -> playAgainAction());
        hbxPlayAgain.getChildren().add(btnPlayAgain);

    }

    private void initGUIAlphabet(){
        hbxAlphabet1 = new HBox();
        hbxAlphabet2 = new HBox();
        hbxAlphabet3 = new HBox();
        vbxBottom.getChildren().add(hbxAlphabet1);
        vbxBottom.getChildren().add(hbxAlphabet2);
        vbxBottom.getChildren().add(hbxAlphabet3);
        char cha;
        int count = 0;
        for(cha = 'A'; cha <= 'Z'; ++cha){
            btnArray[count] = new Button(String.valueOf(cha));
            char finalCha = cha;
            int finalCount = count;
            btnArray[count].setOnAction(event -> btnInputAction(finalCha, finalCount));
            btnArray[count].setMinSize(30, 30);
            count++;
    }
        for(int i = 0; i < 10; i++){

            hbxAlphabet1.getChildren().add(btnArray[i]);
        }
        for(int j = 10; j < 20; j++){
            hbxAlphabet2.getChildren().add(btnArray[j]);
        }
        for(int k = 20; k < 26; k++){
            hbxAlphabet3.getChildren().add(btnArray[k]);
        }
    }

    private void btnInputAction(char letter, int btnIndex){
        lblDots.requestFocus();
        gameController.scan(letter);
        lblDots.setText(gameController.printDots());
        btnArray[btnIndex].setDisable(true);

        if(gameController.haveWon()){
            lblPrompt.setText("YOU WIN THE GAME\n  (" + (gameController.guesses.size() - gameController.correctGuessAmount) + " Wrong Guesses)");
            lblPrompt.setTextFill(Color.GREEN);
            lblPrompt.setFont(Font.font("", FontWeight.BOLD, 20));
            lblDots.setText(gameController.secretWord);
            vbxBottom.getChildren().add(hbxPlayAgain);
            disableButtons(true);
        }
        else{
            gameController.printDots();
        }
    }

    private void disableButtons(boolean disabled){
        for(Button btn : btnArray){
            btn.setDisable(disabled);
        }

    }

    private void playAgainAction(){
        disableButtons(false);
        vbxBottom.getChildren().remove(hbxPlayAgain);
        lblPrompt.setText("");
        gameController.guesses.clear();
        gameController.correctGuessAmount = 0;
        gameController.secretWord = secretWordController.toString();
        gameController.tmpDots = "";
        lblDots.setText(gameController.printDots());
        lblInfo.setText("Welcome to word guesser!\nThe length of your word is: " + gameController.secretWord.length() + "\n");

    }
}
