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

    public static void setMenuNumber(int menuNumber) {
        ButtonField.menuNumber = menuNumber;
    }

    public static String[] getStandardButtonField(int numFromList){
        List<String[]> listStandardButtonFields = new ArrayList<>();
        String standardButtonsSetNr1[] = {
                "7","8","9","/","CE",
                "4","5","6","X","C",
                "1","2","3","-","",
                ".","0","=","+",""
        };
        String standardButtonsSetNr2[] = {
                "9","8","7","/","CE",
                "6","5","4","X","C",
                "3","2","1","-","",
                ".","0","=","+",""
        };
        listStandardButtonFields.add(standardButtonsSetNr1);
        listStandardButtonFields.add(standardButtonsSetNr2);
        return listStandardButtonFields.get(numFromList);
    }
    public static void setStandardButtonField(){
        ListOfButtons = new ArrayList<>();
        String[] tempButtonList = getStandardButtonField(menuNumber);
        for (int i = 0; i < tempButtonList.length; i++) {
            Button tempButt = new Button(tempButtonList[i] + "");
            tempButt.setPrefSize(50, 50);
            ListOfButtons.add(tempButt);
        }
    }
}
