package dk.base;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class FileManager {

    public static void saveToFile(HBox root){

        if(root.getChildren().size() != 5) //Check for correct number of elements
            throw new IllegalArgumentException();

        //TODO Is the order always this??

        DayDisplay old = (DayDisplay)root.getChildren().get(0);
        DayDisplay today = (DayDisplay)root.getChildren().get(1);
        DayDisplay tomorrow = (DayDisplay)root.getChildren().get(2);
        DayDisplay theDayAfter = (DayDisplay)root.getChildren().get(3);
        DayDisplay thoughts = (DayDisplay)root.getChildren().get(4);

        ArrayList<String> oldLines = fromDayDisplayToStrings(old);
        ArrayList<String> todayLines = fromDayDisplayToStrings(today);
        ArrayList<String> tomorrowLines = fromDayDisplayToStrings(tomorrow);
        ArrayList<String> theDayAfterLines = fromDayDisplayToStrings(theDayAfter);
        ArrayList<String> thoughtsLines = fromDayDisplayToStrings(thoughts);




    }

    private static ArrayList<String> fromDayDisplayToStrings(DayDisplay dayDisplay){

        ArrayList<String> cardStrings = new ArrayList<>();

        for(Node node : dayDisplay.getChildren()){

            if(node instanceof Card){
                cardStrings.add(((Card) node).getText());
            }
        }

        return cardStrings;
    }
}
