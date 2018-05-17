package dk.base;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class DayDisplay extends VBox {

    private HBox header;
    private String displayName;
    private ArrayList<Card> cardsList;
    private App masterParent;

    private Card draggedCard;
    private boolean gotCardBeingDragged;

    private boolean isMouseOver;

    public DayDisplay(String displayName, App masterParent) {
        super();
        this.masterParent = masterParent;
        this.displayName = displayName;
        this.gotCardBeingDragged = false;
        this.draggedCard = null;
        this.isMouseOver = false;

        this.cardsList = new ArrayList<>();

        this.setMouseActions();

        //this.testAddCards(); //TODO TEST REMOVE

        this.setStyle(  "-fx-padding: 3;" + //Inside: space between border and content
                        "-fx-border-style: solid inside;" +
                        "-fx-border-width: 3;" +
                        "-fx-border-insets: 2;" + //Outside: space to other elements
                        "-fx-border-radius: 5;" + //Side of rounding in border
                        "-fx-border-color: black;");
        this.setMinSize(240, 240);

        Text title = new Text(displayName);
        header = new HBox();
        //header.setPadding(new Insets(2));
        header.setBackground(new Background(new BackgroundFill(new Color(198/255f, 255/255f, 163/255f, 1), null, null)));
        header.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
                BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
                CornerRadii.EMPTY, new BorderWidths(2), Insets.EMPTY)));
        //header.getChildren().add(title);

        updateDisplay();
    }

    //TODO Update method has to be changed.. maybe not remove all and then all.. maybe something else?
    public void updateDisplay(){

        //Remove all content
        this.getChildren().removeAll();

        //Add header and cards
        this.getChildren().add(header);
        this.getChildren().addAll(cardsList);
    }

    private void testAddCards(){

        this.cardsList.add(new Card("This is a test.", masterParent, this));
        this.cardsList.add(new Card("Number this needs to be done!", masterParent, this));
        this.cardsList.add(new Card("3??", masterParent, this));
    }

    protected void cardIsBeingDragged(Card card){
        this.draggedCard = card;
        this.gotCardBeingDragged = true;
    }

    protected void cardHasBeenReleased(){

        //TODO Call parent with card for check for other
        this.masterParent.cardDragged(this.draggedCard, this); //Pass card to master

        this.draggedCard = null;
        this.gotCardBeingDragged = false;
    }

    private void setMouseActions(){

        this.setOnMouseEntered(event -> {
            DayDisplay.this.isMouseOver = true;
            System.out.println("Mouse enter: " + DayDisplay.this.displayName);
            DayDisplay.this.masterParent.moveCardToDest(DayDisplay.this);
        });

        this.setOnMouseExited(event -> {
            DayDisplay.this.isMouseOver = false;
            System.out.println("Mouse exit: " + DayDisplay.this.displayName);
        });
    }

    public boolean isMouseOver() {
        return isMouseOver;
    }

    public void addCard(Card card){
        this.cardsList.add(card);
        this.updateDisplay();
    }

    public void removeCard(Card card){
        this.cardsList.remove(card);
        this.updateDisplay();
    }
}
