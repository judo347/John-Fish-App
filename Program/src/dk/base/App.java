package dk.base;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    private DayDisplay old;
    private DayDisplay today;
    private DayDisplay tomorrow;
    private DayDisplay theDayAfter;
    private DayDisplay thoughts;

    private boolean isWaitingForTransfer;
    private Card cardUnderTransfer;
    private DayDisplay cardSourceDayDisplay;

    @Override
    public void start(Stage stage) {

        this.cardUnderTransfer = null;
        this.cardSourceDayDisplay = null;
        this.isWaitingForTransfer = false;

        //Create root
        HBox root = new HBox();

        //Create days
        this.old = new DayDisplay("Old", this, stage);
        this.today = new DayDisplay("Today", this, stage);
        this.tomorrow = new DayDisplay("Tomorrow", this, stage);
        this.theDayAfter = new DayDisplay("The Day After", this, stage);
        this.thoughts = new DayDisplay("Thoughts", this, stage); //TODO Might just get reworked to a textfield? Or something else?



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

    private void addTestCards(){
        this.old.addCard(new Card("Sometihng 1", old));
        this.today.addCard(new Card("Sometihng 2", today));
        this.tomorrow.addCard(new Card("Sometihng 3", tomorrow));
        this.theDayAfter.addCard(new Card("Sometihng 4", theDayAfter));
    }

    protected void cardDragged(Card card, DayDisplay sourceDayDisplay){

        this.cardUnderTransfer = card;
        this.cardSourceDayDisplay = sourceDayDisplay;
        this.isWaitingForTransfer = true;
    }

    protected void moveCardToDest(DayDisplay destDayDisplay) {

        if(this.isWaitingForTransfer){
            this.isWaitingForTransfer = false;

            cardSourceDayDisplay.removeCard(this.cardUnderTransfer);
            destDayDisplay.addCard(this.cardUnderTransfer);

            this.cardSourceDayDisplay = null;
            this.cardUnderTransfer = null;
        }
    }
}
