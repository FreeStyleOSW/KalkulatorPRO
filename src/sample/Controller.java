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
    private double lastNumber,currentNumber,result;
    private Label mainUpLabel;
    private Label mainDownLabel;
    private FlowPane calculateFlowPane;
    private String lastOperation;

    @FXML Pane mainPane;
    @FXML FlowPane flowPaneForStandardButtons;
    @FXML Label menuLabel;
    @FXML Label errorLabel;
    @FXML MenuButton menuButton;
    @FXML MenuItem menuItem1;
    @FXML MenuItem menuItem2;

    @FXML public void initialize(){
        ButtonField buttonField = new ButtonField();

        Image playI = new Image("/menu/res/menu1.png");
        ImageView imageView = new ImageView(playI);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        menuButtons();
        menuLabel.setText(menuItem1.getText());
        menuButton.setGraphic(imageView);
        menuButton.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY)));
        menuButton.setStyle("-fx-border-color: black;");
        mainPane.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY, Insets.EMPTY)));

        mainUpLabel = buttonField.addMainUpLabel();
        mainDownLabel = buttonField.addMainDownLabel();
        flowPaneForStandardButtons.getChildren().add(mainUpLabel);
        flowPaneForStandardButtons.getChildren().add(mainDownLabel);
        buttonField.getStandardButtonField();
        actionForStandardButtons();
        for (Button butt : ButtonField.getListOfStandardButtons()){
            flowPaneForStandardButtons.getChildren().add(butt);
        }

        calculateFlowPane = buttonField.getCalculateFlowPane();
        mainPane.getChildren().add(calculateFlowPane);
        buttonField.actionForCalculateDate();
        calculateFlowPane.setVisible(false);
    }

    public void menuButtons(){
        menuItem1.setOnAction(event -> {
            menuLabel.setText(menuItem1.getText());
            mainUpLabel.setText("");
            mainDownLabel.setText("");
            setButtons("Standard");
        });
        menuItem2.setOnAction(event -> {
            menuLabel.setText(menuItem2.getText());
            setButtons("CalculateDate");
        });
    }

    public void setButtons(String whatButtonFieldShowed){
        switch (whatButtonFieldShowed){
            case "Standard":
                flowPaneForStandardButtons.setVisible(true);
                calculateFlowPane.setVisible(false);
                break;
            case "CalculateDate":
                flowPaneForStandardButtons.setVisible(false);
                calculateFlowPane.setVisible(true);
                break;
        }
    }

    public void actionForStandardButtons(){
        for (Button butt : ButtonField.getListOfStandardButtons() ){
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
