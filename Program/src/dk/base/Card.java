package dk.base;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

/** The card is the one displaying a checkbox and a text. */
public class Card extends HBox {

    private DayDisplay displayParent;
    private boolean isDragging = false;
    private CheckBox checkBox;
    private Text text;

    public Card(String inputString, DayDisplay displayParent) {
        super();

        this.displayParent = displayParent;

        setStyleNormal(); //Set style of this class

        //Create checkbox
        this.checkBox = new CheckBox();
        HBox left = new HBox();
        left.getChildren().add(checkBox);

        //Create text
        this.text = new Text(inputString);
        HBox right = new HBox();
        right.getChildren().add(text);

        this.getChildren().addAll(left, right);

        setMouseActions();
    }

    /** Sets mouse actions for the card. */
    private void setMouseActions(){

        // The action that happens when the card is dragged.
        // Change style and tell parent that this is being dragged.
        this.setOnDragDetected(event -> {
            Card.this.isDragging = true;
            Card.this.setStyleFaded();
            Card.this.displayParent.cardIsBeingDragged(Card.this); //Send the card being dragged to DayDisplay parent.
        });

        // The action that happens when the card is released from a drag.
        // Change style to normal and tell parent it has been released.
        this.setOnMouseReleased(event -> {
            if(Card.this.isDragging){ //Is the card getting released from a drag?
                Card.this.isDragging = false;
                Card.this.setStyleNormal();
                Card.this.displayParent.cardHasBeenReleased(); //Send to the DayDisplay parrent, that the card is no longer being dragged.
            }
        });
    }

    private void setStyleNormal(){

        this.setStyle(  "-fx-padding: 4;" + //Inside: space between border and content
                "-fx-background-color: grey;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 3;" +
                "-fx-border-insets: 1;" + //Outside: space to other elements
                "-fx-border-radius: 4;" + //Side of rounding in border
                "-fx-border-color: black;" +
                "-fx-hgap: 50;" + //TODO Does this really do anything?
                "-fx-vgap: 50;"); //TODO Does this really do anything?
    }

    private void setStyleFaded(){ //TODO Change to something that looks faded

        this.setStyle(  "-fx-padding: 3;" + //Inside: space between border and content
                "-fx-background-color: red;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 3;" +
                "-fx-border-insets: 9;" + //Outside: space to other elements
                "-fx-border-radius: 5;" + //Side of rounding in border
                "-fx-border-color: black;" +
                "-fx-hgap: 50;" +
                "-fx-vgap: 50;");
    }

    public void setDisplayParent(DayDisplay displayParent) {
        this.displayParent = displayParent;
    }

    public String getText() {
        return text.getText();
    }

    public boolean getCheckedStatus(){
        return this.checkBox.isSelected(); //TODO is this the correct way to get it?
    }
}
