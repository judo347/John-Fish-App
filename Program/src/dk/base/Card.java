package dk.base;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class Card extends HBox {

    private DayDisplay displayParent;
    private boolean isDragging = false;

    public Card(String inputString, DayDisplay displayParent) {
        super();

        this.displayParent = displayParent;

        //this.setPadding(new Insets(10)); //TODO Should be done with css functions TODO does not give space between cards
        setStyleNormal(); //Set style of this class

        //Create checkbox
        CheckBox checkbox = new CheckBox();
        HBox left = new HBox();
        left.getChildren().add(checkbox);

        //Create string
        Text text = new Text(inputString);
        HBox right = new HBox();
        //right.setMaxWidth(0.1);
        right.getChildren().add(text);

        this.getChildren().addAll(left, right);

        setMouseActions();
    }

    private void setMouseActions(){

        this.setOnDragDetected(event -> {
            Card.this.isDragging = true;
            Card.this.setStyleFaded(); //TODO TAKE NOTE OF THIS AKA REMEMBER!
            Card.this.displayParent.cardIsBeingDragged(Card.this); //Send the card being dragged to DayDisplay parent.
            System.out.println("Detected");
        });

        this.setOnMouseReleased(event -> {
            if(Card.this.isDragging){
                System.out.println("Drag released");
                Card.this.isDragging = false;
                Card.this.setStyleNormal();
                Card.this.displayParent.cardHasBeenReleased(); //Send to the DayDisplay parrent, that the card is no longer being dragged.

                //TODO Check and remove card

            }
        });
    }

    private void setStyleNormal(){

        this.setStyle(  "-fx-padding: 3;" + //Inside: space between border and content
                "-fx-background-color: grey;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 3;" +
                "-fx-border-insets: 9;" + //Outside: space to other elements
                "-fx-border-radius: 5;" + //Side of rounding in border
                "-fx-border-color: black;" +
                "-fx-hgap: 50;" +
                "-fx-vgap: 50;");
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
}
