package dk.base;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Card extends HBox {

    public Card(String inputString) {
        super();

        setMouseActions();

        this.setBackground(new Background(new BackgroundFill(new Color(160/255f, 160/255f, 160/255f, 1), null, null)));
        //this.setPadding(new Insets(10)); //TODO does not give space between cards
        setStyleNoraml(); //Set style of this class
        CheckBox checkbox = new CheckBox();
        HBox left = new HBox();
        left.getChildren().add(checkbox);

        Text text = new Text(inputString);
        HBox right = new HBox();
        //right.setMaxWidth(0.1);
        right.getChildren().add(text);

        this.getChildren().addAll(left, right);
    }

    private void setMouseActions(){

        this.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Dragged!");
                Card.this.setStyleFaded(); //TODO TAKE NOTE OF THIS AKA REMEMBER!
            }
        });
    }

    private void setStyleNoraml(){

        this.setStyle(  "-fx-padding: 3;" + //Inside: space between border and content
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 3;" +
                "-fx-border-insets: 9;" + //Outside: space to other elements
                "-fx-border-radius: 5;" + //Side of rounding in border
                "-fx-border-color: black;" +
                "-fx-hgap: 50;" +
                "-fx-vgap: 50;");
    }

    private void setStyleFaded(){

        this.setStyle(  "-fx-padding: 3;" + //Inside: space between border and content
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 3;" +
                "-fx-border-insets: 9;" + //Outside: space to other elements
                "-fx-border-radius: 5;" + //Side of rounding in border
                "-fx-border-color: blue;" +
                "-fx-hgap: 50;" +
                "-fx-vgap: 50;");
    }
}
