package menu;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 03.07.2017.
 */
 public class ButtonField {
    static List<Button> ListOfButtons;
    static int menuNumber = 0;

    public static List<Button> getListOfButtons() {
        return ListOfButtons;
    }

    public static void getStandardButtonField(){
        ListOfButtons = new ArrayList<>();
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
            ListOfButtons.add(tempButt);
        }
    }
}
