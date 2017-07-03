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

import java.util.ArrayList;
import java.util.List;

public class Controller{
    int lastNumber;
    int menuNumber = 0;
    List<Button> ListOfButtons;



    @FXML
    Pane mainPane;

    @FXML
    FlowPane flowPane;

    @FXML
    Label mainLabel;

    @FXML
    Label buttonLabel;

    @FXML
    MenuButton menuButton;

    @FXML
    MenuItem menuItem1;

    @FXML
    MenuItem menuItem2;

    @FXML
    public void initialize(){
        Image playI = new Image("/menu/res/menu1.png");
        ImageView imageView = new ImageView(playI);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        menuButton.setGraphic(imageView);
        menuButton.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY)));
        buttonLabel.setText(menuItem1.getText());
        mainPane.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY, Insets.EMPTY)));
        mainLabel.setStyle("-fx-border-color: black;");
        menuButton.setStyle("-fx-border-color: black;");
        mainLabel.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE,CornerRadii.EMPTY, Insets.EMPTY)));
        menuButtons();
        setButtons();
        actionForButtons();

    }
    public void menuButtons(){
        menuItem1.setOnAction(event -> {
            buttonLabel.setText(menuItem1.getText());
            flowPane.getChildren().remove(0,flowPane.getChildren().size());
            menuNumber = 0;
            setButtons();
            actionForButtons();
        });
        menuItem2.setOnAction(event -> {
            buttonLabel.setText(menuItem2.getText());
            flowPane.getChildren().remove(0,flowPane.getChildren().size());
            menuNumber = 1;
            setButtons();
            actionForButtons();
        });
    }
    public void actionForButtons(){
        for (Button butt : ListOfButtons){
            String WhichButtonClicked = butt.getText();
            if (butt.getText().matches("1|2|3|4|5|6|7|8|9|0")){
                WhichButtonClicked = "numbers";
            }
            switch (WhichButtonClicked){
                case "numbers":
                    butt.setOnAction(event -> {
                        mainLabel.setText(mainLabel.getText()+butt.getText());
                    });
                    break;
                case "CE":
                    butt.setOnAction(event -> {
                        mainLabel.setText("");
                    });
                    break;
                case "C":
                    butt.setOnAction(event -> {
                        if (!mainLabel.getText().equals("")){
                            mainLabel.setText(mainLabel.getText().substring(0,mainLabel.getText().length()-1));
                        }
                    });
            }
        }
    }

    public void setButtons(){
        ListOfButtons = new ArrayList<>();
        String[] tempButtonList = ButtonField.getButtonField(menuNumber);
        for (int i = 0; i < tempButtonList.length; i++) {
            Button tempButt = new Button(tempButtonList[i]+"");
            tempButt.setPrefSize(50,50);
            ListOfButtons.add(tempButt);
        }
        for (Button butt : ListOfButtons){
            flowPane.getChildren().add(butt);
        }
    }
}
