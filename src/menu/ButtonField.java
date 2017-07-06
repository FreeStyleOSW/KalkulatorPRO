package menu;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 03.07.2017.
 */
 public class ButtonField {
    static List<Button> ListOfStandardButtons;
    static FlowPane calculateFlowPane;
    int years1 = 0;
    int years2 = 0;
    int years = 0;
    int months1 = 0;
    int months2 = 0;
    int months = 0;
    int days1 = 0;
    int days2 = 0;
    int days = 0;
    boolean dataPickerSet1 = false;
    boolean dataPickerSet2 = false;
    DatePicker datePicker1;
    DatePicker datePicker2;
    Label showFullTime;
    Label showDayTime;

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

    public FlowPane getCalculateFlowPane(){
        calculateFlowPane = new FlowPane();
        calculateFlowPane.setId("calculateFlowPane");
        calculateFlowPane.setLayoutX(24);
        calculateFlowPane.setLayoutY(61);

        Label fromLabel = new Label("OD");
        fromLabel.setFont(new Font("",20));
        fromLabel.setPrefSize(250,30);

        Label toLabel = new Label("DO");
        toLabel.setFont(new Font("",20));
        toLabel.setPrefSize(250,30);

        showFullTime = new Label("");
        showFullTime.setFont(new Font("",15));
        showFullTime.setPrefSize(250,30);

        showDayTime = new Label("");
        showDayTime.setFont(new Font("",20));
        showDayTime.setPrefSize(250,30);

        Label diffLabel = new Label("Różnica");
        diffLabel.setFont(new Font("",20));
        diffLabel.setPrefSize(250,40);

        datePicker1 = new DatePicker();
        datePicker1.setPrefSize(250,30);


        datePicker2 = new DatePicker();
        datePicker2.setPrefSize(250,30);

        calculateFlowPane.getChildren().addAll(fromLabel,datePicker1,toLabel,datePicker2,diffLabel,showFullTime,showDayTime);
        return calculateFlowPane;
    }
    public void actionForCalculateDate(){
        datePicker1.setOnAction(event -> {
            dataPickerSet1 = true;
            years1 = datePicker1.getValue().getYear();
            months1 = datePicker1.getValue().getMonthValue();
            days1 = datePicker1.getValue().getDayOfMonth();

        });
        datePicker2.setOnAction(event -> {
            dataPickerSet2 = true;
            years2 = datePicker2.getValue().getYear();
            months2 = datePicker2.getValue().getMonthValue();
            days2 = datePicker2.getValue().getDayOfMonth();
        });

        if (dataPickerSet1 && dataPickerSet2){
            years = Math.abs(years1-years2);
            months = Math.abs(months1-months2);
            days = Math.abs(days1-days2);
            showFullTime.setText("Year(s): "+years+ " , Month(s): "+months+" , Day(s): "+days);
        }
    }
}
