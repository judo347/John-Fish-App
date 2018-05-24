package dk.base;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

/** The card is the one displaying a checkbox and a text. */
public class Card extends HBox {

    private boolean isMouseOver;

    private DayDisplay displayParent;
    private boolean isDragging = false;
    private CheckBox checkBox;
    private Text text;

    private final String cssNormal = "CardNormal";
    private final String cssFaded = "CardFaded";

    public Card(String inputString, DayDisplay displayParent) {
        super();
        this.isMouseOver = false;

        this.displayParent = displayParent;

        addStyles();
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

        // Triggers if card is clicked. Use to edit or delete
        this.setOnMouseClicked(event -> PopupEditCard.display(Card.this.displayParent, Card.this));

        // Triggers if the mouse has entered this card.
        this.setOnMouseEntered(event -> Card.this.isMouseOver = true);

        // Triggers if the mouse has exited this card.
        this.setOnMouseExited(event -> Card.this.isMouseOver = false);
    }

    /** Adds the styles to the card. */
    private void addStyles(){
        //this.getStyleClass().add(cssNormal);
        //this.getStyleClass().add(cssFaded);
    }

    /** Set the style of the card to the normal one. */
    private void setStyleNormal(){
        this.getStyleClass().clear();
        this.getStyleClass().add(cssNormal);
        this.setStyle(cssNormal);
    }

    /** Set the style of the card to the faded one. */
    private void setStyleFaded(){
        this.getStyleClass().clear();
        this.getStyleClass().add(cssFaded);
        this.setStyle(cssFaded);
    }

    public void setDisplayParent(DayDisplay displayParent) {
        this.displayParent = displayParent;
    }

    /** Gets the text of the card. */
    public String getText() {
        return text.getText();
    }

    /** Sets the text of the card. */
    public void setText(String string){
        this.text.setText(string);
    }

    /** Gets the status of the checkbox. */
    public boolean getCheckedStatus(){
        return this.checkBox.isSelected(); //TODO is this the correct way to get it?
    }

    public boolean isMouseOver() {
        return isMouseOver;
    }

    public DayDisplay getDisplayParent() {
        return displayParent;
    }
}
