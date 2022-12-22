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

import java.util.concurrent.LinkedBlockingDeque;

public class GUI extends Application {

    GameController gameController = new GameController();
    int wordLength = gameController.secretWord.length();
    TextField txfInput;
    Label lblPrompt, lblDots, lblUsed;
    Button btnInput;

    @Override
    public void start(Stage stage){
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane);
        pane.setPrefSize(400, 400);
        stage.setScene(scene);
        stage.show();
        pane.setPadding(new Insets(50, 50, 50, 50));
        initGUI(pane);
    }

    private void initGUI(BorderPane pane){
        //TOP
        VBox vbxTop = new VBox();
        pane.setTop(vbxTop);
        vbxTop.setAlignment(Pos.CENTER);
        Label lblTest = new Label("WORD GUESSER");
        vbxTop.getChildren().add(lblTest);
        lblTest.setFont(Font.font("", FontWeight.BOLD, 30));

        Label lblInfo = new Label();
        lblInfo.setText("Welcome to word guesser!\nThe length of your word is: " + wordLength + "\n");
        vbxTop.getChildren().add(lblInfo);

        //CENTER
        VBox vbxCenter = new VBox();
        pane.setCenter(vbxCenter);
        vbxCenter.setAlignment(Pos.CENTER);
        lblPrompt = new Label("Enter your guess (One letter, or a full word): ");
        vbxCenter.getChildren().add(lblPrompt);
        lblPrompt.setAlignment(Pos.BOTTOM_CENTER);
        Label lblBlank = new Label();
        vbxCenter.getChildren().add(lblBlank);
        lblDots = new Label();
        vbxCenter.getChildren().add(lblDots);
        String dots = new String();
        for (int i = 0; i < wordLength; i++){
            dots = dots+"*";
        }
        lblDots.setText(dots);
        lblDots.setFont(Font.font("", FontWeight.BOLD, 20));

        //BOTTOM
        VBox vbxBottom = new VBox();
        pane.setBottom(vbxBottom);

        HBox hbxInput = new HBox();
        vbxBottom.getChildren().add(hbxInput);
        hbxInput.setAlignment(Pos.BOTTOM_CENTER);
        txfInput = new TextField();
        hbxInput.getChildren().add(txfInput);
        txfInput.setAlignment(Pos.BOTTOM_CENTER);
        txfInput.setOnKeyPressed(event -> {if(event.getCode() == KeyCode.ENTER){btnInputAction();}});

        btnInput = new Button("OK");
        hbxInput.getChildren().add(btnInput);
        btnInput.setOnAction(event -> btnInputAction());

        lblUsed = new Label();
        vbxBottom.getChildren().add(lblUsed);
    }

    private void btnInputAction(){

        gameController.scan(txfInput.getText());
        lblDots.setText(gameController.printDots());
        txfInput.clear();

        if(gameController.haveWon()){
            lblPrompt.setText("YOU WIN THE GAME\n         (" + gameController.guessAmount + " Guesses)");
            lblPrompt.setTextFill(Color.GREEN);
            lblPrompt.setFont(Font.font("", FontWeight.BOLD, 20));
            btnInput.setDisable(true);
            txfInput.setDisable(true);
            lblDots.setText(gameController.secretWord);

        }
        else{
            //gameController.updateGuessString();
            lblUsed.setText(gameController.guessesString);
            gameController.printDots();

        }

    }
}
