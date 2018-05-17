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
    private DayDisplay cardSource;

    @Override
    public void start(Stage stage) throws Exception {

        this.cardUnderTransfer = null;
        this.cardSource = null;
        this.isWaitingForTransfer = false;

        //Create root
        Pane root = new HBox();

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
    }

    private void addTestCards(){
        this.old.addCard(new Card("Sometihng 1", this, old));
        this.today.addCard(new Card("Sometihng 2", this, today));
        this.tomorrow.addCard(new Card("Sometihng 3", this, tomorrow));
        this.theDayAfter.addCard(new Card("Sometihng 4", this, theDayAfter));
    }

    protected void cardDragged(Card card, DayDisplay sourceDayDisplay){

        this.cardUnderTransfer = card;
        this.cardSource = sourceDayDisplay;
        this.isWaitingForTransfer = true;
    }

    protected void moveCardToDest(DayDisplay destDayDisplay) {

        if(this.isWaitingForTransfer){
            destDayDisplay.addCard(this.cardUnderTransfer);

            this.cardUnderTransfer = null;
            this.isWaitingForTransfer = false;
        }
    }
}
