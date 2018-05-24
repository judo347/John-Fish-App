package dk.base.fileTransfering;

import dk.base.App;
import dk.base.Card;
import dk.base.DayDisplay;
import javafx.scene.layout.HBox;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSON {

    private static final String KEY_OWNEDBTDISPLAY = "ownedByDisplay";
    private static final String KEY_TEXT = "text";
    private static final String KEY_ISCHECKED = "isChecked";

    public void 

    /** Takes a HBox and returns an array of string that is cards in JSON format. */
    private static ArrayList<String> createJSONstring(HBox hBox){

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
        //ArrayList<Card> cardsList = new ArrayList<>();

        //for(Card card : cardsList)
        //    json.add(convertCardToJsoncard(card));

        //Then convert to JSON
        ArrayList<String> jsonStringArray = new ArrayList<>();

        for(Card card : cardsList)
            jsonStringArray.add(new JSONObject().put(KEY_OWNEDBTDISPLAY, card.getDisplayParent().getDisplayName())
                                                        .put(KEY_TEXT, card.getText())
                                                        .put(KEY_ISCHECKED, card.getCheckedStatus()).toString());

        return new ArrayList<>(jsonStringArray);
    }

    /** Converts a card to a JSONcard. */
    private static JSONcard convertCardToJsoncard(Card card){
        return new JSONcard(card.getDisplayParent().getDisplayName(), card.getText(), card.getCheckedStatus());
    }
}
