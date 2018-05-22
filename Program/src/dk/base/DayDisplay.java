package dk.base;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

/** This is the columns displayed in the app. */
public class DayDisplay extends VBox {

    private HBox header;
    private String displayName;
    private ArrayList<Card> cardsList;
    private App masterParent;

    private Card draggedCard;

    private boolean isMouseOver;

    /** The constructor for the display.
     *  @param displayName the name of the display.
     *  @param masterParent the parent of the display. */
    public DayDisplay(String displayName, App masterParent) {
        super(4);
        this.masterParent = masterParent;
        this.displayName = displayName;
        this.header = createHeader(displayName);
        this.draggedCard = null;
        this.isMouseOver = false;
        this.cardsList = new ArrayList<>();

        this.getChildren().add(header); // Add content

        this.setMouseActions();
        this.setStyle();

        updateDisplay();
    }

    /** Sets the style of the display. */
    private void setStyle(){
        this.getStyleClass().add("DayDisplay");
        //this.setStyle("DayDisplay");
        this.setMinSize(200, 620);
    }

    /** Creates and returns the header for the display.
     *  @param displayName the name to be displayed in the header. */
    private HBox createHeader(String displayName){
        Text title = new Text(displayName);
        HBox header = new HBox();
        header.setBackground(new Background(new BackgroundFill(new Color(198/255f, 255/255f, 163/255f, 1), null, null)));
        header.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
                BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
                CornerRadii.EMPTY, new BorderWidths(2), Insets.EMPTY)));
        header.getChildren().add(title);

        return header;
    }

    /** Updates the content of the display column. */
    private void updateDisplay(){

        //Remove all content
        this.getChildren().clear();

        //Add header and cards
        this.getChildren().add(header);
        this.getChildren().addAll(cardsList);
    }

    /** This is called when a card is dragged.
     *  Sets the display variable to that card. */
    protected void cardIsBeingDragged(Card card){
        this.draggedCard = card;
    }

    /** This is called when a card is released.
     *  Places the card into the correct display. */
    protected void cardHasBeenReleased(){

        this.masterParent.cardDragged(this.draggedCard, this); //Pass card to master
        this.draggedCard = null;
    }


    /** Sets mouse actions for the display. */
    private void setMouseActions() {

        // Triggers if the mouse has entered this display.
        // Sets the move card dest to this.
        this.setOnMouseEntered(event -> {
            DayDisplay.this.isMouseOver = true;
            DayDisplay.this.masterParent.moveCardToDest(DayDisplay.this);
        });

        // Triggers if the mouse has exited this display.
        this.setOnMouseExited(event -> DayDisplay.this.isMouseOver = false);

        // Trippers when display is clicked: popup with create a new card.
        // Also checks if the click happened on a card or this display.
        this.setOnMouseClicked(event -> {
            if(!DayDisplay.this.isMouseOverCard())
                PopupAddCard.display(DayDisplay.this);
        });
    }

    /** Added a card to this display. */
    public void addCard(Card card){

        // Adds the card to this display
        card.setDisplayParent(this);
        this.cardsList.add(card);

        // Update the display
        this.updateDisplay();
    }

    /** Removes a card from this display. */
    public void removeCard(Card card){
        this.cardsList.remove(card);
        this.updateDisplay();
    }

    public String getDisplayName() {
        return displayName;
    }

    /** Checks if the mouse is over one of the cards in this display. */
    public boolean isMouseOverCard(){

        boolean isMouseOverACard = false;

        for(Card card : cardsList){
            if(card.isMouseOver())
                isMouseOverACard = true;
        }

        return isMouseOverACard;
    }
}
