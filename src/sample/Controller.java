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

import java.text.DecimalFormat;

public class Controller{
    double lastNumber;
    double currentNumber;
    double result;
    boolean deletedMainLabels = false;
    FlowPane calculateFlowPane;
    String lastOperation;
    DecimalFormat df;

    @FXML Pane mainPane;
    @FXML FlowPane flowPaneForStandardButtons;
    @FXML Label mainDownLabel;
    @FXML Label mainUpLabel;
    @FXML Label menuLabel;
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
        menuLabel.setText(menuItem1.getText());
        mainPane.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY, Insets.EMPTY)));
        mainDownLabel.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)));
        mainUpLabel.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)));
        menuButton.setStyle("-fx-border-color: black;");
        df = new DecimalFormat(); 
        df.setMinimumFractionDigits(0);
        df.setMaximumFractionDigits(10);
        menuButtons();
        setButtons("Standard");
        actionForStandardButtons();
        for (Button butt : ButtonField.getListOfStandardButtons()){
            flowPaneForStandardButtons.getChildren().add(butt);
        }
    }

    public void menuButtons(){
        menuItem1.setOnAction(event -> {
            menuLabel.setText(menuItem1.getText());
            setButtons("Standard");
            actionForStandardButtons();
        });
        menuItem2.setOnAction(event -> {
            menuLabel.setText(menuItem2.getText());
            setButtons("CalculateDate");
        });
    }

    public void setButtons(String whatButtonFieldShowed){
        switch (whatButtonFieldShowed){
            case "Standard":
                if (deletedMainLabels){
                    mainPane.getChildren().addAll(mainUpLabel,mainDownLabel,flowPaneForStandardButtons);
                    deletedMainLabels = false;
                }
                mainPane.getChildren().remove(calculateFlowPane);
                ButtonField.getStandardButtonField();
                actionForStandardButtons();
                System.out.println(mainPane.getChildren().toString());
                break;
            case "CalculateDate":
                calculateFlowPane = ButtonField.getCalculateDate();
                mainPane.getChildren().removeAll(flowPaneForStandardButtons,mainUpLabel,mainDownLabel);
                deletedMainLabels = true;
                mainPane.getChildren().add(calculateFlowPane);
                System.out.println(mainPane.getChildren().toString());
                break;
        }
    }

    public void actionForStandardButtons(){
        for (Button butt : ButtonField.getListOfStandardButtons()){
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
                        if (mainUpLabel.getText().equals("") && mainDownLabel.getText().equals("")){
                            errorLabel.setText("Brak liczby !");
                            return;

                        }else if (mainUpLabel.getText().equals("") && !mainDownLabel.getText().equals("")){
                            lastNumber = Double.valueOf(mainDownLabel.getText());
                            lastOperation = butt.getText();
                            mainUpLabel.setText(lastNumber+lastOperation);
                            mainDownLabel.setText("");
                            errorLabel.setText("");

                        }else if (!mainUpLabel.getText().equals("") && !mainDownLabel.getText().equals("")){
                            currentNumber = Double.valueOf(mainDownLabel.getText());
                            if (!lastOperation.equals(butt.getText())){
                                lastNumber = operationForNumber(lastOperation);
                                mainUpLabel.setText(lastNumber+butt.getText());
                                lastOperation = butt.getText();
                                mainDownLabel.setText("");
                                errorLabel.setText("");
                            }else{
                                lastOperation = butt.getText();
                                lastNumber = operationForNumber(lastOperation);
                                mainUpLabel.setText(lastNumber+butt.getText());
                                mainDownLabel.setText("");
                            }

                        }else if (!mainUpLabel.getText().equals("") && mainDownLabel.getText().equals("")){
                            mainUpLabel.setText(lastNumber+butt.getText());
                            lastOperation = butt.getText();
                        }
                    });
                    break;
                case "=":
                    butt.setOnAction(event -> {
                        if (!mainUpLabel.getText().equals("") && mainDownLabel.getText().equals("")){
                            mainDownLabel.setText(lastNumber+"");
                            mainUpLabel.setText("");

                        }else if (!mainUpLabel.getText().equals("") && !mainDownLabel.getText().equals("")){
                            currentNumber = Double.valueOf(mainDownLabel.getText());
                            mainDownLabel.setText(operationForNumber(lastOperation)+"");
                            mainUpLabel.setText("");
                        }
                    });
                    break;
                case ".":
                    butt.setOnAction(event -> {
                        if (mainDownLabel.getText().equals("")) mainDownLabel.setText("0");
                        if (!mainDownLabel.getText().contains(".")) {
                            mainDownLabel.setText(mainDownLabel.getText() + ".");
                        }
                    });
            }
        }
    }

    public double operationForNumber(String whatOper){
        if (whatOper.equals("/")){
            if (currentNumber == 0){
                errorLabel.setText("Nie dzieli się przez 0 !");
                result = lastNumber;
            }else{
                result = lastNumber / currentNumber;
            }
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
