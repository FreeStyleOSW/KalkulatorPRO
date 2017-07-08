package menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Marcin on 03.07.2017.
 */
 public class ButtonField {
    private static List<Button> ListOfStandardButtons;
    private static FlowPane calculateFlowPane;
    private int years1,years2,months1,months2,days1,days2;
    private DatePicker datePicker1;
    private DatePicker datePicker2;
    private Label showFullTime;
    private Label showDayTime;
    private Label mainUpLabel;
    private Label mainDownLabel;

    public static List<Button> getListOfStandardButtons() {
        return ListOfStandardButtons;
    }

    public List<Button> getStandardButtonField(){
        ListOfStandardButtons = new ArrayList<>();
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
        return ListOfStandardButtons;
    }

    public FlowPane getCalculateFlowPane(){
        calculateFlowPane = new FlowPane();
        calculateFlowPane.setId("calculateFlowPane");
        calculateFlowPane.setLayoutX(24);
        calculateFlowPane.setLayoutY(61);

        Label fromLabel = new Label("OD");
        fromLabel.setFont(new Font("",15));
        fromLabel.setPrefSize(250,30);

        Label toLabel = new Label("DO");
        toLabel.setFont(new Font("",15));
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
        datePicker1.setPromptText(getCurrentDate());


        datePicker2 = new DatePicker();
        datePicker2.setPrefSize(250,30);
        datePicker2.setPromptText(getCurrentDate());

        calculateFlowPane.getChildren().addAll(fromLabel,datePicker1,toLabel,datePicker2,diffLabel,showFullTime,showDayTime);
        return calculateFlowPane;
    }

    public void actionForCalculateDate(){
        datePicker1.setOnAction(event -> {
            years1 = datePicker1.getValue().getYear();
            months1 = datePicker1.getValue().getMonthValue();
            days1 = datePicker1.getValue().getDayOfMonth();
            int years = Math.abs(years1-years2);
            int months = Math.abs(months1-months2);
            int days = Math.abs(days1-days2);
            int allOfDays = (years*365)+(months*30)+days;
            showFullTime.setText("Year(s): "+years+ "   Month(s): "+months+"   Day(s): "+days);
            showDayTime.setText("Day(s): ~"+allOfDays);
        });
        datePicker2.setOnAction(event -> {
            years2 = datePicker2.getValue().getYear();
            months2 = datePicker2.getValue().getMonthValue();
            days2 = datePicker2.getValue().getDayOfMonth();
            int years = Math.abs(years1-years2);
            int months = Math.abs(months1-months2);
            int days = Math.abs(days1-days2);
            int allOfDays = (years*365)+(months*30)+days;
            showDayTime.setText("Day(s): ~"+allOfDays);
            showFullTime.setText("Year(s): "+years+ "   Month(s): "+months+"   Day(s): "+days);
        });
    }

    public Label addMainUpLabel(){
        mainUpLabel = new Label("");
        mainUpLabel.setPrefSize(250,30);
        mainUpLabel.setLayoutX(25);
        mainUpLabel.setLayoutY(59);
        mainUpLabel.setFont(new Font("",20));
        mainUpLabel.setAlignment(Pos.BOTTOM_RIGHT);
        mainUpLabel.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)));

        return mainUpLabel;
    }

    public Label addMainDownLabel(){
        mainDownLabel = new Label("");
        mainDownLabel.setPrefSize(250,30);
        mainDownLabel.setLayoutX(25);
        mainDownLabel.setLayoutY(89);
        mainDownLabel.setFont(new Font("",20));
        mainDownLabel.setAlignment(Pos.BOTTOM_RIGHT);
        mainDownLabel.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        return mainDownLabel;
    }

    public String getCurrentDate(){
        int currDay = new GregorianCalendar().get(Calendar.DAY_OF_MONTH);
        int currMonth = new GregorianCalendar().get(Calendar.MONTH)+1;
        String currYearString = ""+new GregorianCalendar().get(Calendar.YEAR);
        String currDayString;
        String currMonthString;
        if (currDay < 10) currDayString = "0"+currDay;
        else currDayString = currDay+"";
        if (currMonth < 10) currMonthString = "0"+currMonth;
        else currMonthString = currMonth+"";

        return currDayString+"."+currMonthString+"."+currYearString;
    }
}
