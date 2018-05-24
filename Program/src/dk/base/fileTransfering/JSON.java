package dk.base.fileTransfering;

import dk.base.App;
import dk.base.Card;
import dk.base.DayDisplay;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class JSON {

    public static String createJSONstring(HBox hBox){

        int numberOfDisplays = App.DisplayNames.values().length;

        //Get all displays
        if(hBox.getChildren().size() != numberOfDisplays)
            throw new IllegalArgumentException(); //Does not contain the right amount of displays

        ArrayList<DayDisplay> dayDisplayList = new ArrayList<>();

        for(int i = 0; i < numberOfDisplays; i++)
            dayDisplayList.add((DayDisplay)hBox.getChildren().get(i));

        //Get all cards
        ArrayList<Card> cardsList = new ArrayList<>();

        for(DayDisplay dayDisplay : dayDisplayList)
            cardsList.addAll(dayDisplay.getCardsList());

        //Convert card to JSON object?

        //Then convert to JSON

        //Then return string??

    }
}
