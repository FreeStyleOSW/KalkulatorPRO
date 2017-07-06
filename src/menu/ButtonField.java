package menu;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 03.07.2017.
 */
 public class ButtonField {
    static List<Button> ListOfStandardButtons;
    static int menuNumber = 0;
    static FlowPane calculateFlowPane;

    public static List<Button> getListOfStandardButtons() {
        return ListOfStandardButtons;
    }

    public static void getStandardButtonField(){
        ListOfStandardButtons = new ArrayList<>();
        List<String[]> listStandardButtonFields = new ArrayList<>();
        String standardButtonsSet[] = {
                "7","8","9","/","CE",
                "4","5","6","X","C",
                "1","2","3","-","",
                ".","0","=","+",""
        };
        for (int i = 0; i < standardButtonsSet.length; i++) {
            Button tempButt = new Button(standardButtonsSet[i] + "");
            tempButt.setPrefSize(50, 50);
            ListOfStandardButtons.add(tempButt);
        }
    }

    public static FlowPane getCalculateDate(){
        calculateFlowPane = new FlowPane();
        calculateFlowPane.setId("calculateFlowPane");
        calculateFlowPane.setLayoutX(24);
        calculateFlowPane.setLayoutY(61);
        Label fromLabel = new Label("OD");
        Label toLabel = new Label("DO");
        DatePicker datePicker1 = new DatePicker();
        DatePicker datePicker2 = new DatePicker();
        toLabel.setFont(new Font("",20));
        toLabel.setPrefSize(250,30);
        fromLabel.setFont(new Font("",20));
        fromLabel.setPrefSize(250,30);
        datePicker1.setPrefSize(250,30);
        datePicker2.setPrefSize(250,30);
        calculateFlowPane.getChildren().addAll(fromLabel,datePicker1,toLabel,datePicker2);
        return calculateFlowPane;
    }
}
