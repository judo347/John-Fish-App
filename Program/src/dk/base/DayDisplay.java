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

    public DayDisplay(String displayName) {
        super();
        this.displayName = displayName;
        this.cardsList = new ArrayList<>();
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
        header.getChildren().add(title);

        updateDisplay();
    }

    public void updateDisplay(){

        this.testAddCards(); //TODO TEST REMOVE

        //Remove all content
        this.getChildren().removeAll();

        //Add header and cards
        this.getChildren().add(header);
        this.getChildren().addAll(cardsList);
    }

    private void testAddCards(){

        this.cardsList.add(new Card("This is a test."));
        this.cardsList.add(new Card("Number this needs to be done!"));
        this.cardsList.add(new Card("3??"));
    }
}
