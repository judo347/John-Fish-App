package dk.base;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class App extends Application {

    public enum DisplayNames{
        EXTRA("Extra"),
        WEEKGOALS("Weekgoals"),
        MONDAY("Monday"),
        TUESDAY("Tuesday"),
        WEDNESDAY("Wednesday"),
        THURSDAY("Thursday"),
        FRIDAY("Friday"),
        SATURDAY("Saturday"),
        SUNDAY("Sunday");

        private String text;

        DisplayNames(String text){ this.text = text; }
    }

    private HashMap<DisplayNames, DayDisplay> dayDisplays;

    private Card cardUnderTransfer;
    private DayDisplay cardSourceDayDisplay;
    private boolean isWaitingForTransfer;

    @Override
    public void start(Stage stage) {

        this.cardUnderTransfer = null;
        this.cardSourceDayDisplay = null;
        this.isWaitingForTransfer = false;

        //Create root
        HBox root = new HBox();

        //Create days
        dayDisplays = new HashMap<>();
        for(DisplayNames displayNames : DisplayNames.values())
            dayDisplays.put(displayNames, new DayDisplay(displayNames.text, this));

        //Add the HBox' to the root
        for(DisplayNames displayNames : DisplayNames.values())
            root.getChildren().add(dayDisplays.get(displayNames));

        //Set the stage
        Scene scene = new Scene(root, 1800, 620);
        scene.getStylesheets().add("dk/base/styles.css");
        stage.setScene(scene);
        stage.setTitle("John Fish App.");
        stage.show();

        addTestCards(); //TODO REMOVE TEMP

        //FileManager.saveToFile(root);
    }

    /** Used to add temp cards for testing. */
    private void addTestCards(){
        /*
        this.old.addCard(new Card("Something 1", old));
        this.today.addCard(new Card("Something 2", today));
        this.tomorrow.addCard(new Card("Something 3", tomorrow));
        this.theDayAfter.addCard(new Card("Something 4", theDayAfter));*/
        int i = 0;
        for(DisplayNames displayNames : DisplayNames.values())
            dayDisplays.get(displayNames).addCard(new Card(String.valueOf(i++), dayDisplays.get(displayNames)));

    }

    /** Used when card is being dragged. */
    protected void cardDragged(Card card, DayDisplay sourceDayDisplay){

        this.cardUnderTransfer = card;
        this.cardSourceDayDisplay = sourceDayDisplay;
        this.isWaitingForTransfer = true;
    }

    /** Used when card is released and moved to its destination.
     *  @param destDayDisplay the destination display. */
    protected void moveCardToDest(DayDisplay destDayDisplay) {

        //Check if there is a card waiting on transfer.
        if(this.isWaitingForTransfer){

            //Remove card from source display and add it to destination display.
            cardSourceDayDisplay.removeCard(this.cardUnderTransfer);
            destDayDisplay.addCard(this.cardUnderTransfer);

            //Reset used variables after transfer.
            this.isWaitingForTransfer = false;
            this.cardSourceDayDisplay = null;
            this.cardUnderTransfer = null;
        }
    }
}
