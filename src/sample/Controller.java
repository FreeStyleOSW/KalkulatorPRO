package sample;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import menu.ButtonField;

public class Controller{
    double lastNumber;
    double currentNumber;
    double result;
    String lastOperation;

    @FXML Pane mainPane;
    @FXML FlowPane flowPane;
    @FXML Label mainDownLabel;
    @FXML Label mainUpLabel;
    @FXML Label buttonLabel;
    @FXML Label errorLabel;
    @FXML MenuButton menuButton;
    @FXML MenuItem menuItem1;
    @FXML MenuItem menuItem2;

    @FXML public void initialize(){
        Image playI = new Image("/menu/res/menu1.png");
        ImageView imageView = new ImageView(playI);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        menuButton.setGraphic(imageView);
        menuButton.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY)));
        buttonLabel.setText(menuItem1.getText());
        mainPane.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY, Insets.EMPTY)));
        mainDownLabel.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)));
        mainUpLabel.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)));
        menuButton.setStyle("-fx-border-color: black;");
        menuButtons();
        setButtons();
        actionForButtons();

    }

    public void menuButtons(){
        menuItem1.setOnAction(event -> {
            buttonLabel.setText(menuItem1.getText());
            flowPane.getChildren().remove(0,flowPane.getChildren().size());
            ButtonField.setMenuNumber(0);
            setButtons();
            actionForButtons();
        });
        menuItem2.setOnAction(event -> {
            buttonLabel.setText(menuItem2.getText());
            flowPane.getChildren().remove(0,flowPane.getChildren().size());
            ButtonField.setMenuNumber(1);
            setButtons();
            actionForButtons();
        });
    }

    public void setButtons(){
        // Tutaj można zmienić ButtonFielda, narazie jest tylko Standardowe ułożenie przycisków
        ButtonField.setStandardButtonField();
        for (Button butt : ButtonField.getListOfButtons()){
            flowPane.getChildren().add(butt);
        }
    }

    public void actionForButtons(){
        for (Button butt : ButtonField.getListOfButtons()){
            String WhichButtonClicked = butt.getText();
            if (butt.getText().matches("1|2|3|4|5|6|7|8|9|0")) WhichButtonClicked = "numbers";
            if (butt.getText().matches("/|X|-")||butt.getText().equals("+")) WhichButtonClicked = "operations";
            switch (WhichButtonClicked){
                case "numbers":
                    butt.setOnAction(event -> {
                        mainDownLabel.setText(mainDownLabel.getText()+butt.getText());
                        errorLabel.setText("");
                    });
                    break;
                case "CE":
                    butt.setOnAction(event -> {
                        mainDownLabel.setText("");
                        mainUpLabel.setText("");
                        errorLabel.setText("");
                    });
                    break;
                case "C":
                    butt.setOnAction(event -> {
                        if (!mainDownLabel.getText().equals("")){
                            mainDownLabel.setText(mainDownLabel.getText().substring(0, mainDownLabel.getText().length()-1));
                            errorLabel.setText("");
                        }
                    });
                    break;
                case "operations":
                    butt.setOnAction(event -> {
                        if (mainDownLabel.getText().equals("") && mainDownLabel.getText().equals("")){
                            // Górne pole   -> EMPTY
                            // Dolne pole   -> EMPTY
                            errorLabel.setText("Brak liczby !");
                            return;
                        }else if (mainUpLabel.getText().equals("")){
                            // Górne pole -> EMPTY
                            // Dolne pole -> FULL
                            lastNumber = Double.valueOf(mainDownLabel.getText());
                            lastOperation = butt.getText();
                            mainUpLabel.setText(lastNumber+butt.getText());
                            mainDownLabel.setText("");

                        }else if (!mainUpLabel.getText().equals("") && !mainDownLabel.getText().equals("")){
                            //Góne pole  -> FULL
                            //Dolne pole -> FULL
                            currentNumber = Double.valueOf(mainDownLabel.getText());
                            if (!lastOperation.equals(butt.getText())){
                                mainUpLabel.setText(operationForNumber(lastOperation)+butt.getText());
                                lastOperation =butt.getText();
                                lastNumber = operationForNumber(lastOperation);
                                mainDownLabel.setText("");


                            }else{
                                mainUpLabel.setText(operationForNumber(lastOperation)+butt.getText());
                                lastOperation = butt.getText();
                                lastNumber = operationForNumber(lastOperation);
                                mainDownLabel.setText("");

                            }
                        }else if (!mainUpLabel.getText().equals("") && mainDownLabel.getText().equals("")){
                            // Górne pole -> FULL
                            // Dolne pole -> EMPTY
                            mainUpLabel.setText(lastNumber+butt.getText());
                        }
                    });
                    break;
                case "=":

            }
        }
    }

    public double operationForNumber(String whatOper){
        System.out.println(whatOper);
        if (whatOper.equals("/")){
            result = lastNumber / currentNumber;
        }
        if (whatOper.equals("X")){
            result = lastNumber * currentNumber;
        }
        if (whatOper.equals("-")){
            result = lastNumber - currentNumber;
        }
        if (whatOper.equals("+")){
            result = lastNumber + currentNumber;
        }
        return result;
    }
}
