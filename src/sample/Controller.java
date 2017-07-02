package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Controller{
    int latestNumber;
    boolean buttonsShowed = false;
    char charButtons[] = {
            '7','8','9','/',
            '4','5','6','X',
            '1','2','3','-',
            '.','0','=','+'
    };
    List<Button> StandardButtons = new ArrayList<>();
    public void setupButtons(){
        for (int i = 0; i < charButtons.length; i++) {
            Button tempButt = new Button(charButtons[i]+"");
            tempButt.setPrefSize(50,50);
            StandardButtons.add(tempButt);
        }
    }

    @FXML
    Pane mainPane;

    @FXML
    FlowPane flowPane;

    @FXML
    public void initialize(){
        addButtons();
        mainPane.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY, Insets.EMPTY)));
        mainLabel.setStyle("-fx-border-color: black;");
        mainLabel.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE,CornerRadii.EMPTY, Insets.EMPTY)));
    }
    @FXML
    Label mainLabel;

    public void addButtons(){
        setupButtons();
        if (buttonsShowed) return;
        buttonsShowed = true;
        for (Button butt : StandardButtons){
            flowPane.getChildren().add(butt);
            butt.setOnAction(event -> {
                mainLabel.setText(mainLabel.getText()+butt.getText());
            });
        }
    }

    public void printButton(ActionEvent ae){

    }
}
