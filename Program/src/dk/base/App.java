package dk.base;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {

    private DayDisplay old;
    private DayDisplay today;
    private DayDisplay tomorrow;
    private DayDisplay theDayAfter;
    private DayDisplay thoughts;

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
        this.old = new DayDisplay("Old", this);
        this.today = new DayDisplay("Today", this);
        this.tomorrow = new DayDisplay("Tomorrow", this);
        this.theDayAfter = new DayDisplay("The Day After", this);
        this.thoughts = new DayDisplay("Thoughts", this); //TODO Might just get reworked to a textfield? Or something else?

        //Add the HBox' to the root
        root.getChildren().addAll(old, today, tomorrow, theDayAfter, thoughts);

        //Set the stage
        Scene scene = new Scene(root, 1280, 620);
        stage.setScene(scene);
        stage.setTitle("John Fish App.");
        stage.show();

        addTestCards(); //TODO REMOVE TEMP

        FileManager.saveToFile(root);
    }

    /** Used to add temp cards for testing. */
    private void addTestCards(){
        this.old.addCard(new Card("Something 1", old));
        this.today.addCard(new Card("Something 2", today));
        this.tomorrow.addCard(new Card("Something 3", tomorrow));
        this.theDayAfter.addCard(new Card("Something 4", theDayAfter));
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
