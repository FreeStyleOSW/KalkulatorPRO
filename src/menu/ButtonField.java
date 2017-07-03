package menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 03.07.2017.
 */
 public class ButtonField {
    public static String[] getButtonField(int numFromList){
        List<String[]> listButtonFields = new ArrayList<>();
        String buttonsSetNr1[] = {
                "7","8","9","/","CE",
                "4","5","6","X","C",
                "1","2","3","-","",
                ".","0","=","+",""
        };
        String buttonsSetNr2[] = {
                "9","8","7","/","CE",
                "6","5","4","X","C",
                "3","2","1","-","",
                ".","0","=","+",""
        };
        listButtonFields.add(buttonsSetNr1);
        listButtonFields.add(buttonsSetNr2);
        return listButtonFields.get(numFromList);
    }
}
